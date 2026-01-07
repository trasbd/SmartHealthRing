import asyncio
from collections import deque
from dataclasses import dataclass
from typing import Optional
import time
from datetime import datetime

from bleak import BleakClient
from bleak.exc import BleakDeviceNotFoundError


# ================================
# Pending command (queue element)
# ================================
@dataclass
class PendingCommand:
    cmd: int
    group: int
    subtype: int
    payload: bytes


# ================================
# Main ring class
# ================================
class SmartHealthR99:

    OFFSET_2000 = 946_684_800  # seconds

    def __init__(self, mac: str, mtu: int = 185):
        self.mac = mac
        self.mtu = mtu
        self.client: Optional[BleakClient] = None

        # ---- Queue / RX state ----
        self.send_queue: deque[PendingCommand] = deque()
        self.rx_buffer: Optional[bytearray] = None
        self.rx_fragmented: bool = False
        self.rx_lock = asyncio.Lock()

        self.cmd_ack = False

        self.health_session: HealthSession | None = None

        self.group2_handlers = {
            0: self.parse_device_info,
            # 1: self.parse_device_support_function,
            # 3: self.parse_device_name,
            # 9: self.parse_home_theme,
            # 10: self.parse_ecg_location,
            # 11: self.parse_device_screen_info,
            # 12: self.parse_now_sport,
            # 13: self.parse_history_outline,
            # 14: self.parse_real_temp,
            # 15: self.parse_screen_info,
            # 16: self.parse_heaven_earth,
            # 17: self.parse_real_blood_oxygen,
            # 18: self.parse_ambient_light,
            # 19: self.parse_ambient_temp_humidity,
            # 20: self.parse_schedule_info,
            # 21: self.parse_sensor_sampling,
            # 22: self.parse_working_mode,
            # 23: self.parse_insurance_info,
            # 24: self.parse_reminder_upload_cfg,
            # 25: self.parse_manual_mode_status,
            # 26: self.parse_event_reminder,
            # 27: self.parse_chip_scheme,
            # 31: self.parse_device_remind_info,
            # 32: self.parse_all_real_data,
            # 33: self.parse_laser_params,
            # 34: self.parse_ali_iot_state,
            # 35: self.parse_screen_params,
            37: self.parse_power_stats,
            38: self.parse_sleep_status,
            # 39: self.parse_ecg_mode,
            # 40: self.parse_measurement_function,
            # 41: self.parse_algorithm_license,
            # 42: self.parse_terminal_conf,
        }

    # ================================
    # BE94 UUIDs
    # ================================
    UUID_SERVICE = "be940000-7333-be46-b7ae-689e71722bd5"

    UUID_WRITE = "be940001-7333-be46-b7ae-689e71722bd5"
    UUID_WRITE2 = "be940002-7333-be46-b7ae-689e71722bd5"
    UUID_IND_BE94_SECOND = "be940003-7333-be46-b7ae-689e71722bd5"

    UUID_NOTIFY_AE02 = "0000ae02-0000-1000-8000-00805f9b34fb"
    UUID_IND_FEA1 = "0000fea1-0000-1000-8000-00805f9b34fb"
    UUID_IND_FEA2 = "0000fea2-0000-1000-8000-00805f9b34fb"

    UUID_NOTIFY_NUS_TX = "6e400003-b5a3-f393-e0a9-e50e24dcca9e"

    # ============================
    # Connection
    # ============================
    async def connect(self):

        print(f"Attempting to connect to {self.mac}...")

        self.client = BleakClient(self.mac, timeout=30)
        start = time.monotonic()
        while True:
            try:
                await self.client.connect()
                break  # ✅ success
            except BleakDeviceNotFoundError:
                elapsed = time.monotonic() - start
                print(f"❌ Device not found after {elapsed:.2f} s, retrying...")
                await asyncio.sleep(1)

            # except BleakError as e:
            #     print(f"❌ BLE error: {e}, retrying...")
            #     await asyncio.sleep(1)

        elapsed = time.monotonic() - start
        print(f"✅ Connected in {elapsed:.2f} s")
        print("Subscribing...")

        await self.client.start_notify(self.UUID_WRITE, self._on_notify)
        await self.client.start_notify(self.UUID_IND_BE94_SECOND, self._on_notify)
        await self.client.start_notify(self.UUID_NOTIFY_AE02, self._on_notify)
        # await self.client.start_notify(self.UUID_IND_FEA1, self._on_notify)
        await self.client.start_notify(self.UUID_IND_FEA2, self._on_notify)
        await self.client.start_notify(self.UUID_NOTIFY_NUS_TX, self._on_notify)

        print(f"✅ Connected to {self.mac}, notifications enabled")

    async def disconnect(self) -> None:
        client = self.client
        if client is None:
            print("Not connected.")
            return
        await client.disconnect()
        print("Disconnected.")

    # ============================
    # Command send (enqueue first)
    # ============================
    async def send_cmd(self, cmd: int, payload: bytes = b""):
        pending = PendingCommand(
            cmd=cmd,
            group=(cmd >> 8) & 0xFF,
            subtype=cmd & 0xFF,
            payload=payload,
        )

        self.send_queue.append(pending)

        # If this is the only command, start sending immediately
        if len(self.send_queue) == 1:
            await self._send_pending(pending)

    async def _send_pending(self, pending: PendingCommand):
        frame = self.build_be94_frame(pending.cmd, pending.payload)
        chunks = self.chunk_for_mtu(frame, self.mtu)

        for chunk in chunks:
            await self.client.write_gatt_char(self.UUID_WRITE, chunk, response=True)
        self.cmd_ack = False

    # ============================
    # Notify handler
    # ============================
    def _on_notify(self, _: int, data: bytearray):
        asyncio.create_task(self._on_notify_async(_, data))

    async def _on_notify_async(self, _: int, data: bytearray):
        async with self.rx_lock:
            if not data or len(data) < 4:
                return

            full = self._reassemble_frame(data)
            if full is None:
                return

            group, subtype, payload = self._decode_frame(full)
            await self._handle_frame(group, subtype, payload)

    def _reassemble_frame(self, data: bytearray) -> Optional[bytearray]:
        expected_len = data[2] | (data[3] << 8)

        if expected_len == len(data):
            return data

        # length mismatch path
        if not self.rx_fragmented and len(data) != self.mtu - 3:
            return None

        self.rx_fragmented = True

        if self.rx_buffer is None:
            self.rx_buffer = bytearray(data)
            return None

        self.rx_buffer.extend(data)

        if len(self.rx_buffer) < 4:
            return None

        new_expected = self.rx_buffer[2] | (self.rx_buffer[3] << 8)

        if len(self.rx_buffer) < new_expected:
            return None

        if len(self.rx_buffer) > new_expected:
            self.rx_buffer = None
            self.rx_fragmented = False
            return None

        full = self.rx_buffer
        self.rx_buffer = None
        self.rx_fragmented = False
        return full

    def _decode_frame(self, data: bytearray) -> tuple[int, int, bytes]:
        group = data[0]
        subtype = data[1]
        total_len = data[2] | (data[3] << 8)

        payload_len = total_len - 6
        payload = data[4 : 4 + payload_len]

        return group, subtype, payload

    # ============================
    # Group handlers
    # ============================
    async def _handle_frame(self, group: int, subtype: int, payload: bytes):
        popped = False
        if self.send_queue:
            head = self.send_queue[0]
            if head.group == group and head.subtype == subtype:
                self.send_queue.popleft()
                self.cmd_ack = True

        
        match group:
            case 1:
                self._handle_group_1(subtype, payload)
            case 2:
                self._handle_group_2(subtype, payload)
                popped = True
            case 5:
                await self._handle_group_5(subtype, payload)
                if subtype == HealthSession.EndSubtype and self.cmd_ack:
                    popped = True

            case _:
                print(f'{group} {subtype} ' + " ".join(f"{b:02X}" for b in payload))

            # 🔑 THIS IS THE PUMP
        if popped and self.send_queue:
            await self._send_pending(self.send_queue[0])

    def _handle_group_1(self, subtype: int, payload: bytes):
        # Stub
        pass

    def _handle_group_2(self, subtype: int, payload: bytes):
        handler = self.group2_handlers.get(subtype)
        if handler:
            print(handler(payload))

    async def _handle_group_5(self, subtype: int, payload: bytes):
        if self.health_session is None:
            self.health_session = HealthSession()

        self.health_session.ingest(subtype, payload)

        if not self.health_session.is_complete():
            return

        # ---- session complete ----
        session = self.health_session
        self.health_session = None

        data = session.parse()

        # Java: sendData2Device(1408, new byte[]{0})
        await self.send_cmd(session.EndCommand, session.EndPayload)

        print("🫀 Health data received:")
        print(data)

    # ============================
    # Group 2 Parsers
    # ============================
    def parse_sleep_status(self, payload: bytes):
        """
        Parse GetSleepStatus (cmd 550) response.
        Mirrors app logic:
        - check error first
        - otherwise decode sleepStatus
        """
        if not payload:
            print("😴 SleepStatus: empty payload")
            return

        # ---- Error check (exact port of isError) ----
        if self.Errors.is_error_payload(payload):
            code = payload[0] & 0xFF
            msg = self.Errors.ERRORS.get(code, f"Unknown error ({code})")
            print(f"❌ GetSleepStatus error: {msg}")
            return

        # ---- Normal decode ----
        sleep_status = payload[0] & 0xFF
        print(f"😴 SleepStatus value: {sleep_status}")

    def parse_device_info(self, payload: bytes):
        """
        Clean Python port of SmartHealth unpackDeviceInfoData()
        """

        # Strip BE94 wrapper: CMD(2), LEN(2), BODY, CRC(2)

        device_info = {}

        # ----------------------------------------
        # Basic Fields (first 8 bytes)
        # ----------------------------------------
        device_id = payload[0] | (payload[1] << 8)
        version_sub = payload[2]
        version_main = payload[3]
        battery_state = payload[4]
        battery_value = payload[5]
        bind_state = payload[6]
        sync_state = payload[7]

        version_str = f"{version_main}.{version_sub:02d}"

        # Fill base fields
        device_info.update(
            {
                "deviceId": device_id,
                "deviceVersion": version_str,
                "deviceBatteryState": battery_state,
                "deviceBatteryValue": battery_value,
                "deviceMainVersion": version_main,
                "deviceSubVersion": version_sub,
                "devicetBindState": bind_state,
                "devicetSyncState": sync_state,
            }
        )

        hardware_type = 0

        # ----------------------------------------
        # Optional Extended Fields (bytes 8..18)
        # ----------------------------------------
        if len(payload) >= 24:
            device_info.update(
                {
                    "bleAgreementSubVersion": payload[8],
                    "bleAgreementMainVersion": payload[9],
                    "bloodAlgoSubVersion": payload[10],
                    "bloodAlgoMainVersion": payload[11],
                    "tpSubVersion": payload[12],
                    "tpMainVersion": payload[13],
                    "bloodSugarSubVersion": payload[14],
                    "bloodSugarMainVersion": payload[15],
                    "uiSubVersion": payload[16],
                    "uiMainVersion": payload[17],
                }
            )

            hardware_type = payload[18]

        device_info["hardwareType"] = hardware_type

        # ----------------------------------------
        # Final output structure (matches Java)
        # ----------------------------------------
        out = {
            "code": 0,
            "dataType": 512,
            "data": device_info,
        }

        # Debug print
        print("📟 Device Info:")
        for k, v in device_info.items():
            print(f"\t{k}: {v}")

        return out

    def parse_power_stats(self, payload: bytes) -> dict:
        """
        Exact port of Java unpackGetPowerStatistics(byte[]).
        Expects payload length >= 34 bytes.
        All fields and math mirror SmartHealth behavior.
        """

        if payload is None or len(payload) < 34:
            raise ValueError("Power statistics payload too short")

        # Java: TimeZone.getDefault().getOffset(System.currentTimeMillis())
        tz_offset_ms = int(time.localtime().tm_gmtoff * 1000)

        # ---- lastChargingTime ----
        last_charge_sec = (
            (payload[0] & 0xFF)
            | ((payload[1] & 0xFF) << 8)
            | ((payload[2] & 0xFF) << 16)
            | ((payload[3] & 0xFF) << 24)
        )

        last_charging_time_ms = (
            last_charge_sec + self.OFFSET_2000
        ) * 1000 - tz_offset_ms

        # ---- durations / counters ----
        usage_time = (
            (payload[4] & 0xFF)
            | ((payload[5] & 0xFF) << 8)
            | ((payload[6] & 0xFF) << 16)
            | ((payload[7] & 0xFF) << 24)
        )

        screen_duration = (
            (payload[8] & 0xFF)
            | ((payload[9] & 0xFF) << 8)
            | ((payload[10] & 0xFF) << 16)
            | ((payload[11] & 0xFF) << 24)
        )

        call_duration = (
            (payload[12] & 0xFF)
            | ((payload[13] & 0xFF) << 8)
            | ((payload[14] & 0xFF) << 16)
            | ((payload[15] & 0xFF) << 24)
        )

        music_duration = (
            (payload[16] & 0xFF)
            | ((payload[17] & 0xFF) << 8)
            | ((payload[18] & 0xFF) << 16)
            | ((payload[19] & 0xFF) << 24)
        )

        health_measurement_duration = (
            (payload[20] & 0xFF)
            | ((payload[21] & 0xFF) << 8)
            | ((payload[22] & 0xFF) << 16)
            | ((payload[23] & 0xFF) << 24)
        )

        messages_number = (
            (payload[24] & 0xFF)
            | ((payload[25] & 0xFF) << 8)
            | ((payload[26] & 0xFF) << 16)
            | ((payload[27] & 0xFF) << 24)
        )

        # ---- battery ----
        last_charging_end_battery = payload[28] & 0xFF
        battery_level = payload[29] & 0xFF

        # ---- arated blood pressure ----
        arated_blood_pressure = (
            (payload[30] & 0xFF)
            | ((payload[31] & 0xFF) << 8)
            | ((payload[32] & 0xFF) << 16)
            | ((payload[33] & 0xFF) << 24)
        )

        return {
            "lastChargingTime": last_charging_time_ms,
            "lastChargingDateTime": datetime.fromtimestamp(
                last_charging_time_ms / 1000
            ).strftime("%Y-%m-%d %H:%M:%S"),
            "usageTime": usage_time,
            # "screenDuration": screen_duration,
            # "callDuration": call_duration,
            # "musicDuration": music_duration,
            "healthMeasurementDuration": health_measurement_duration,
            # "messagesNumber": messages_number,
            "lastChargingEndBattery": last_charging_end_battery,
            "batteryLevel": battery_level,
            # "aratedBloodPressure": arated_blood_pressure,
            # "dataType": "GetPowerStatistics",
        }

    # ============================
    # Utilities
    # ============================
    @staticmethod
    def build_be94_frame(cmd: int, payload: bytes) -> bytes:
        total_len = len(payload) + 6
        out = bytearray(total_len)

        out[0] = (cmd >> 8) & 0xFF
        out[1] = cmd & 0xFF
        out[2] = total_len & 0xFF
        out[3] = (total_len >> 8) & 0xFF
        out[4 : 4 + len(payload)] = payload

        crc = SmartHealthR99.crc16_ycbt(out[: 4 + len(payload)])
        out[4 + len(payload)] = crc & 0xFF
        out[5 + len(payload)] = (crc >> 8) & 0xFF

        return bytes(out)

    @staticmethod
    def chunk_for_mtu(data: bytes, mtu: int):
        usable = mtu - 3
        return [data[i : i + usable] for i in range(0, len(data), usable)]

    @staticmethod
    def crc16_ycbt(data: bytes, seed: int = 0xFFFF) -> int:
        s = seed & 0xFFFF
        for byte in data:
            swapped = ((s << 8) & 0xFF00) | ((s >> 8) & 0x00FF)
            s2 = swapped ^ byte
            s2 ^= (s2 & 0xFF) >> 4
            s3 = s2 ^ ((s2 << 12) & 0xFFFF)
            s = s3 ^ (((s3 & 0xFF) << 5) & 0xFFFF)
            s &= 0xFFFF
        return s

    class Errors:
        ERRORS = {
            251: "Unsupported Command ID",
            252: "Unsupported Key",
            253: "Length Error",
            254: "Data Error",
            255: "CRC16 Error",
        }

        @staticmethod
        def is_error_payload(payload: bytes) -> bool:
            return (
                payload is not None
                and len(payload) == 1
                and (payload[0] & 0xF0) == 0xF0
            )


class HealthSession:
    SleepHealthHeader = 4
    HealthTypes = (SleepHealthHeader, 8, 9)
    EndSubtype = 128
    EndCommand = 1408
    EndPayload = bytes([0])

    SleepTypes = {
        241: "Deep Sleep",
        242: "Light Sleep",
        243: "REM",
    }

    def __init__(self):
        self.health_type: int | None = None
        self.blocks: list[bytes] = []
        self.complete: bool = False

    def ingest(self, subtype: int, payload: bytes):
        """
        Feed one health packet into the session.
        """
        # start of session
        # print(f"{subtype} {payload}")
        if subtype in self.HealthTypes:
            self.health_type = subtype
            self.blocks.clear()
            self.complete = False
            return

        if self.health_type is None:
            return  # ignore stray packets

        self.blocks.append(payload)

        # end of session
        if subtype == self.EndSubtype:
            self.complete = True

    def is_complete(self) -> bool:
        return self.complete

    def parse(self) -> dict:
        if self.health_type is None:
            raise RuntimeError("No health session active")

        raw = b"".join(self.blocks)
        return self.unpack_health_data(raw, self.health_type)

    def unpack_health_data(self, raw: bytes, health_type: int) -> dict:
        """
        Faithful port of DataUnpack.unpackHealthData()
        Currently implements CASE 9 (Health_HistoryAll) only.
        """

        match health_type:
            case 4:
                return self.unpack_Sleep_Data(raw, health_type)
            case 9:
                return self.unpack_Health_HistoryAll(raw, health_type)
            case _:
                raise NotImplementedError(
                    f"Health type {health_type} not implemented yet"
                )

    def unpack_Health_HistoryAll(self, raw: bytes, health_type: int) -> dict:
        OFFSET_2000 = 946684800
        TZ_OFFSET_MS = int(time.localtime().tm_gmtoff * 1000)

        out = {
            "code": 0,
            "dataType": health_type,
            "data": [],
        }

        i = 0
        length = len(raw)

        RECORD_LEN = 20

        while i + RECORD_LEN <= length:
            b = raw

            # ---- timestamp ----
            ts_sec = (
                (b[i] & 0xFF)
                | ((b[i + 1] & 0xFF) << 8)
                | ((b[i + 2] & 0xFF) << 16)
                | ((b[i + 3] & 0xFF) << 24)
            )
            start_time = ((ts_sec + OFFSET_2000) * 1000) - TZ_OFFSET_MS

            start_datetime = datetime.fromtimestamp(start_time / 1000.0).strftime(
                "%Y%m%d %H%M%S"
            )

            # ---- fields (EXACT Java mapping) ----
            step_value = (b[i + 4] & 0xFF) | ((b[i + 5] & 0xFF) << 8)
            heart_value = b[i + 6] & 0xFF
            sbp_value = b[i + 7] & 0xFF
            dbp_value = b[i + 8] & 0xFF
            oo_value = b[i + 9] & 0xFF
            respiratory_rate = b[i + 10] & 0xFF
            hrv_value = b[i + 11] & 0xFF
            cvrr_value = b[i + 12] & 0xFF
            temp_int = b[i + 13] & 0xFF
            temp_float = b[i + 14] & 0xFF
            body_fat_int = b[i + 15] & 0xFF
            body_fat_float = b[i + 16] & 0xFF
            blood_sugar = b[i + 17] & 0xFF

            record = {
                "startTime": start_time,
                "startDateTime": start_datetime,
                "stepValue": step_value,
                "heartValue": heart_value,
                # "SBPValue": sbp_value,
                # "DBPValue": dbp_value,
                "OOValue": oo_value,
                "respiratoryRateValue": respiratory_rate,
                "hrvValue": hrv_value,
                "cvrrValue": cvrr_value,
                "tempIntValue": temp_int,
                "tempFloatValue": temp_float,
                # "bodyFatIntValue": body_fat_int,
                # "bodyFatFloatValue": body_fat_float,
                # "bloodSugarValue": blood_sugar,
            }

            out["data"].append(record)
            i += RECORD_LEN

        return out

    def unpack_Sleep_Data(self, raw: bytes, health_type: int) -> dict:
        OFFSET_2000 = 946684800
        TZ_OFFSET_MS = int(time.localtime().tm_gmtoff * 1000)

        out = {
            "code": 0,
            "dataType": health_type,
            "data": [],
        }

        b = raw
        i = 0
        length = len(b)

        while i + 20 <= length:
            session_start = i

            # ---- session header ----
            session_len = (b[i + 2] & 0xFF) | ((b[i + 3] & 0xFF) << 8)

            start_sec = (
                (b[i + 4] & 0xFF)
                | ((b[i + 5] & 0xFF) << 8)
                | ((b[i + 6] & 0xFF) << 16)
                | ((b[i + 7] & 0xFF) << 24)
            )

            end_sec = (
                (b[i + 8] & 0xFF)
                | ((b[i + 9] & 0xFF) << 8)
                | ((b[i + 10] & 0xFF) << 16)
                | ((b[i + 11] & 0xFF) << 24)
            )

            start_time = ((start_sec + OFFSET_2000) * 1000) - TZ_OFFSET_MS
            end_time = ((end_sec + OFFSET_2000) * 1000) - TZ_OFFSET_MS

            deep_sleep_count = (b[i + 12] & 0xFF) | ((b[i + 13] & 0xFF) << 8)

            # ---- dual interpretation block (EXACT Java behavior) ----
            if deep_sleep_count == 0xFFFF:
                rem_total = (b[i + 14] & 0xFF) | ((b[i + 15] & 0xFF) << 8)
                deep_total = (b[i + 16] & 0xFF) | ((b[i + 17] & 0xFF) << 8)
                light_total = (b[i + 18] & 0xFF) | ((b[i + 19] & 0xFF) << 8)
                light_count = 0
            else:
                light_count = (b[i + 14] & 0xFF) | ((b[i + 15] & 0xFF) << 8)
                rem_total = 0
                deep_total = ((b[i + 16] & 0xFF) | ((b[i + 17] & 0xFF) << 8)) * 60
                light_total = ((b[i + 18] & 0xFF) | ((b[i + 19] & 0xFF) << 8)) * 60

            # ---- parse sleep segments ----
            sleep_segments = []
            seen = set()
            wake_count = 0
            wake_duration = 0

            seg_ptr = session_start + 20
            session_end = session_start + session_len

            while seg_ptr + 8 <= session_end:
                sleep_type = b[seg_ptr] & 0xFF

                seg_sec = (
                    (b[seg_ptr + 1] & 0xFF)
                    | ((b[seg_ptr + 2] & 0xFF) << 8)
                    | ((b[seg_ptr + 3] & 0xFF) << 16)
                    | ((b[seg_ptr + 4] & 0xFF) << 24)
                )

                seg_time = ((seg_sec + OFFSET_2000) * 1000) - TZ_OFFSET_MS

                dur = (
                    (b[seg_ptr + 5] & 0xFF)
                    | ((b[seg_ptr + 6] & 0xFF) << 8)
                    | ((b[seg_ptr + 7] & 0xFF) << 16)
                )

                if sleep_type == 244:  # wake
                    wake_count += 1
                    wake_duration += dur

                if seg_time not in seen:
                    sleep_segments.append(
                        {
                            "sleepType": sleep_type,
                            "sleepStartTime": seg_time,
                            "sleepStartDateTime": datetime.fromtimestamp(
                                seg_time / 1000
                            ).strftime("%Y%m%d %H%M%S"),
                            "sleepLen": dur,
                        }
                    )
                    seen.add(seg_time)

                seg_ptr += 8

            out["data"].append(
                {
                    "startTime": start_time,
                    "startDateTime": datetime.fromtimestamp(start_time / 1000).strftime(
                        "%Y%m%d %H%M%S"
                    ),
                    "endTime": end_time,
                    "endDateTime": datetime.fromtimestamp(end_time / 1000).strftime(
                        "%Y%m%d %H%M%S"
                    ),
                    "deepSleepCount": deep_sleep_count,
                    "lightSleepCount": light_count,
                    "deepSleepTotal": deep_total,
                    "lightSleepTotal": light_total,
                    "rapidEyeMovementTotal": rem_total,
                    "sleepData": sleep_segments,
                    "wakeCount": wake_count,
                    "wakeDuration": wake_duration,
                }
            )

            i = seg_ptr  # EXACT Java behavior

        return out


async def main():
    ring = SmartHealthR99("07:35:00:01:8A:EC", mtu=185)
    await ring.connect()

    #await ring.send_cmd(549)

    # await ring.send_cmd(1284)
    # await ring.send_cmd(1289)
    # await ring.send_cmd(512, bytes([71, 67]))
    await ring.send_cmd(815, bytes([1,0]) )

    print("📡 Requests sent, watching for notifications...")
    await asyncio.sleep(30)
    await ring.send_cmd(815, bytes([0,0]) )
    await asyncio.sleep(5)

    print(ring.send_queue)

    await ring.disconnect()


if __name__ == "__main__":
    asyncio.run(main())

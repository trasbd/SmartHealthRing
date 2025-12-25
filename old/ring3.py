import asyncio
from bleak import BleakClient
import time
from bleak.exc import BleakDeviceNotFoundError, BleakError
from capabilities import DeviceSupportCapabilities


# ================================================
# CMD CLASS + GLOBAL CMD_REGISTRY
# ================================================
class CMD:
    def __init__(self, name: str, cmd: int, parser: str = "", payload: bytes = b"", response: bool = False):
        self.name = name
        self.cmd = cmd
        self.parser = parser
        self.payload = payload
        self.response = response

def is_error_payload(payload: bytes) -> bool:
    return (
        payload is not None
        and len(payload) == 1
        and (payload[0] & 0xF0) == 0xF0
    )

ERRORS = {
    251: "Unsupported Command ID",
    252: "Unsupported Key",
    253: "Length Error",
    254: "Data Error",
    255: "CRC16 Error",
}


# Global list of CMD objects
CMD_REGISTRY = [
    CMD("Health_HistorySport", 1282, "parse_1282"),
    CMD("Health_HistorySleep", 1284, "parse_1284"),
    CMD("Health_HistoryHeart", 1286, "parse_1286"),
    CMD("Health_HistoryBlood", 1288, "parse_1288"),
    CMD("Health_HistoryAll", 1289, "parse_1289"),
    CMD("Health_HistoryComprehensiveMeasureData", 1327, "parse_1327"),
    CMD("Health_HistorySportMode", 1325, "parse_1325"),
    CMD("Health_History_Body_Data", 1331, "parse_1331"),
    CMD("Health_HistoryBlock", 1408, "parse_1408"),
    CMD("Customize_Data_Sync", 3445, "parse_3445"),
    CMD("GetDeviceSupportFunction", 513, "parse_GetDeviceSupportFunction", bytes([71, 70]),),
    CMD("GetDeviceInfo", 512, "parse_512_device_info", bytes([71, 67])),
    CMD("Unknown_1304", 1304, "parse_1304"),
    CMD("GetSleepStatus", 550, "parse_550_sleepStatus")
]


# ================================================
# SmartHealth R99 Ring Client
# ================================================
class SmartHealthR99:

    # BLE UUID definitions
    UUID_SERVICE = "be940000-7333-be46-b7ae-689e71722bd5"

    UUID_WRITE = "be940001-7333-be46-b7ae-689e71722bd5"
    UUID_WRITE2 = "be940002-7333-be46-b7ae-689e71722bd5"
    UUID_IND_BE94_SECOND = "be940003-7333-be46-b7ae-689e71722bd5"

    UUID_NOTIFY_AE02 = "0000ae02-0000-1000-8000-00805f9b34fb"
    UUID_IND_FEA1 = "0000fea1-0000-1000-8000-00805f9b34fb"
    UUID_IND_FEA2 = "0000fea2-0000-1000-8000-00805f9b34fb"

    UUID_NOTIFY_NUS_TX = "6e400003-b5a3-f393-e0a9-e50e24dcca9e"

    # ============================================
    # CMD LOOKUP HELPERS
    # ============================================
    @staticmethod
    def find_cmd(name: str) -> CMD | None:
        return next((c for c in CMD_REGISTRY if c.name == name), None)

    @classmethod
    def name_from_cmd(cls, cmd_value: int):
        for entry in CMD_REGISTRY:
            if entry.cmd == cmd_value:
                return entry.name
        return None

    # ============================================
    # Constructor
    # ============================================
    def __init__(self, mac: str, mtu: int = 185):
        self.mac = mac
        self.mtu = mtu
        self.client: BleakClient | None = None
        self.capabilities = DeviceSupportCapabilities

    # ============================================
    # Static Helpers
    # ============================================
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

    # ============================================
    # Connect
    # ============================================
    async def connect(self):

        print(f"Attempting to connect to {self.mac}...")
        

        self.client = BleakClient(self.mac, timeout=30)
        start = time.monotonic()
        while True:
            try:
                await self.client.connect()
                break  # ✅ success
            except BleakDeviceNotFoundError as e:
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
        await self.client.start_notify(self.UUID_IND_FEA1, self._on_notify)
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

    

    # ============================================
    # Notification Handler
    # ============================================
    def _on_notify(self, characteristic, data: bytes):

        raw_hex = data.hex()
        cmd_value = int.from_bytes(data[:2], "big") if len(data) >= 2 else None

        print(f"[NOTIFY {characteristic}] | {cmd_value} | {raw_hex}")

        if cmd_value is None:
            return

        name = self.name_from_cmd(cmd_value)
        if not name:
            return

        entry = self.find_cmd(name)
        if not entry:
            return

        parser_fn = getattr(self, entry.parser, None)
        if parser_fn:
            parser_fn(data)

    # ============================================
    # Command Sender
    # ============================================
    async def send_cmd(self, cmd, payload: bytes = None, response: bool = None):
        """
        Flexible command sender:
        - send_cmd(CMD_OBJECT)
        - send_cmd(cmd_id, payload, secondary)
        """

        # Case 1: User passed a CMD object
        if isinstance(cmd, CMD):
            cmd_id = cmd.cmd
            payload = cmd.payload
            response = cmd.response

        # Case 2: User passed raw ID + optional payload/secondary
        else:
            cmd_id = cmd
            payload = payload or b""
            response = response or False

        # Build frame
        frame = self.build_be94_frame(cmd_id, payload)
        chunks = self.chunk_for_mtu(frame, self.mtu)

        for chunk in chunks:
            await self.client.write_gatt_char(self.UUID_WRITE, chunk, response=True)
            await asyncio.sleep(0.03)

    # ============================================
    # High-Level Wrapper Methods
    # ============================================
    async def request_history(self, name: str):
        entry = self.find_cmd(name)
        if not entry:
            print("Unknown CMD:", name)
            return
        print(f"➡️ Requesting history: {name} (cmd={entry.cmd})")
        await self.send_cmd(entry.cmd, b"")

    async def get_all_health_history(self):
        await self.request_history("Health_HistoryAll")

    async def get_sleep_history(self):
        await self.request_history("Health_HistorySleep")

    async def get_device_support_function(self):
        entry = self.find_cmd("GetDeviceSupportFunction")
        print(f"➡️ Requesting device support function (cmd={entry.cmd})")
        await self.send_cmd(entry.cmd, entry.payload)

    # ============================================
    # Parsers (placeholders)
    # ============================================
    def parse_1282(self, data):
        pass

    def parse_1284(self, data):
        pass

    def parse_1286(self, data):
        pass

    def parse_1288(self, data):
        pass

    def parse_1289(self, data):
        pass

    def parse_1327(self, data):
        pass

    def parse_1325(self, data):
        pass

    def parse_1331(self, data):
        pass

    def parse_1408(self, data):
        pass

    def parse_3445(self, data):
        pass

    def parse_1304(self, data):
        pass

    def parse_GetDeviceSupportFunction(self, data):
        payload = data[4:-2]
        flags = self.capabilities.decode(payload)

        print("Device Capabilities:")
        for k, v in flags.items():
            if v:
                print(f"  {k}: {v}")

    def parse_512_device_info(self, data: bytes):
        """
        Clean Python port of SmartHealth unpackDeviceInfoData()
        """

        # Strip BE94 wrapper: CMD(2), LEN(2), BODY, CRC(2)
        payload = data[4:-2]

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


    def parse_550_sleepStatus(self, data: bytes):
        """
        Parse GetSleepStatus (cmd 550) response.
        Mirrors app logic:
        - check error first
        - otherwise decode sleepStatus
        """

        # Strip BE94 wrapper: CMD(2), LEN(2), PAYLOAD, CRC(2)
        payload = data[4:-2]

        if not payload:
            print("😴 SleepStatus: empty payload")
            return

        # ---- Error check (exact port of isError) ----
        if (is_error_payload(payload) ):
            code = payload[0] & 0xFF
            msg = ERRORS.get(code, f"Unknown error ({code})")
            print(f"❌ GetSleepStatus error: {msg}")
            return

        # ---- Normal decode ----
        sleep_status = payload[0] & 0xFF
        print(f"😴 SleepStatus value: {sleep_status}")


# ================================================
# Example Usage
# ================================================
async def main():
    ring = SmartHealthR99("07:35:00:01:8A:EC", mtu=185)
    await ring.connect()

    # await ring.get_device_support_function()

    #await ring.send_cmd(ring.find_cmd("GetDeviceInfo"))

    #await ring.send_cmd(ring.find_cmd("GetDeviceSupportFunction"))

    await ring.send_cmd(550)

    await ring.send_cmd(ring.find_cmd("Health_HistorySleep"))
    await ring.send_cmd(ring.find_cmd("Health_HistoryAll"))

    print("📡 Requests sent, watching for notifications...")
    await asyncio.sleep(5)

    await ring.disconnect()

if __name__ == "__main__":
    asyncio.run(main())

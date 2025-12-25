import asyncio
from bleak import BleakClient
import binascii
import struct

# ====== YOUR RING'S BLE UUIDS ======
UUID_SERVICE_MAIN = "be940000-7333-be46-b7ae-689e71722bd5"
UUID_WRITE_MAIN   = "be940001-7333-be46-b7ae-689e71722bd5"
UUID_IND_MAIN     = "be940003-7333-be46-b7ae-689e71722bd5"

# ====== YOUR RING MAC ======
RING_MAC = "07:35:00:01:8A:EC"

# ====== SMARTHEALTH COMMAND IDS ======
CMD_SLEEP_HISTORY  = 0x0504  # 1284
CMD_HEART_HISTORY  = 0x0506  # 1286
CMD_SLEEP_STATUS   = 0x0226  # 550

# --------------------------
# CRC16 from YCBT ByteUtil
# --------------------------
def crc16_ycbt(data):
    s = 0xFFFF
    for b in data:
        x = ((s << 8) & 0xFF00) | ((s >> 8) & 0x00FF)
        x ^= b
        x ^= ((x & 0x00FF) >> 4)
        x = (x ^ (x << 12)) & 0xFFFF
        s = (x ^ ((x & 0x00FF) << 5)) & 0xFFFF
    return s

def build_packet(cmd, payload):
    length = len(payload) + 6
    out = bytearray()
    out.append((cmd >> 8) & 0xFF)   # high byte
    out.append(cmd & 0xFF)          # low byte
    out += struct.pack("<H", length)      # total length
    out += payload
    crc = crc16_ycbt(out)
    out += struct.pack("<H", crc)
    return bytes(out)

# ------------------------------------
# Mandatory handshake #1: getDeviceName
# ------------------------------------
def cmd_get_device_name():
    # payload = [0x47, 0x50]
    return build_packet(515, bytes([0x47, 0x50]))

# ------------------------------------
# Sleep Status / Sleep / HR history
# ------------------------------------
def cmd_sleep_status():
    return build_packet(0x0226, b"")

def cmd_sleep_history():
    return build_packet(0x0504, b"")

def cmd_hr_history():
    return build_packet(0x0506, b"")

# Print notifications nicely
def notification_handler(sender, data):
    print(f"[NOTIFY] {binascii.hexlify(data).decode()}")


async def main():
    print(f"🔗 Connecting to ring at {RING_MAC} …")

    async with BleakClient(RING_MAC, timeout=30) as client:
        print("✅ Connected")

        print("📡 Enabling notifications…")
        await client.start_notify(UUID_NOTIFY, notification_handler)

        # -------------------------------------
        # TRY HANDSHAKE #1 (safe)
        # -------------------------------------
        pkt = cmd_get_device_name()
        print(f"➡️ Sending getDeviceName: {pkt.hex()}")
        await client.write_gatt_char(UUID_WRITE, pkt)

        await asyncio.sleep(2)

        # -------------------------------------
        # Try sleep status (requires handshake)
        # -------------------------------------
        print("➡️ Sending Sleep Status…")
        await client.write_gatt_char(UUID_WRITE, cmd_sleep_status())

        await asyncio.sleep(2)

        print("➡️ Sending Sleep History…")
        await client.write_gatt_char(UUID_WRITE, cmd_sleep_history())

        await asyncio.sleep(2)

        print("➡️ Sending HR History…")
        await client.write_gatt_char(UUID_WRITE, cmd_hr_history())

        print("⏳ Waiting for notifications (20s)…")
        await asyncio.sleep(20)

        await client.stop_notify(UUID_NOTIFY)
        print("🛑 Stopping notifications")


asyncio.run(main())

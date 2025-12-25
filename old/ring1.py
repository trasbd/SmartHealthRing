import asyncio
from bleak import BleakClient

# ====== YOUR RING'S BLE UUIDS ======
UUID_SERVICE = "0000ae00-0000-1000-8000-00805f9b34fb"
UUID_WRITE   = "0000ae01-0000-1000-8000-00805f9b34fb"
UUID_NOTIFY  = "0000ae02-0000-1000-8000-00805f9b34fb"

# ====== YOUR RING MAC ======
RING_MAC = "07:35:00:01:8A:EC"

# ====== SMARTHEALTH COMMAND IDS ======
CMD_SLEEP_HISTORY  = 0x0504  # 1284
CMD_HEART_HISTORY  = 0x0506  # 1286
CMD_SLEEP_STATUS   = 0x0226  # 550

# ================================================================
# EXACT SMARTHEALTH CRC (copied 1:1 from ByteUtil.crc16_compute)
# ================================================================
def smarthealth_crc16(data: bytes) -> int:
    s = 0xFFFF
    for b in data:
        # byte swap
        s = ((s << 8) & 0xFF00) | ((s >> 8) & 0xFF)

        # xor with input byte
        s ^= (b & 0xFF)

        # xor with upper nibble
        s ^= ((s & 0xFF) >> 4)

        # xor with shifted versions
        s ^= (s << 12) & 0xFFFF
        s ^= ((s & 0xFF) << 5) & 0xFFFF

        s &= 0xFFFF
    return s

# ================================================================
# EXACT SMARTHEALTH FRAME BUILDER
# ================================================================
def build_sh_cmd(cmd: int, payload: bytes) -> bytes:
    total_len = len(payload) + 6  # header + payload + CRC
    frame = bytearray(total_len)

    # command ID
    frame[0] = (cmd >> 8) & 0xFF
    frame[1] = cmd & 0xFF

    # length
    frame[2] = total_len & 0xFF
    frame[3] = (total_len >> 8) & 0xFF

    # payload
    frame[4:4+len(payload)] = payload

    # CRC
    crc = smarthealth_crc16(frame[:4 + len(payload)])
    frame[4 + len(payload)] = crc & 0xFF
    frame[5 + len(payload)] = (crc >> 8) & 0xFF

    return bytes(frame)

# ================================================================
# NOTIFICATION HANDLER
# ================================================================
def notify_handler(sender, data: bytearray):
    hex_data = data.hex(" ")
    print(f"[NOTIFY] {hex_data}")


# ================================================================
# MAIN BLE LOGIC
# ================================================================
async def main():

    print(f"🔗 Connecting to ring at {RING_MAC} …")
    async with BleakClient(RING_MAC) as client:
        print("✅ Connected")

        # enable notifications
        await client.start_notify(UUID_NOTIFY, notify_handler)
        print("📡 Notifications enabled")

        # ============================================================
        # SEND COMMANDS EXACTLY AS SMARTHEALTH
        # ============================================================

        cmd = build_sh_cmd(0x0200, b"")
        print(f"➡️ Sending Get Info: {cmd.hex(' ')}")
        await client.write_gatt_char(UUID_WRITE, cmd)


        # 1. Sleep Status (like SmartHealth dashboard)
        cmd = build_sh_cmd(CMD_SLEEP_STATUS, b"")
        print(f"➡️ Sending Sleep Status: {cmd.hex(' ')}")
        await client.write_gatt_char(UUID_WRITE, cmd)

        await asyncio.sleep(2)

        # 2. Sleep History
        cmd = build_sh_cmd(CMD_SLEEP_HISTORY, b"")
        print(f"➡️ Sending Sleep History: {cmd.hex(' ')}")
        await client.write_gatt_char(UUID_WRITE, cmd)

        await asyncio.sleep(2)

        # 3. Heart Rate History
        cmd = build_sh_cmd(CMD_HEART_HISTORY, b"")
        print(f"➡️ Sending Heart History: {cmd.hex(' ')}")
        await client.write_gatt_char(UUID_WRITE, cmd)

        print("⏳ Waiting 20 seconds for notifications…")
        await asyncio.sleep(20)

        print("🛑 Stopping notifications")
        await client.stop_notify(UUID_NOTIFY)


# ================================================================
# RUN
# ================================================================
if __name__ == "__main__":
    asyncio.run(main())

def build_ycbt_frame(cmd: int, payload: bytes, crc16_func) -> bytes:
    payload_len = len(payload)
    total_len = payload_len + 6  # cmd(2) + len(2) + payload + crc(2)

    frame = bytearray(total_len)
    # command ID (big-endian)
    frame[0] = (cmd >> 8) & 0xFF
    frame[1] = cmd & 0xFF

    # total length (little-endian)
    frame[2] = total_len & 0xFF
    frame[3] = (total_len >> 8) & 0xFF

    # payload
    frame[4:4+payload_len] = payload

    # CRC over header+length+payload
    crc_input_len = 4 + payload_len
    crc = crc16_func(frame[:crc_input_len])  # must match ByteUtil.crc16_compute

    frame[crc_input_len] = crc & 0xFF        # CRC_L
    frame[crc_input_len + 1] = (crc >> 8) & 0xFF  # CRC_H

    return bytes(frame)
def build_sh_cmd(cmd: int, payload: bytes) -> bytes:
    # total length = payload + 6
    total_len = len(payload) + 6

    # header
    frame = bytearray(total_len)
    frame[0] = (cmd >> 8) & 0xFF
    frame[1] = cmd & 0xFF
    frame[2] = total_len & 0xFF
    frame[3] = (total_len >> 8) & 0xFF

    # payload
    frame[4:4 + len(payload)] = payload

    # compute CRC over header + payload
    crc_input = frame[:4 + len(payload)]
    crc = smarthealth_crc16(crc_input)

    frame[4 + len(payload)] = crc & 0xFF         # CRC_L
    frame[5 + len(payload)] = (crc >> 8) & 0xFF  # CRC_H

    return bytes(frame)

    # GetSleepStatus (no payload)
frame = build_ycbt_frame(0x0226, b"", crc16_func)

# Health_HistorySleep with some params
frame2 = build_ycbt_frame(0x0504, payload_bytes, crc16_func)

def smarthealth_crc16(data: bytes) -> int:
    s = 0xFFFF
    for b in data:
        # swap bytes of s like the Java code:
        s = ((s << 8) & 0xFF00) | ((s >> 8) & 0xFF)

        # XOR with byte
        s ^= (b & 0xFF)

        # XOR with upper nibble
        s ^= ((s & 0xFF) >> 4)

        # XOR with shifted values (same as Java)
        s ^= (s << 12) & 0xFFFF
        s ^= ((s & 0xFF) << 5) & 0xFFFF

        s &= 0xFFFF

    return s

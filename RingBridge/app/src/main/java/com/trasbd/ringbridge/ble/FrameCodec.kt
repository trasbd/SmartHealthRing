package com.trasbd.ringbridge.ble

object FrameCodec {
    fun decodeFrame(data: ByteArray): DecodedFrame {
        val group = data[0].toInt() and 0xFF
        val subtype = data[1].toInt() and 0xFF


        val totalLen = (data[2].toInt() and 0xFF) or ((data[3].toInt() and 0xFF) shl 8)

        val payloadLen = totalLen - 6
        val payload = data.copyOfRange(4, 4 + payloadLen)

        return DecodedFrame(group, subtype, payload)
    }

    fun buildBe94Frame(cmd: Int, payload: ByteArray): ByteArray {
        val totalLen = payload.size + 6
        val out = ByteArray(totalLen)

        out[0] = ((cmd shr 8) and 0xFF).toByte()
        out[1] = (cmd and 0xFF).toByte()
        out[2] = (totalLen and 0xFF).toByte()
        out[3] = ((totalLen shr 8) and 0xFF).toByte()

        // payload
        System.arraycopy(payload, 0, out, 4, payload.size)

        // CRC over header + payload
        val crc = crc16Ycbt(out.copyOfRange(0, 4 + payload.size))

        out[4 + payload.size] = (crc and 0xFF).toByte()
        out[5 + payload.size] = ((crc shr 8) and 0xFF).toByte()

        return out
    }

    private fun crc16Ycbt(data: ByteArray, seed: Int = 0xFFFF): Int {
        var s = seed and 0xFFFF

        for (b in data) {
            val byte = b.toInt() and 0xFF

            val swapped = ((s shl 8) and 0xFF00) or ((s ushr 8) and 0x00FF)
            var s2 = swapped xor byte

            s2 = s2 xor ((s2 and 0xFF) ushr 4)
            val s3 = s2 xor ((s2 shl 12) and 0xFFFF)

            s = s3 xor (((s3 and 0xFF) shl 5) and 0xFFFF)
            s = s and 0xFFFF
        }

        return s
    }

    @Suppress("unused")
    fun chunkForMtu(data: ByteArray, mtu: Int): List<ByteArray> {
        val usable = mtu - 3
        val chunks = mutableListOf<ByteArray>()

        var i = 0
        while (i < data.size) {
            val end = minOf(i + usable, data.size)
            chunks.add(data.copyOfRange(i, end))
            i += usable
        }

        return chunks
    }



}
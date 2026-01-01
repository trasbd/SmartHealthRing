package com.trasbd.ringbridge.ble

@Suppress("ArrayInDataClass")
data class DecodedFrame(
    val group: Int, val subtype: Int, val payload: ByteArray
)
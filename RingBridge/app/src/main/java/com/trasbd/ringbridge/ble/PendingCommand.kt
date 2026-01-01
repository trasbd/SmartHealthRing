package com.trasbd.ringbridge.ble

@Suppress("ArrayInDataClass")
data class PendingCommand(
    val cmd: Int,
    val group: Int,
    val subtype: Int,
    val payload: ByteArray = byteArrayOf()
)
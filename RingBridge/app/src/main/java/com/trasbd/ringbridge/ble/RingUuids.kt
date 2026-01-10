package com.trasbd.ringbridge.ble

import java.util.UUID

@Suppress("unused")
object RingUuids {
    val UUID_BE94_SERVICE: UUID = UUID.fromString("be940000-7333-be46-b7ae-689e71722bd5")

    val UUID_BE94_WRITE: UUID = UUID.fromString("be940001-7333-be46-b7ae-689e71722bd5")
    val UUID_BE94_WRITE2: UUID = UUID.fromString("be940002-7333-be46-b7ae-689e71722bd5")
    val UUID_IND_BE94_SECOND: UUID = UUID.fromString("be940003-7333-be46-b7ae-689e71722bd5")

    val UUID_NOTIFY_AE02: UUID = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb")
    val UUID_IND_FEA1: UUID = UUID.fromString("0000fea1-0000-1000-8000-00805f9b34fb")
    val UUID_IND_FEA2: UUID = UUID.fromString("0000fea2-0000-1000-8000-00805f9b34fb")

    val UUID_NOTIFY_NUS_TX: UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e")

    val NOTIFY_ALLOWLIST = setOf(
        UUID_BE94_WRITE,
        UUID_IND_BE94_SECOND,
        UUID_NOTIFY_AE02,
        UUID_IND_FEA2,
        UUID_NOTIFY_NUS_TX
    )
}
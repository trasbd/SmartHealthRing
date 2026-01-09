package com.trasbd.ringbridge.ui.model

import java.time.LocalDateTime

data class RingUiModel(
    val isConnected: Boolean,
    val isReady: Boolean,
    val batteryLevel: Int?,
    val chargeDateTime: LocalDateTime?,
    val onConnect: () -> Unit,
    val onRequestBattery: () -> Unit,
    val onRequestHealth: () -> Unit,
    val onRequestSleep: () -> Unit,
    val onLiveHRStart: () -> Unit,
    val onLiveHRStop: () -> Unit
)

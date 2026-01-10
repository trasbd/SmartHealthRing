package com.trasbd.ringbridge.healthconnect

class HealthConnectWriteException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
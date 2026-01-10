// com.trasbd.lib.utilities.CoroutineLogging.kt
package com.trasbd.lib.utilities

import com.trasbd.lib.ILogger
import kotlinx.coroutines.CoroutineExceptionHandler

fun loggingCoroutineExceptionHandler(
    logger: ILogger,
    tag: String = "CoroutineException"
): CoroutineExceptionHandler =
    CoroutineExceptionHandler { context, throwable ->
        logger.e(
            tag = tag,
            msg = buildString {
                appendLine("Context: $context")
                appendLine(throwable.stackTraceToString())
            }
        )
    }

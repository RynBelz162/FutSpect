package com.belzsoftware.futspect.util.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDateTimeString(format: String): String {
    val today = LocalDateTime.now()
    return today.toFormattedString(format)
}

fun LocalDateTime.toFormattedString(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}
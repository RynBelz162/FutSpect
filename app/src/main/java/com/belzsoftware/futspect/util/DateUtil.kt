package com.belzsoftware.futspect.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getCurrentDateTimeString(format: String): String {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val today = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(format)
        return today.format(formatter)
    }

    val simpleDateFormatter = SimpleDateFormat(format, Locale.US)
    return simpleDateFormatter.format(Date())
}
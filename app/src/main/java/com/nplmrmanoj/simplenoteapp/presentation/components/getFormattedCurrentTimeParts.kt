package com.nplmrmanoj.simplenoteapp.presentation.components

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedCurrentTimeParts(addedTime: Long): Pair<String, String> {
    val date = Date(addedTime)

    val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault()) // time only
    val amPmFormat = SimpleDateFormat("a", Locale.getDefault())     // AM or PM

    val time = timeFormat.format(date)
    val amPm = amPmFormat.format(date)

    return Pair(time, amPm)
}
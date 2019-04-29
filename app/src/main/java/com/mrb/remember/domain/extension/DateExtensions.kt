package com.mrb.remember.domain.extension

import com.mrb.remember.domain.model.Hour
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import java.util.Locale

const val ONE_DAY_MILLIS: Long = 86400000

fun Date.getDaysBetween(endDate: Date): Int = with(this) {
    val todayCalendar = Calendar.getInstance()
    todayCalendar.time = this
    todayCalendar.set(Calendar.HOUR, 0)
    todayCalendar.set(Calendar.MINUTE, 0)

    val different = endDate.time - this.time

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24

    val elapsedDays = different / daysInMilli

    return elapsedDays.toInt()
}

fun Hour.getCalendarForToday(): Calendar {
    val calendar = java.util.Calendar.getInstance()
    calendar.set(java.util.Calendar.HOUR_OF_DAY, this.hour)
    calendar.set(java.util.Calendar.MINUTE, this.minute)
    calendar.set(java.util.Calendar.SECOND, 0)
    return calendar
}

fun Date.getStringHour(): String {
    val df = SimpleDateFormat("HH:mm", Locale.getDefault())
    return df.format(this)
}
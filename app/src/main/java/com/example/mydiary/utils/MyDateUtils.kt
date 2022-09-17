package com.example.mydiary.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object MyDateUtils {
    fun getTimeThenDayThenMonth(date: Date?): String? {
        if (date == null) return null
        return if (isToday(date)) {
            return DateUtils.getRelativeTimeSpanString(date.time).toString()
        } else if (isCurrentMonth(date)) {
            "${getDateInString(date, "dd MMM")}\n${getDateInString(date, "hh:mm a")}"
        } else if (isCurrentYear(date)) {
            getDateInString(date, "dd MMM")
        } else {
            getDateInString(date, "MMM yyyy")
        }
    }

    fun isToday(date: Date?): Boolean {
        if (date == null) return false
        val givenCal = Calendar.getInstance().also { it.time = date }
        val nowCal = Calendar.getInstance().also { it.time = Date() }
        return nowCal.get(Calendar.YEAR) == givenCal.get(Calendar.YEAR)
                && nowCal.get(Calendar.MONTH) == givenCal.get(Calendar.MONTH)
                && nowCal.get(Calendar.DATE) == givenCal.get(Calendar.DATE)
    }

    fun isCurrentMonth(date: Date?): Boolean {
        if (date == null) return false
        val givenCal = Calendar.getInstance().also { it.time = date }
        val nowCal = Calendar.getInstance().also { it.time = Date() }
        return nowCal.get(Calendar.YEAR) == givenCal.get(Calendar.YEAR)
                && nowCal.get(Calendar.MONTH) == givenCal.get(Calendar.MONTH)
    }

    fun isCurrentYear(date: Date?): Boolean {
        if (date == null) return false
        val givenCal = Calendar.getInstance().also { it.time = date }
        val nowCal = Calendar.getInstance().also { it.time = Date() }
        return nowCal.get(Calendar.YEAR) == givenCal.get(Calendar.YEAR)
    }

    fun getDateInString(date: Date?, format: String): String? {
        return date?.let {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault(Locale.Category.FORMAT))
            dateFormat.format(it)
        }
    }
}
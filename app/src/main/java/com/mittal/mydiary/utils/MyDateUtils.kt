package com.mittal.mydiary.utils

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object MyDateUtils {

    private const val DISPLAY_DATE_TIME_FORMAT = "MM/dd/yy hh:mm:ss a"
    private const val DISPLAY_TIME_FORMAT = "hh:mm:ss a"
    private const val SIMPLE_DATE_FORMAT = "MM/dd/yyyy"
    private const val DISPLAY_DAY_FORMAT = "EEEE"
    private const val DATE_TIME_FORMAT = "MM/dd/yy hh:mm a"
    private const val LONG_DATE_TIME_FORMAT = "MM/dd/yy hh:mm:ss a"
    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    /**
     * Converts the specified date to a string formatted as MM/dd/yy HH:mm:ss a.
     * If the input is null, returns null.
     *
     * @param date: The date
     * @return the date, as a string, converted to standard date time format. Or null, if the input is null.
     */
    fun convertToDateTimeFormat(date: Date?): String? {
        return if (date == null) {
            null
        } else convertDateFormat(date, DISPLAY_DATE_TIME_FORMAT)
    }

    /**
     * Converts the specified date to a string formatted as MM/dd/yy HH:mm:ss a.
     * If the input is null, returns null.
     *
     * @param date: The date
     * @return the date, as a string, converted to standard date time format. Or null, if the input is null.
     */
    fun convertToLongDateTimeFormat(date: Date?): String? {
        return if (date == null) {
            null
        } else convertDateFormat(date, LONG_DATE_TIME_FORMAT, Locale.US)
    }

    /**
     * @param date:   The date to convert
     * @param format: The format to convert to
     * @param locale
     * @return
     */
    private fun convertDateFormat(date: Date?, format: String?, locale: Locale?): String? {
        if (date == null) {
            return null
        }
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(date)
    }

    /**
     * Converts the specified date to a string formatted as HH:mm:ss a.
     * If the input is null, returns null.
     *
     * @param date: The date
     * @return the date, as a string, converted to standard time format. Or null, if the input is null.
     */
    fun convertToTimeFormat(date: Date?): String? {
        return if (date == null) {
            null
        } else convertDateFormat(date, DISPLAY_TIME_FORMAT)
    }

    fun getCurrentLocalTime(): Date? {
        return Calendar.getInstance().time
    }

    fun getCurrentLocalTimeLess5Min(): Date? {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, -5)
        return cal.time
    }

    fun getCurrentTimeZone(): String? {
        return TimeZone.getDefault().id
    }

    /**
     * @param date:   The date to convert
     * @param format: The format to convert to
     * @return
     */
    private fun convertDateFormat(date: Date?, format: String?): String? {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(date)
    }

    fun stringDateToDate(StrDate: String?): Date? {
        var dateToReturn: Date? = null
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        try {
            dateToReturn = dateFormat.parse(StrDate) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateToReturn
    }

    fun formatGmtDateStringToLocalDate(
        dateString: String?,
        toTimeZone: String?,
        fromTimeZone: String?,
        format: String?
    ): String? {
        try {
            val fromZoneDateFormat = SimpleDateFormat(format, Locale.getDefault())
            fromZoneDateFormat.timeZone = TimeZone.getTimeZone(fromTimeZone)
            fromZoneDateFormat.isLenient = false
            val toZoneDateFormat = SimpleDateFormat(format, Locale.getDefault())
            toZoneDateFormat.isLenient = false
            toZoneDateFormat.timeZone = TimeZone.getTimeZone(toTimeZone)
            val timestamp = fromZoneDateFormat.parse(dateString)
            return toZoneDateFormat.format(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Parses the specified date string using the specified format and returns a Date object.
     */
    @Throws(ParseException::class)
    fun convertToDate(dateString: String?, format: String): Date? {
        if (com.mittal.mydiary.utils.StringUtils.isEmpty(dateString)) return null

        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        dateFormat.isLenient = false
        return dateFormat.parse(dateString!!)
    }

    /**
     * Parses the specified date string using the specified format and returns a Date object.
     */
    @Throws(ParseException::class)
    fun convertToGMTDate(dateString: String?, format: String?): Date? {
        if (com.mittal.mydiary.utils.StringUtils.isEmpty(dateString)) return null

        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        dateFormat.isLenient = false
        return dateFormat.parse(dateString!!)
    }

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

    private fun isCurrentMonth(date: Date?): Boolean {
        if (date == null) return false
        val givenCal = Calendar.getInstance().also { it.time = date }
        val nowCal = Calendar.getInstance().also { it.time = Date() }
        return nowCal.get(Calendar.YEAR) == givenCal.get(Calendar.YEAR)
                && nowCal.get(Calendar.MONTH) == givenCal.get(Calendar.MONTH)
    }

    private fun isCurrentYear(date: Date?): Boolean {
        if (date == null) return false
        val givenCal = Calendar.getInstance().also { it.time = date }
        val nowCal = Calendar.getInstance().also { it.time = Date() }
        return nowCal.get(Calendar.YEAR) == givenCal.get(Calendar.YEAR)
    }

    private fun getDateInString(date: Date?, format: String): String? {
        return date?.let {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault(Locale.Category.FORMAT))
            dateFormat.format(it)
        }
    }

    private fun convertTimeToDays(time: Long): Long {
        return time / (1000 * 60 * 60 * 24)
    }
}
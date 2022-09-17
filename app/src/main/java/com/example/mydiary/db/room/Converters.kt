package com.example.mydiary.db.room

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromDateToLong(date: Date) : Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(longDate: Long) : Date {
        return Date(longDate)
    }
}
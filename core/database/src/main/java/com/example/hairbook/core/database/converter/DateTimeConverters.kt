package com.example.hairbook.core.database.converter

import androidx.room.TypeConverter
import java.time.Instant

class DateTimeConverters {
    @TypeConverter
    fun fromInstant(value: Instant?): Long? = value?.toEpochMilli()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let(Instant::ofEpochMilli)
}

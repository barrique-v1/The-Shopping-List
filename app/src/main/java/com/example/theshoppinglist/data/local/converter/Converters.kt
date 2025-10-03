package com.example.theshoppinglist.data.local.converter

import androidx.room.TypeConverter
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.Unit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? {
        return value?.format(dateTimeFormatter)
    }

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it, dateTimeFormatter) }
    }

    @TypeConverter
    fun fromCategory(value: Category?): String? {
        return value?.name
    }

    @TypeConverter
    fun toCategory(value: String?): Category? {
        return value?.let { Category.valueOf(it) }
    }

    @TypeConverter
    fun fromUnit(value: Unit?): String? {
        return value?.name
    }

    @TypeConverter
    fun toUnit(value: String?): Unit? {
        return value?.let { Unit.valueOf(it) }
    }
}

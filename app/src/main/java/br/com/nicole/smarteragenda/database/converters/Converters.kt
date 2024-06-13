package br.com.nicole.smarteragenda.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class Converters {

    @TypeConverter
    fun fromDatetoLong (value: Date?): Long?{
        return value?.time
    }

    @TypeConverter
    fun fromLongtoDate(value: Long?): Date?{
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromLocalDatetoLong(value: LocalDateTime?): Long? {
        return value?.toEpochSecond(ZoneOffset.UTC)
    }

    @TypeConverter
    fun fromLongtoLocalDate(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }
    }

    @TypeConverter
    fun fromStringListtoString(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringtoStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }


}
package com.zaritcare.data.room

import androidx.room.TypeConverter
import com.zaritcare.utilities.images.Images
import java.time.LocalDate

class RoomConverters {
    @TypeConverter
    fun toBlob(value: ByteArray?): String? = Images.blobToBase64(value)
    @TypeConverter
    fun fromBlob(value: String?): ByteArray? = Images.base64ToBlob(value)
    @TypeConverter
    fun fromDate(date: LocalDate): Long {
        return date.toEpochDay()
    }
    @TypeConverter
    fun toDate(date: Long): LocalDate {
        return LocalDate.ofEpochDay(date)
    }
}
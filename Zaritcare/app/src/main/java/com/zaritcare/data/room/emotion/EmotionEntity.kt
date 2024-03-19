package com.zaritcare.data.room.emotion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotions")
data class EmotionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: String,
    @ColumnInfo(name = "name")
    val name: String
)

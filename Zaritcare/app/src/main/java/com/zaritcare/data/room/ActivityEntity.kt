package com.zaritcare.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "authorQuote")
    val authorQuote: String?,
    @ColumnInfo(name = "quote")
    val quote: String?,
    @ColumnInfo(name = "action")
    val action: String
)
package com.zaritcare.data.room.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zaritcare.models.Action

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "front_page", typeAffinity = ColumnInfo.BLOB)
    val frontPage: String,
    @ColumnInfo(name = "banner", typeAffinity = ColumnInfo.BLOB)
    val banner: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "quote_image", typeAffinity = ColumnInfo.BLOB)
    val quoteImage: String?,
    @ColumnInfo(name = "authorQuote")
    val authorQuote: String?,
    @ColumnInfo(name = "quote")
    val quote: String?,
    @ColumnInfo(name = "actions")
    val actions: List<Action>,
    @ColumnInfo(name = "action")
    val action: String
)
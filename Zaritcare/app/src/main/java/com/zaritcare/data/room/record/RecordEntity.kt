package com.zaritcare.data.room.record

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "activity")
    val activity: Int,
    @ColumnInfo(name = "realizationDate")
    val realizationDate: Long,
    @ColumnInfo(name = "answer")
    val answer: Int
)
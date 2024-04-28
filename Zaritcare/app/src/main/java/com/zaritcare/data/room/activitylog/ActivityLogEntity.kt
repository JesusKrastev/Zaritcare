package com.zaritcare.data.room.activitylog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zaritcare.models.Action
import java.time.LocalDate

@Entity(tableName = "activity_logs")
data class ActivityLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "activity")
    val activity: Int,
    @ColumnInfo(name = "date")
    val date: LocalDate,
    @ColumnInfo(name = "user")
    val user: String
)
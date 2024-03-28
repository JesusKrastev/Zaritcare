package com.zaritcare.data.room.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "minimum_value_indicator")
    val minimumValueIndicator: String,
    @ColumnInfo(name = "maximum_value_indicator")
    val maximumValueIndicator: String,
)

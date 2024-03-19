package com.zaritcare.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "question")
    val question: Int,
    @ColumnInfo(name = "answer")
    val answer: String,
    @ColumnInfo(name = "user")
    val user: Int
)
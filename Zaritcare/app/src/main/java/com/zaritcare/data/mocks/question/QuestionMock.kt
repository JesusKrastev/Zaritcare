package com.zaritcare.data.mocks.question

data class QuestionMock(
    val id: Int,
    val question: String,
    val category: String,
    val minimumValueIndicator: String,
    val maximumValueIndicator: String,
    val type: String
)
package com.zaritcare.models

data class Question(
    val id: Int,
    val question: String,
    val category: String,
    val minimumValueIndicator: String,
    val maximumValueIndicator: String,
    val type: String
)
package com.zaritcare.models

import java.time.LocalDate

data class Answer(
    val id: Int,
    val question: Int,
    val answer: String,
    val date: LocalDate,
    val category: Category,
    val type: String,
    val user: String
)

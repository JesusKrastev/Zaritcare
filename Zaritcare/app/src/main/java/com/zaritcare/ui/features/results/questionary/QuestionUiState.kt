package com.zaritcare.ui.features.results.questionary

import com.zaritcare.models.Category
import com.zaritcare.models.Question
import com.zaritcare.models.Type

data class QuestionUiState(
    val id: Int,
    val question: String,
    val category: Category,
    val type: Type,
    val range: ClosedFloatingPointRange<Float> = 0f..10f,
    val steps: Int = 9,
    val answer: String = "0",
    val minimumValueIndicator: String = "",
    val maximumValueIndicator: String = ""
)

fun Question.toQuestionUiState(): QuestionUiState =
    QuestionUiState(
        id = id,
        question = question,
        category = Category.valueOf(category),
        type = Type.valueOf(type),
        minimumValueIndicator = minimumValueIndicator,
        maximumValueIndicator = maximumValueIndicator
    )
package com.zaritcare.ui.features.results.questionary

import com.zaritcare.models.Answer
import com.zaritcare.models.Category
import com.zaritcare.models.Question
import com.zaritcare.models.Type
import java.time.LocalDate

data class QuestionUiState(
    val id: Int,
    val question: String,
    val category: Category,
    val type: Type,
    val range: ClosedFloatingPointRange<Float> = 0f..10f,
    val steps: Int = range.endInclusive.toInt() - 1,
    val answer: String = "0",
    val minimumValueIndicator: String = "",
    val maximumValueIndicator: String = ""
)

fun Question.toQuestionUiState(): QuestionUiState =
    QuestionUiState(
        id = id,
        question = question,
        category = Category.valueOf(category),
        range = Category.valueOf(category).range,
        type = Type.valueOf(type),
        minimumValueIndicator = minimumValueIndicator,
        maximumValueIndicator = maximumValueIndicator
    )

fun QuestionUiState.toAnswer(): Answer =
    Answer(
        id = 0,
        question = id,
        answer = answer,
        category = category.name,
        type = type.name,
        date = LocalDate.now(),
        user = 1
    )
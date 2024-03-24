package com.zaritcare.ui.features.questionaryform

import com.zaritcare.models.Question

data class QuestionUiState(
    val id: Int,
    val question: String,
    val category: String,
    val type: QuestionUiType,
    val range: ClosedFloatingPointRange<Float> = 0f..10f,
    val steps: Int = 9,
    val answer: String = "0",
    val minimumValueIndicator: String = "",
    val maximumValueIndicator: String = ""
) {
    enum class QuestionUiType {
        EMOTION,
        RANGE
    }
}

fun Question.toQuestionUiState(): QuestionUiState =
    QuestionUiState(
        id = id,
        question = question,
        category = category,
        type = when (type) {
            Question.QuestionType.EMOTION -> QuestionUiState.QuestionUiType.EMOTION
            Question.QuestionType.RANGE -> QuestionUiState.QuestionUiType.RANGE
        }
    )
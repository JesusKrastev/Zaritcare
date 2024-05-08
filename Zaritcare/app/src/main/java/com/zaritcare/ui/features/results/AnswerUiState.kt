package com.zaritcare.ui.features.results

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Answer
import com.zaritcare.models.Category
import com.zaritcare.models.Type
import com.zaritcare.ui.features.results.questionary.CategoryUiState
import com.zaritcare.ui.features.results.questionary.toCategoryUiState
import java.time.LocalDate

data class AnswerUiState(
    val id: Int = 0,
    val question: Int,
    val image: ImageBitmap? = null,
    val answer: String,
    val date: LocalDate = LocalDate.now(),
    val category: CategoryUiState,
    val type: Type = Type.RANGO,
    val user: String
)

fun Answer.toAnswerUiState(): AnswerUiState =
    AnswerUiState(
        id = id,
        question = question,
        answer = answer,
        date = date,
        category = category.toCategoryUiState(),
        type = Type.valueOf(type),
        user = user
    )
package com.zaritcare.ui.features.results

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Answer
import com.zaritcare.models.Category
import com.zaritcare.models.Type
import java.time.LocalDate

data class AnswerUiState(
    val id: Int = 0,
    val question: Int,
    val image: ImageBitmap? = null,
    val answer: String,
    val date: LocalDate = LocalDate.now(),
    val category: Category,
    val type: Type = Type.RANGO,
    val user: Int
)

fun Answer.toAnswerUiState(): AnswerUiState =
    AnswerUiState(
        id = id,
        question = question,
        answer = answer,
        date = date,
        category = Category.valueOf(category),
        type = Type.valueOf(type),
        user = user
    )
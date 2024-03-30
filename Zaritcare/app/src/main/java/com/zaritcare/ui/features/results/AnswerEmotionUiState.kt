package com.zaritcare.ui.features.results

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Category
import com.zaritcare.models.Emotion
import com.zaritcare.models.Type
import com.zaritcare.utilities.images.Images
import java.time.LocalDate

data class AnswerEmotionUiState(
    val id: Int,
    val name: String,
    val image: ImageBitmap,
    val date: LocalDate = LocalDate.now(),
    val category: Category = Category.BIENESTAR,
    val type: Type = Type.EMOCION,
    val user: Int = -1
)

fun Emotion.toAnswerEmotionUiState(): AnswerEmotionUiState =
    AnswerEmotionUiState(
        id = id,
        name = name,
        image = Images.base64ToBitmap(image)
    )
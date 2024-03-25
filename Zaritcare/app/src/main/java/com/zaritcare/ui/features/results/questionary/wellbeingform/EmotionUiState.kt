package com.zaritcare.ui.features.results.questionary.wellbeingform

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.data.room.emotion.EmotionEntity
import com.zaritcare.models.Emotion
import com.zaritcare.utilities.images.Images

data class EmotionUiState(
    val id: Int,
    val name: String,
    val image: ImageBitmap
)

fun EmotionUiState.toEmotion(): Emotion =
    Emotion(
        id = id,
        name = name,
        image = Images.bitmapToBase64(image)
    )

fun Emotion.toEmotionUiState(): EmotionUiState =
    EmotionUiState(
        id = id,
        name = name,
        image = Images.base64ToBitmap(image)
    )

fun EmotionUiState.toEmotionEntity(): EmotionEntity =
    EmotionEntity(
        id = id,
        name = name,
        image = Images.bitmapToBase64(image)
    )

fun EmotionEntity.toEmotionUiState(): EmotionUiState =
    EmotionUiState(
        id = id,
        name = name,
        image = Images.base64ToBitmap(image)
    )
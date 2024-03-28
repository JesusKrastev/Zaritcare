package com.zaritcare.ui.features.tips

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Advice
import com.zaritcare.utilities.images.Images

data class AdviceUiState(
    val id: Int,
    val image: ImageBitmap,
    val title: String,
    val description: String
)

fun Advice.toAdviceUiState() = AdviceUiState(
    id = id,
    image = Images.base64ToBitmap(image),
    title = title,
    description = description
)
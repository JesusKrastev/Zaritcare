package com.zaritcare.ui.features.activities

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Activity
import com.zaritcare.utilities.images.Images

data class ActivityCoverUiState(
    val id: Int,
    val title: String,
    val image: ImageBitmap,
    val isCompletedToday: Boolean = false,
    val authorQuote: String?,
    val quote: String?
)

fun Activity.toActivityCoverUiState(): ActivityCoverUiState =
    ActivityCoverUiState(
        id = id,
        title = title,
        image = Images.base64ToBitmap(frontPage),
        authorQuote = authorQuote,
        quote = quote
    )
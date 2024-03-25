package com.zaritcare.ui.features.activities.introactivity

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Activity
import com.zaritcare.utilities.images.Images

data class ActivityQuoteUiState(
    val id: Int = -1,
    val title: String = "",
    val quoteImage: ImageBitmap = ImageBitmap(1, 1),
    val quote: String = "",
    val authorQuote: String = ""
)

fun Activity.toActivityQuoteUiState(): ActivityQuoteUiState =
    ActivityQuoteUiState(
        id = id,
        title = title,
        quoteImage = Images.base64ToBitmap(quoteImage!!),
        quote = quote!!,
        authorQuote = authorQuote!!
    )
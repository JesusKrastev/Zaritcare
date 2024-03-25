package com.zaritcare.ui.features.activities.activity

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Action
import com.zaritcare.models.Activity
import com.zaritcare.utilities.images.Images

data class ActivityUiState(
    val id: Int = -1,
    val frontPage: ImageBitmap = ImageBitmap(1, 1),
    val title: String = "",
    val description: String = "",
    val action: String = "",
    val actions: List<Action> = emptyList()
)

fun Activity.toActivityUiState(): ActivityUiState =
    ActivityUiState(
        id = id,
        frontPage = Images.base64ToBitmap(frontPage),
        title = title,
        description = description,
        action = action,
        actions = actions
    )
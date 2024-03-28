package com.zaritcare.ui.features.splashscreen

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter

@Immutable
data class ScreenInfoUiState(
    val title: String,
    val text: String,
    val image: Painter
)
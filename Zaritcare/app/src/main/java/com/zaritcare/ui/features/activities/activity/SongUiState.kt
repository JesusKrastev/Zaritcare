package com.zaritcare.ui.features.activities.activity

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Song
import com.zaritcare.utilities.images.Images

data class SongUiState(
    val id: Int,
    val image: ImageBitmap,
    val audio: Int,
    val state: SongState = SongState.STOP
) {
    enum class SongState {
        PLAYING, PAUSE, STOP
    }
}

fun Song.toSongUiState(): SongUiState =
    SongUiState(
        id = id,
        image = Images.base64ToBitmap(image),
        audio = audio
    )
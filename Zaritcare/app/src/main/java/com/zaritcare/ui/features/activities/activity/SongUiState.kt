package com.zaritcare.ui.features.activities.activity

import androidx.compose.ui.graphics.ImageBitmap
import com.zaritcare.models.Song
import com.zaritcare.utilities.images.Images

data class SongUiState(
    val id: Int,
    val image: ImageBitmap,
    val audio: Int,
    var state: State = State.STOPPED
) {
    enum class State {
        PLAYING,
        PAUSED,
        STOPPED
    }
}

fun Song.toSongUiState(): SongUiState =
    SongUiState(
        id = id,
        image = Images.base64ToBitmap(image),
        audio = audio
    )
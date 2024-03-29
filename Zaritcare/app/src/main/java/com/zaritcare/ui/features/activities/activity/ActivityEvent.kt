package com.zaritcare.ui.features.activities.activity

sealed interface ActivityEvent {
    data class OnClickSong(val song: SongUiState): ActivityEvent
}
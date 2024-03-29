package com.zaritcare.ui.features.activities.activity

sealed interface ActivityEvent {
    data class OnClickSong(val song: SongUiState): ActivityEvent
    data class OnClickFinished(val onNavigateToActivities: () -> Unit): ActivityEvent
    data object OnFinishTime: ActivityEvent
}
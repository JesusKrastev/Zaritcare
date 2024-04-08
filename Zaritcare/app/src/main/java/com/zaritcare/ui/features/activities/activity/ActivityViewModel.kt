package com.zaritcare.ui.features.activities.activity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.R
import com.zaritcare.data.ActivityLogRepository
import com.zaritcare.data.ActivityRepository
import com.zaritcare.data.SongRepository
import com.zaritcare.models.Activity
import com.zaritcare.ui.features.activities.AudioPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val songRepository: SongRepository,
    private val songsAudioPlayer: AudioPlayer,
    private val soundsAudioPlayer: AudioPlayer,
    private val activityLogRepository: ActivityLogRepository
) : ViewModel() {
    class ActivityViewModelException(message: String) : Exception(message)

    var activityState: ActivityUiState by mutableStateOf(ActivityUiState())
        private set
    var songsState: List<SongUiState> by mutableStateOf(emptyList())
        private set
    var playingSongState: SongUiState? by mutableStateOf(null)
        private set

    private suspend fun getSongs(): List<SongUiState> =
        songRepository.get().map { it.toSongUiState() }

    fun loadSongs() {
        viewModelScope.launch {
            try {
                songsState = getSongs()
            } catch (e: Exception) {
                Log.d("ActivityViewModel", "Error loading songs: ${e.message}")
            }
        }
    }

    init {
        loadSongs()
    }

    fun setActivityState(activityId: Int) {
        viewModelScope.launch {
            val activity: Activity = activityRepository.get(activityId)
                ?: throw ActivityViewModelException("Activity not found with id $activityId")
            activityState = activity.toActivityUiState()
        }
    }

    fun clearActivityState() {
        songsAudioPlayer.stop()
        soundsAudioPlayer.stop()
        playingSongState = null
    }

    fun onActivityEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.OnClickSong -> {
                val clickedSong: SongUiState = event.song

                if (playingSongState?.id != clickedSong.id) {
                    playingSongState = clickedSong.copy(state = SongUiState.State.PLAYING)
                    songsAudioPlayer.stop()
                    songsAudioPlayer.play(clickedSong.audio)
                } else {
                    if(playingSongState?.state == SongUiState.State.PLAYING) {
                        playingSongState = playingSongState!!.copy(state = SongUiState.State.PAUSED)
                        songsAudioPlayer.pause()
                    } else {
                        playingSongState = playingSongState!!.copy(state = SongUiState.State.PLAYING)
                        songsAudioPlayer.play(playingSongState!!.audio)
                    }
                }
            }
            is ActivityEvent.OnClickFinished -> {
                viewModelScope.launch {
                    activityLogRepository.insert(activityState.toActivityLog())
                }
                event.onNavigateToActivities()
            }
            is ActivityEvent.OnFinishTime -> {
                clearActivityState()
                soundsAudioPlayer.play(R.raw.finish_time_audio)
            }
        }
    }
}
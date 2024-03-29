package com.zaritcare.ui.features.activities.activity

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
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
): ViewModel() {
    class ActivityViewModelException(message: String) : Exception(message)

    var activityState: ActivityUiState by mutableStateOf(ActivityUiState())
        private set
    var songsState: List<SongUiState> by  mutableStateOf(emptyList())
        private set

    private suspend fun getSongs(): List<SongUiState> = songRepository.get().map { it.toSongUiState() }

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
            val activity: Activity = activityRepository.get(activityId) ?: throw ActivityViewModelException("Activity not found with id $activityId")
            activityState = activity.toActivityUiState()
        }
    }

    private fun updateSongState(clickedSong: SongUiState, newState: SongUiState.SongState) {
        val mutableSongsState: MutableList<SongUiState> = songsState.toMutableList()
        val index = mutableSongsState.indexOfFirst { it.id == clickedSong.id }
        if (index != -1) {
            val updatedSong: SongUiState = mutableSongsState[index].copy(state = newState)
            mutableSongsState[index] = updatedSong
            songsState = mutableSongsState
        }
    }

    private fun onClickSong(clickedSong: SongUiState) {
        when(clickedSong.state) {
            SongUiState.SongState.STOP -> {
                songsAudioPlayer.stop() // If other music is playing, need to stop it
                songsAudioPlayer.play(clickedSong.audio)
                songsState = songsState.map { song ->
                    if (song.id == clickedSong.id) {
                        song.copy(state = SongUiState.SongState.PLAYING)
                    } else {
                        song.copy(state = SongUiState.SongState.STOP)
                    }
                }
            }
            SongUiState.SongState.PLAYING -> {
                songsAudioPlayer.pause()
                updateSongState(clickedSong, SongUiState.SongState.PAUSE)
            }
            SongUiState.SongState.PAUSE -> {
                songsAudioPlayer.play(clickedSong.audio)
                updateSongState(clickedSong, SongUiState.SongState.PLAYING)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        songsAudioPlayer.stop()
    }

    fun onActivityEvent(event: ActivityEvent) {
        when(event) {
            is ActivityEvent.OnClickSong -> {
                onClickSong(event.song)
            }
            is ActivityEvent.OnFinishTime -> {
                soundsAudioPlayer.stop()
                soundsAudioPlayer.play(R.raw.finish_time_audio)
            }
            is ActivityEvent.OnClickFinished -> {
                viewModelScope.launch {
                    activityLogRepository.insert(activityState.toActivityLog())
                }
                event.onNavigateToActivities()
            }
        }
    }
}
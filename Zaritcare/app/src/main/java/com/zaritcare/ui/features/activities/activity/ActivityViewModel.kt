package com.zaritcare.ui.features.activities.activity

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
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
    application: Application
): AndroidViewModel(application) {
    class ActivityViewModelException(message: String) : Exception(message)

    var activityState: ActivityUiState by mutableStateOf(ActivityUiState())
        private set
    var songsState: List<SongUiState> by  mutableStateOf(emptyList())
        private set
    private val context = application.applicationContext
    private val audioPlayer: AudioPlayer = AudioPlayer(context)

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
                audioPlayer.stop() // If other music is playing, need to stop it
                audioPlayer.play(clickedSong.audio)
                songsState = songsState.map { song ->
                    if (song.id == clickedSong.id) {
                        song.copy(state = SongUiState.SongState.PLAYING)
                    } else {
                        song.copy(state = SongUiState.SongState.STOP)
                    }
                }
            }
            SongUiState.SongState.PLAYING -> {
                audioPlayer.pause()
                updateSongState(clickedSong, SongUiState.SongState.PAUSE)
            }
            SongUiState.SongState.PAUSE -> {
                audioPlayer.play(clickedSong.audio)
                updateSongState(clickedSong, SongUiState.SongState.PLAYING)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.stop()
    }

    fun onActivityEvent(event: ActivityEvent) {
        when(event) {
            is ActivityEvent.OnClickSong -> {
                onClickSong(event.song)
            }
        }
    }
}
package com.zaritcare.ui.features.activities

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
): ViewModel() {
    var activitiesState: List<ActivityCoverUiState> by mutableStateOf(emptyList())

    suspend fun getActivities() = activityRepository.get().map { activity -> activity.toActivityCoverUiState() }

    fun loadActivities() {
        viewModelScope.launch {
            try {
                activitiesState = getActivities()
                Log.d("ActivitiesViewModel", "Activities loaded ${activitiesState.size}")
            } catch (e: Exception) {
                Log.d("ActivitiesViewModel", "Error loading activities", e)
            }
        }
    }

    init {
        loadActivities()
    }
}
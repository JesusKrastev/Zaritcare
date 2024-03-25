package com.zaritcare.ui.features.activities.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.ActivityRepository
import com.zaritcare.models.Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
): ViewModel() {
    class ActivityViewModelException(message: String) : Exception(message)

    var activityState: ActivityUiState by mutableStateOf(ActivityUiState())
        private set

    fun setActivityState(activityId: Int) {
        viewModelScope.launch {
            val activity: Activity = activityRepository.get(activityId) ?: throw ActivityViewModelException("Activity not found with id $activityId")
            activityState = activity.toActivityUiState()
        }
    }
}
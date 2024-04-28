package com.zaritcare.ui.features.activities

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.ActivityLogRepository
import com.zaritcare.data.ActivityRepository
import com.zaritcare.data.services.authentication.AuthServiceImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val activityLogRepository: ActivityLogRepository,
    private val authService: AuthServiceImplementation
): ViewModel() {
    var activitiesState: List<ActivityCoverUiState> by mutableStateOf(emptyList())
    private var user: String by mutableStateOf("")

    suspend fun getActivities() = activityRepository.get().map { activity -> activity.toActivityCoverUiState() }
    fun getCompletedActivities() = activityLogRepository.get(LocalDate.now(), user)

    fun loadActivities() {
        viewModelScope.launch {
            try {
                activitiesState = getActivities()
                getCompletedActivities().collect  {
                    val completedActivities: List<Int> = it.map { activityLog -> activityLog.activity }
                    activitiesState = activitiesState.map { activity ->
                        activity.copy(isCompletedToday = completedActivities.contains(activity.id))
                    }
                }
            } catch (e: Exception) {
                Log.d("ActivitiesViewModel", "Error loading activities", e)
            }
        }
    }

    fun loadUser() {
        runBlocking {
            user = authService.getCurrentUser()?.uid ?: ""
        }
    }

    init {
        loadUser()
        loadActivities()
    }
}
package com.kinoyamboladmin.ui.features.settings

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.SettingsRepository
import com.zaritcare.models.SettingsValues
import com.zaritcare.ui.features.settings.SettingsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    var themes by mutableStateOf(SettingsValues.themes)
    var userSettingsState: SettingsUiState? by mutableStateOf(SettingsUiState())

    private fun loadUserSettings() {
        viewModelScope.launch {
            settingsRepository.settingsFlow.collect { settings ->
                userSettingsState = SettingsUiState(
                    theme = settings.theme
                )
            }
        }
    }

    init {
        loadUserSettings()
    }

    fun onSettingsEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.OnChangeTheme -> {
                viewModelScope.launch {
                    settingsRepository.saveTheme(event.theme)
                }
            }
            is SettingsEvent.OnClickPrivacyPolicies -> {

            }
            is SettingsEvent.OnClickTermsAndConditions -> {

            }
        }
    }
}
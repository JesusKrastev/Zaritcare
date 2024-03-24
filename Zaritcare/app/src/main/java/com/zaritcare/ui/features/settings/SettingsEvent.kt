package com.zaritcare.ui.features.settings

sealed interface SettingsEvent {
    data class OnChangeTheme(val theme: String): SettingsEvent
    data object OnClickPrivacyPolicies : SettingsEvent
    data object OnClickTermsAndConditions : SettingsEvent
}
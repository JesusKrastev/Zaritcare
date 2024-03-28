package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel
import com.zaritcare.ui.features.settings.SettingsScreen

const val SettingsGraphRoute = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(SettingsGraphRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen(
    vm : SettingsViewModel
) {
    composable(
        route = SettingsGraphRoute,
        arguments = emptyList()
    ) {
        SettingsScreen(
            themes = vm.themes,
            settingsUiState = vm.userSettingsState!!,
            onSettingsEvent = vm::onSettingsEvent
        )
    }
}
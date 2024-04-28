package com.zaritcare.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.activities.ActivitiesScreen
import com.zaritcare.ui.features.activities.ActivitiesViewModel
import com.zaritcare.ui.features.login.LoginScreen

const val ActivitiesGraphRoute = "activities"

fun NavController.navigateToActivities(navOptions: NavOptions? = null) {
    this.navigate(ActivitiesGraphRoute, navOptions)
}

fun NavGraphBuilder.activitiesScreen(
    onNavigateToIntroActivity: (Int) -> Unit,
    onNavigateToActivity: (Int) -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    composable(
        route = ActivitiesGraphRoute,
        arguments = emptyList()
    ) {
        val vm: ActivitiesViewModel = hiltViewModel<ActivitiesViewModel>()

        ActivitiesScreen(
            activities = vm.activitiesState,
            onNavigateToIntroActivity = onNavigateToIntroActivity,
            onNavigateToActivity = onNavigateToActivity,
            onNavigateToResults = onNavigateToResults,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToTips = onNavigateToTips
        )
    }
}
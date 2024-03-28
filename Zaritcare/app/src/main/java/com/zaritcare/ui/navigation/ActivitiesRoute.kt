package com.zaritcare.ui.navigation

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
    vm: ActivitiesViewModel,
    onNavigateToIntroActivity: (Int) -> Unit,
    onNavigateToActivity: (Int) -> Unit
) {
    composable(
        route = ActivitiesGraphRoute,
        arguments = emptyList()
    ) {
        ActivitiesScreen(
            activities = vm.activitiesState,
            onNavigateToIntroActivity = onNavigateToIntroActivity,
            onNavigateToActivity = onNavigateToActivity
        )
    }
}
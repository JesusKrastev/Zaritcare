package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zaritcare.ui.features.activities.activity.ActivityScreen
import com.zaritcare.ui.features.activities.activity.ActivityViewModel


private const val ActivityGraphRoute = "activity"
private const val ActivityParameterName = "activityId"

fun NavController.navigateToActivity(
    activityId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate("$ActivityGraphRoute/$activityId", navOptions)
}

fun NavGraphBuilder.activityScreen(
    vm: ActivityViewModel,
    onNavigateToActivities: () -> Unit
) {
    composable(
        route = "$ActivityGraphRoute/{$ActivityParameterName}",
        arguments = listOf(
            navArgument(ActivityParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val activityId: Int? = backStackEntry.arguments?.getInt(ActivityParameterName, -1)
        if (activityId != null
            && activityId != -1
        ) {
            vm.setActivityState(activityId!!)
        }

        ActivityScreen(
            activity = vm.activityState,
            onNavigateToActivities = onNavigateToActivities,
            onActivityEvent = vm::onActivityEvent,
            songs = vm.songsState,
            playingSong = vm.playingSongState,
            clearActivityState = vm::clearActivityState
        )
    }
}
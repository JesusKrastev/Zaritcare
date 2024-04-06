package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zaritcare.ui.features.activities.introactivity.IntroActivityScreen
import com.zaritcare.ui.features.activities.introactivity.IntroActivityViewModel

private const val IntroActivityGraphRoute = "intro_activity"
private const val IntroActivityParameterName = "activityId"

fun NavController.navigateToIntroActivity(
    activityId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate("$IntroActivityGraphRoute/$activityId", navOptions)
}

fun NavGraphBuilder.introActivityScreen(
    vm : IntroActivityViewModel,
    onNavigateToActivity: (Int) -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    composable(
        route = "$IntroActivityGraphRoute/{$IntroActivityParameterName}",
        arguments = listOf(
            navArgument(IntroActivityParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val activityId :Int? = backStackEntry.arguments?.getInt(IntroActivityParameterName, -1)
        if (activityId != null
            && activityId != -1) {
            vm.setActivityQuoteState(activityId!!)
        }

        IntroActivityScreen(
            activityQuote = vm.activityQuoteState,
            onNavigateToActivity = onNavigateToActivity,
            onNavigateToResults = onNavigateToResults,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToTips = onNavigateToTips
        )
    }
}
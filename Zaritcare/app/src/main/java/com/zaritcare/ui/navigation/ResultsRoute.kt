package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.results.ResultsScreen

const val ResultsGraphRoute = "results"

fun NavController.navigateToResults(navOptions: NavOptions? = null) {
    this.navigate(ResultsGraphRoute, navOptions)
}

fun NavGraphBuilder.resultsScreen(
    onNavigateToQuestionary: () -> Unit,
    onNavigateToForms: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToTips: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    composable(
        route = ResultsGraphRoute,
        arguments = emptyList()
    ) {
        ResultsScreen(
            onClickStart = onNavigateToQuestionary,
            onNavigateToForms = onNavigateToForms,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToTips = onNavigateToTips,
            onNavigateToSettings = onNavigateToSettings
        )
    }
}
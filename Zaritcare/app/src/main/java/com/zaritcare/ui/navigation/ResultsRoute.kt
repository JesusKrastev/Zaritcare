package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.results.ResultsScreen
import com.zaritcare.ui.features.results.ResultsViewModel

const val ResultsGraphRoute = "results"

fun NavController.navigateToResults(navOptions: NavOptions? = null) {
    this.navigate(ResultsGraphRoute, navOptions)
}

fun NavGraphBuilder.resultsScreen(
    vm: ResultsViewModel,
    onNavigateToQuestionary: () -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    composable(
        route = ResultsGraphRoute,
        arguments = emptyList()
    ) {
        ResultsScreen(
            answersByCategory = vm.answersByCategory,
            onClickStart = onNavigateToQuestionary,
            onNavigateToResults = onNavigateToResults,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToTips = onNavigateToTips
        )
    }
}
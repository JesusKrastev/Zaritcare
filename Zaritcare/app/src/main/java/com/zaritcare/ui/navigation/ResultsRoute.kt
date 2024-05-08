package com.zaritcare.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
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
        val vm: ResultsViewModel = hiltViewModel<ResultsViewModel>()

        ResultsScreen(
            answersByCategory = vm.answersByCategory,
            onClickStart = onNavigateToQuestionary,
            onNavigateToResults = onNavigateToResults,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToTips = onNavigateToTips,
            informationState = vm.informationState,
            onResultsEvent = vm::onResultsEvent
        )
    }
}
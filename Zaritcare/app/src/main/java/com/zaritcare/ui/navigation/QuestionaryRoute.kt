package com.zaritcare.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.results.questionary.QuestionaryScreen
import com.zaritcare.ui.features.results.questionary.QuestionaryViewModel

const val QuestionaryGraphRoute = "questionary"

fun NavController.navigateToQuestionary(navOptions: NavOptions? = null) {
    this.navigate(QuestionaryGraphRoute, navOptions)
}

fun NavGraphBuilder.questionaryScreen(
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    composable(
        route = QuestionaryGraphRoute,
        arguments = emptyList()
    ) {
        val vm: QuestionaryViewModel = hiltViewModel<QuestionaryViewModel>()

        QuestionaryScreen(
            selectedIndex = vm.selectedTab,
            onQuestionaryEvent = vm::onQuestionaryEvent,
            questions = vm.questionsByCategoryState,
            emotions = vm.emotionsState,
            categories = vm.categoriesState,
            onNavigateToResults = onNavigateToResults,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToTips = onNavigateToTips
        )
    }
}
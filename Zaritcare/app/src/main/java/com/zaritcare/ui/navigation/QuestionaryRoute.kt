package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.questionary.QuestionaryScreen
import com.zaritcare.ui.features.questionary.QuestionaryViewModel

const val QuestionaryGraphRoute = "questionary"

fun NavController.navigateToQuestionary(navOptions: NavOptions? = null) {
    this.navigate(QuestionaryGraphRoute, navOptions)
}

fun NavGraphBuilder.questionaryScreen(
    vm: QuestionaryViewModel,
    onNavigateToForms: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToTips: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    composable(
        route = QuestionaryGraphRoute,
        arguments = emptyList()
    ) {
        QuestionaryScreen(
            selectedIndex = vm.selectedTab,
            onQuestionaryEvent = vm::onQuestionaryEvent,
            questions = vm.questionsState,
            emotions = vm.emotionsState,
            categories = vm.categoriesState,
            onNavigateToForms = onNavigateToForms,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToTips = onNavigateToTips,
            onNavigateToSettings = onNavigateToSettings
        )
    }
}
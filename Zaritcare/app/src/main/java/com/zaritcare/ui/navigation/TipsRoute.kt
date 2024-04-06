package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.tips.TipsScreen
import com.zaritcare.ui.features.tips.TipsViewModel

const val TipsGraphRoute = "tips"

fun NavController.navigateToTips(navOptions: NavOptions? = null) {
    this.navigate(TipsGraphRoute, navOptions)
}

fun NavGraphBuilder.tipsScreen(
    vm: TipsViewModel,
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    composable(
        route = TipsGraphRoute,
        arguments = emptyList()
    ) {
        TipsScreen(
            tipsState = vm.tipsState,
            onNavigateToResults = onNavigateToResults,
            onNavigateToActivities = onNavigateToActivities,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToTips = onNavigateToTips
        )
    }
}
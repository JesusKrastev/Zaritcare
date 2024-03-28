package com.zaritcare.ui.features

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zaritcare.ui.features.components.ZaritcareNavBar
import com.zaritcare.ui.navigation.ZaritcareNavHost
import com.zaritcare.ui.navigation.navigateToActivities
import com.zaritcare.ui.navigation.navigateToResults
import com.zaritcare.ui.navigation.navigateToSettings
import com.zaritcare.ui.navigation.navigateToTips

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            ZaritcareNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues = paddingValues)
            )
        },
        bottomBar = {
            ZaritcareNavBar(
                onNavigateToForms = { navController.navigateToResults()},
                onNavigateToActivities = { navController.navigateToActivities() },
                onNavigateToTips = { navController.navigateToTips() },
                onNavigateToSettings = { navController.navigateToSettings() }
            )
        },
    )
}
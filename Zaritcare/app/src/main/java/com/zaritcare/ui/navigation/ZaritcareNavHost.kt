package com.zaritcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel
import com.zaritcare.ui.features.questionary.QuestionaryViewModel

@Composable
fun ZaritcareNavHost() {
    val navController: NavHostController = rememberNavController()
    val questionaryVm = hiltViewModel<QuestionaryViewModel>()
    val settingsVm = hiltViewModel<SettingsViewModel>()

    NavHost(
        navController = navController,
        startDestination = LoginGraphRoute
    ) {
        loginScreen(
            onClickLogin = {
                navController.navigateToResults()
            },
            onClickRegister = {
                navController.navigateToRegister()
            }
        )
        registerScreen(
            onRegisterClick = {
                navController.navigateToSplash()
            },
            onLoginClick = {
                navController.navigateToLogin()
            }
        )
        splashScreen(
            onClickStart = {
                navController.navigateToResults()
            }
        )
        questionaryScreen(
            vm = questionaryVm,
            onNavigateToForms = {
                navController.navigateToResults()
            },
            onNavigateToActivities = {

            },
            onNavigateToTips = {

            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            }
        )
        resultsScreen(
            onNavigateToQuestionary = {
                navController.navigateToQuestionary()
            },
            onNavigateToForms = {

            },
            onNavigateToActivities = {

            },
            onNavigateToTips = {

            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            }
        )
        settingsScreen(
            onNavigateToForms = {
                navController.navigateToResults()
            },
            onNavigateToActivities = {

            },
            onNavigateToTips = {

            },
            onNavigateToSettings = { },
            vm = settingsVm
        )
    }
}
package com.zaritcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel
import com.zaritcare.ui.features.questionary.QuestionaryViewModel

@Composable
fun ZaritcareNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val questionaryVm: QuestionaryViewModel = hiltViewModel<QuestionaryViewModel>()
    val settingsVm: SettingsViewModel = hiltViewModel<SettingsViewModel>()

    NavHost(
        modifier = modifier,
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
            vm = questionaryVm
        )
        resultsScreen(
            onNavigateToQuestionary = {
                navController.navigateToQuestionary()
            }
        )
        settingsScreen(
            vm = settingsVm
        )
    }
}
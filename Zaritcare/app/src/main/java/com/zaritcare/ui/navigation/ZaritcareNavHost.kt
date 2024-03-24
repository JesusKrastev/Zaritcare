package com.zaritcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zaritcare.ui.features.questionary.QuestionaryViewModel

@Composable
fun ZaritcareNavHost() {
    val navController: NavHostController = rememberNavController()
      val questionaryVm= hiltViewModel<QuestionaryViewModel>()

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

            },
            onNavigateToActivities = {

            },
            onNavigateToTips = {

            },
            onNavigateToSettings = {

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

            }
        )
    }
}
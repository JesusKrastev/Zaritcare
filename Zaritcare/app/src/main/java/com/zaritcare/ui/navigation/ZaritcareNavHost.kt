package com.zaritcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel
import com.zaritcare.ui.features.activities.ActivitiesViewModel
import com.zaritcare.ui.features.activities.activity.ActivityViewModel
import com.zaritcare.ui.features.activities.introactivity.IntroActivityViewModel
import com.zaritcare.ui.features.results.questionary.QuestionaryViewModel
import com.zaritcare.ui.features.tips.TipsViewModel

@Composable
fun ZaritcareNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val questionaryVm: QuestionaryViewModel = hiltViewModel<QuestionaryViewModel>()
    val settingsVm: SettingsViewModel = hiltViewModel<SettingsViewModel>()
    val tipsVm: TipsViewModel = hiltViewModel<TipsViewModel>()
    val activitiesVm: ActivitiesViewModel = hiltViewModel<ActivitiesViewModel>()
    val introActivitiesVm: IntroActivityViewModel = hiltViewModel<IntroActivityViewModel>()
    val activityVm: ActivityViewModel = hiltViewModel<ActivityViewModel>()

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
        tipsScreen(
            vm = tipsVm
        )
        activitiesScreen(
            vm = activitiesVm,
            onNavigateToActivity = {
                navController.navigateToActivity(it)
            },
            onNavigateToIntroActivity = {
                navController.navigateToIntroActivity(it)
            }
        )
        introActivityScreen(
            vm = introActivitiesVm,
            onNavigateToActivity = {
                navController.navigateToActivity(it)
            }
        )
        activityScreen(
            vm = activityVm,
            onNavigateToActivities = {
                navController.navigateToActivities()
            }
        )
    }
}
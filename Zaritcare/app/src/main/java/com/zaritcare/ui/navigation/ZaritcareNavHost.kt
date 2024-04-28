package com.zaritcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kinoyamboladmin.ui.features.settings.SettingsViewModel
import com.zaritcare.data.services.authentication.AuthServiceImplementation
import com.zaritcare.ui.features.activities.ActivitiesViewModel
import com.zaritcare.ui.features.activities.activity.ActivityViewModel
import com.zaritcare.ui.features.activities.introactivity.IntroActivityViewModel
import com.zaritcare.ui.features.login.LoginViewModel
import com.zaritcare.ui.features.results.ResultsViewModel
import com.zaritcare.ui.features.results.questionary.QuestionaryViewModel
import com.zaritcare.ui.features.tips.TipsViewModel
import javax.inject.Inject

@Composable
fun ZaritcareNavHost(
    modifier: Modifier = Modifier
) {
    val navController: NavHostController = rememberNavController()
    val settingsVm: SettingsViewModel = hiltViewModel<SettingsViewModel>()
    val tipsVm: TipsViewModel = hiltViewModel<TipsViewModel>()
    val introActivitiesVm: IntroActivityViewModel = hiltViewModel<IntroActivityViewModel>()
    val loginVm: LoginViewModel = hiltViewModel<LoginViewModel>()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LoginGraphRoute
    ) {
        loginScreen(
            vm = loginVm,
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToSplash = {
                navController.navigateToSplash()
            }
        )
        splashScreen(
            onClickStart = {
                navController.navigateToResults()
            }
        )
        questionaryScreen(
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateToActivities = {
                navController.navigateToActivities()
            }
        )
        resultsScreen(
            onNavigateToQuestionary = {
                navController.navigateToQuestionary()
            },
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateToActivities = {
                navController.navigateToActivities()
            }
        )
        settingsScreen(
            vm = settingsVm,
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateToActivities = {
                navController.navigateToActivities()
            },
            onNavigateToLogin = {
                navController.navigateToLogin()
            }
        )
        tipsScreen(
            vm = tipsVm,
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateToActivities = {
                navController.navigateToActivities()
            }
        )
        activitiesScreen(
            onNavigateToActivity = {
                navController.navigateToActivity(it)
            },
            onNavigateToIntroActivity = {
                navController.navigateToIntroActivity(it)
            },
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateToActivities = {
                navController.navigateToActivities()
            }
        )
        introActivityScreen(
            vm = introActivitiesVm,
            onNavigateToActivity = {
                navController.navigateToActivity(it)
            },
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            },
            onNavigateToActivities = {
                navController.navigateToActivities()
            }
        )
        activityScreen(
            onNavigateToActivities = {
                navController.navigateToActivities()
            },
            onNavigateToResults = {
                navController.navigateToResults()
            },
            onNavigateToTips = {
                navController.navigateToTips()
            },
            onNavigateToSettings = {
                navController.navigateToSettings()
            }
        )
    }
}
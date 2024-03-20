package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.login.LoginScreen
import com.zaritcare.ui.features.splashscreen.SplashScreen

const val SplashGraphRoute = "splash"

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(SplashGraphRoute, navOptions)
}

fun NavGraphBuilder.splashScreen(
    onClickStart: () -> Unit
) {
    composable(
        route = SplashGraphRoute,
        arguments = emptyList()
    ) {
        SplashScreen(
            onClickStart = onClickStart
        )
    }
}
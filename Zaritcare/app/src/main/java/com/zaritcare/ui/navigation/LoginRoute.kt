package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.login.LoginScreen
import com.zaritcare.ui.features.login.LoginViewModel

const val LoginGraphRoute = "login"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.popBackStack()
    this.navigate(LoginGraphRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    vm: LoginViewModel,
    onNavigateToResults: () -> Unit,
    onNavigateToSplash: () -> Unit
) {
    composable(
        route = LoginGraphRoute,
        arguments = emptyList()
    ) {
        LoginScreen(
            onLoginEvent = vm::onLoginEvent,
            informationState = vm.informationState,
            onNavigateToResults = onNavigateToResults,
            onNavigateToSplash = onNavigateToSplash
        )
    }
}
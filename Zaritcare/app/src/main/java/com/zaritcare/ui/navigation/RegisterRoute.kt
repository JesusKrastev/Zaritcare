package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.register.RegisterScreen

const val RegisterGraphRoute = "register"

fun NavController.navigateToRegister(navOptions: NavOptions? = null) {
    this.navigate(RegisterGraphRoute, navOptions)
}

fun NavGraphBuilder.registerScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    composable(
        route = RegisterGraphRoute,
        arguments = emptyList()
    ) {
        RegisterScreen(
            onClickRegister = onRegisterClick,
            onClickLogin = onLoginClick
        )
    }
}
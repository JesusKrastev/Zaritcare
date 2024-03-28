package com.zaritcare.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zaritcare.ui.features.login.LoginScreen

const val LoginGraphRoute = "login"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(LoginGraphRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit
) {
    composable(
        route = LoginGraphRoute,
        arguments = emptyList()
    ) {
        LoginScreen(
            onClickLogin = onClickLogin,
            onClickRegister = onClickRegister
        )
    }
}
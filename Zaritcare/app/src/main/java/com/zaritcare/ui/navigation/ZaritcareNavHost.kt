package com.zaritcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun ZaritcareNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginGraphRoute
    ) {
        loginScreen(
            onClickLogin = {

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

            }
        )
    }
}
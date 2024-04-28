package com.zaritcare.ui.features.login

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.services.authentication.AuthServiceException
import com.zaritcare.data.services.authentication.AuthServiceImplementation
import com.zaritcare.data.services.authentication.methods.GoogleAuth
import com.zaritcare.utilities.error_handling.InformationStateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthServiceImplementation
): ViewModel() {
    var informationState: InformationStateUiState by mutableStateOf(InformationStateUiState.Hidden())

    private fun onGoogleLoginSelect(googleLauncherLogin: ActivityResultLauncher<Intent>) {
        val googleSignInClient: Intent = authService.getGoogleAccount().signInIntent

        googleLauncherLogin.launch(googleSignInClient)
    }

    private fun signInWithGoogle(idToken: String, onNavigateToResults: () -> Unit, onNavigateToSplash: () -> Unit) {
        var isNewUser: Boolean = false

        informationState = InformationStateUiState.Information(
            showProgress = true,
            message = "Verificando cuenta de Google..."
        )
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    isNewUser = authService.isNewUser(idToken, GoogleAuth())
                    authService.signIn(idToken, GoogleAuth())
                }
                informationState = InformationStateUiState.Hidden()
                if(isNewUser) onNavigateToSplash() else onNavigateToResults()
            } catch (e: AuthServiceException) {
                informationState = InformationStateUiState.Error(
                    message = "Error al intentar iniciar sesión con Google. Por favor, inténtalo de nuevo.",
                    onDismiss = { informationState = InformationStateUiState.Hidden() }
                )
                Log.d("LoginViewModel", "Error: ${e.message}")
            }
        }
    }

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnClickSignInWithGoogle -> {
                onGoogleLoginSelect(event.googleLauncher)
            }
            is LoginEvent.OnSignInWithGoogle -> {
                signInWithGoogle(
                    idToken = event.idToken,
                    onNavigateToResults = event.onNavigateToResults,
                    onNavigateToSplash = event.onNavigateToSplash
                )
            }
            is LoginEvent.OnCheckUserLoggedIn -> {
                if (authService.isUserLoggedIn()) event.onNavigateToResults()
            }
        }
    }
}
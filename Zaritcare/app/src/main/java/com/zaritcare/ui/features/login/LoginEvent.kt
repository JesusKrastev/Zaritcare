package com.zaritcare.ui.features.login

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

sealed interface LoginEvent {
    data class OnSignInWithGoogle(val idToken: String, val onNavigateToResults: () -> Unit, val onNavigateToSplash: () -> Unit): LoginEvent
    data class OnClickSignInWithGoogle(val googleLauncher: ActivityResultLauncher<Intent>): LoginEvent
    data class OnCheckUserLoggedIn(val onNavigateToResults: () -> Unit): LoginEvent
}
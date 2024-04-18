package com.zaritcare.data.services.authentication

import android.util.Log
import com.zaritcare.data.services.authentication.methods.AuthProviderStrategy
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthServiceImplementation @Inject constructor(
    private val authService: AuthService
) {
    private val logTag: String = "AuthServiceImplementation"

    fun checkUserLoggedIn(): Boolean = authService.checkUserLoggedIn()

    fun logOut() = authService.logOut()

    fun getGoogleAccount(): GoogleSignInClient {
        val errorMessage: String = "Error while trying to get Google account."

        try {
            return authService.getGoogleAccount()
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw AuthServiceException(errorMessage)
        }
    }

    suspend fun signIn(idToken: String, authProvider: AuthProviderStrategy): FirebaseUser {
        val credential = authService.getCredential(idToken, authProvider)

        return authProvider.signIn(credential = credential)
    }
}
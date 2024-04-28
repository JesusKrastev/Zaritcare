package com.zaritcare.data.services.authentication

import android.util.Log
import com.zaritcare.data.services.authentication.methods.AuthProviderStrategy
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthServiceImplementation @Inject constructor(
    private val authService: AuthService
) {
    private val logTag: String = "AuthServiceImplementation"

    fun isUserLoggedIn(): Boolean = authService.isUserLoggedIn()

    fun getCurrentUser(): FirebaseUser? = authService.getCurrentUser()

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

    suspend fun isNewUser(idToken: String, authProvider: AuthProviderStrategy): Boolean {
        val credential = authService.getCredential(idToken, authProvider)

        return credential.await().additionalUserInfo?.isNewUser ?: false
    }
}
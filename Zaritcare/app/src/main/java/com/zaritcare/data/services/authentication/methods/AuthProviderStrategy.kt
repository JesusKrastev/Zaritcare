package com.zaritcare.data.services.authentication.methods

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.zaritcare.data.services.authentication.AuthServiceException
import kotlinx.coroutines.tasks.await

abstract class AuthProviderStrategy(
    protected val errorMessage: String
) {
    abstract fun getCredential(idToken: String): AuthCredential
    suspend fun signIn(credential: Task<AuthResult>): FirebaseUser {
        return try {
            val user: FirebaseUser? = credential.await().user

            user ?: throw AuthServiceException(errorMessage)
        } catch (e: Exception) {
            throw AuthServiceException(errorMessage)
        }
    }
}
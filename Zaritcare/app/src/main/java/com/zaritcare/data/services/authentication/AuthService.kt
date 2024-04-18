package com.zaritcare.data.services.authentication

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.zaritcare.R
import com.zaritcare.data.services.authentication.methods.AuthProviderStrategy
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
) {
    fun checkUserLoggedIn(): Boolean = firebaseAuth.currentUser != null

    fun logOut() {
        getGoogleAccount().signOut()
    }

    fun getGoogleAccount(): GoogleSignInClient {
        val idToken = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, idToken)
    }

    fun getCredential(idToken: String, authProvider: AuthProviderStrategy): Task<AuthResult> {
        val credential: AuthCredential = authProvider.getCredential(idToken)

        return firebaseAuth.signInWithCredential(credential)
    }
}
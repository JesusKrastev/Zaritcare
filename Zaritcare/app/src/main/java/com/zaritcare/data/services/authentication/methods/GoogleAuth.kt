package com.zaritcare.data.services.authentication.methods

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.zaritcare.data.services.authentication.methods.AuthProviderStrategy

class GoogleAuth(
    errorMessage: String = "Error while trying to sign in with Google."
): AuthProviderStrategy(errorMessage) {
    override fun getCredential(idToken: String): AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
}
package com.zaritcare.ui.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.zaritcare.R
import com.zaritcare.ui.composables.CoroutineManagementSnackBar
import com.zaritcare.ui.composables.SnackbarCommon
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile
import com.zaritcare.utilities.device.registerGoogleLauncher
import com.zaritcare.utilities.error_handling.InformationStateUiState
import kotlinx.coroutines.delay

@Composable
fun ImageLogo(
    modifier: Modifier = Modifier,
    image: Painter
) {
    Image(
        modifier = modifier,
        painter = image,
        contentScale = ContentScale.FillBounds,
        contentDescription = "logo"
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google"
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Iniciar sesión con Google"
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onLoginEvent: (LoginEvent) -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToSplash: () -> Unit
) {
    val googleLauncher = registerGoogleLauncher(
        signInWithGoogle = { idToken, onNavigateToResultsScreen, onNavigateToSplashScreen ->
            onLoginEvent(LoginEvent.OnSignInWithGoogle(idToken, onNavigateToResultsScreen, onNavigateToSplashScreen))
        },
        onNavigateToResults = onNavigateToResults,
        onNavigateToSplash = onNavigateToSplash
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageLogo(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(width = 200.dp, height = 150.dp),
            image = painterResource(id = R.drawable.logo)
        )
        Spacer(modifier = Modifier.size(48.dp))
        LoginButton(
            onClick = {
                onLoginEvent(LoginEvent.OnClickSignInWithGoogle(googleLauncher))
            }
        )
    }
}

@Composable
fun LoginScreen(
    informationState: InformationStateUiState,
    onLoginEvent: (LoginEvent) -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToSplash: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onLoginEvent(LoginEvent.OnCheckUserLoggedIn(onNavigateToResults))
    }

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    CoroutineManagementSnackBar(
        snackbarHostState = snackbarHostState,
        informationState = informationState
    )

    Scaffold(
        content = { paddingValues ->
            MainContent(
                modifier = Modifier.padding(paddingValues),
                onLoginEvent = onLoginEvent,
                onNavigateToResults = onNavigateToResults,
                onNavigateToSplash = onNavigateToSplash
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                SnackbarCommon(informationState = informationState)
            }
        }
    )
}
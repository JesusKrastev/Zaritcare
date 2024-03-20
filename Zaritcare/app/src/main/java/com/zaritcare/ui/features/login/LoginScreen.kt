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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile

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
fun Header(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ImageLogo(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(width = 200.dp, height = 150.dp),
            image = painterResource(id = R.drawable.logo)
        )
        TextTile(
            title = "Iniciar sesión"
        )
        Text(
            text = "Bienvenid@ de nuevo, te echábamos de menos.",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = Color(0xFF979797)
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LoginButton(
            onClick = onClickLogin
        )
        TextBody(
            modifier = Modifier.clickable { onClickRegister() },
            text = buildAnnotatedString {
                append("¿No tienes una cuenta? ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Regístrarse")
                }
            }
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Header()
        Spacer(modifier = Modifier.size(16.dp))
        Content(
            onClickLogin = onClickLogin,
            onClickRegister = onClickRegister
        )
    }
}

@Composable
fun LoginScreen(
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            MainContent(
                modifier = Modifier.padding(paddingValues),
                onClickLogin = onClickLogin,
                onClickRegister = onClickRegister
            )
        }
    )
}
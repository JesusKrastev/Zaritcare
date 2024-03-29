package com.zaritcare.ui.features.activities.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zaritcare.models.Action
import com.zaritcare.ui.composables.GradientBrush
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile
import com.zaritcare.ui.features.activities.components.BurstBallon
import com.zaritcare.ui.features.activities.components.Chronometer

@Composable
fun HeaderImage(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    gradientColors: List<Color>
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            bitmap = image,
            contentScale = ContentScale.Crop,
            contentDescription = "front page"
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = GradientBrush(
                        isVerticalGradient = true,
                        colors = gradientColors
                    )
                )
        )
    }
}

@Composable
fun FinishedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = onClick
    ) {
        Text(text = "Terminado")
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    activity: ActivityUiState,
    onClickFinishedButton: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextTile(
            title = activity.title,
            color = MaterialTheme.colorScheme.onSecondary
        )
        TextBody(
            text = activity.description,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondary
        )
        TextBody(
            text = activity.action,
            color = MaterialTheme.colorScheme.onSecondary
        )
        when {
            Action.CONTADOR in activity.actions -> {
                Chronometer()
            }
            Action.ESTALLAR_GLOBO in activity.actions -> {
                BurstBallon(
                    size = Size(200f, 300f)
                )
            }
            else -> {
                // La lista de acciones no contiene Action.Cronometro
                // Realiza alguna otra acción en este caso
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        FinishedButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onClickFinishedButton
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    activity: ActivityUiState,
    onClickFinishedButton: () -> Unit
) {
    val gradientColors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colorScheme.background
    )

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderImage(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            image = activity.banner,
            gradientColors = gradientColors
        )
        Body(
            activity = activity,
            onClickFinishedButton = onClickFinishedButton
        )
    }
}

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    activity: ActivityUiState,
    onNavigateToActivities: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Content(
            activity = activity,
            onClickFinishedButton = onNavigateToActivities
        )
    }
}
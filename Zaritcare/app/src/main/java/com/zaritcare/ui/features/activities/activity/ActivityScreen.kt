package com.zaritcare.ui.features.activities.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.composables.GradientBrush
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile

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
fun ActivityScreen(
    modifier: Modifier = Modifier,
    activity: ActivityUiState,
    onNavigateToActivities: () -> Unit
) {
    val gradientColors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colorScheme.background
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderImage(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            image = activity.frontPage,
            gradientColors = gradientColors
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextTile(
                title = activity.title,
                color = MaterialTheme.colorScheme.onSecondary
            )
            TextBody(
                text = activity.description,
                color = MaterialTheme.colorScheme.onSecondary
            )
            FinishedButton(
                onClick = onNavigateToActivities
            )
        }
    }
}
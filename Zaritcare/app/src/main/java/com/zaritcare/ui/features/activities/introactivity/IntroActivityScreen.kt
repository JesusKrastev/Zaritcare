package com.zaritcare.ui.features.activities.introactivity

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile
import com.zaritcare.ui.theme.ZaritcareTheme
import com.zaritcare.utilities.images.Images

@Composable
fun StartButton(
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
        Text(text = "Empezar")
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    activityQuote: ActivityQuoteUiState,
    onClickStartButton: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextTile(
            title = activityQuote.title
        )
        Image(
            modifier = Modifier.size(200.dp),
            bitmap = activityQuote.quoteImage,
            contentDescription = "ilustration"
        )
        Log.d("IntroActivityScreen", "Content: ${activityQuote.quote}")
        TextBody(
            text = "${activityQuote.quote} - ${activityQuote.authorQuote}"
        )
        Spacer(modifier = Modifier.size(16.dp))
        StartButton(
            onClick = onClickStartButton
        )
    }
}

@Composable
fun IntroActivityScreen(
    modifier: Modifier = Modifier,
    activityQuote: ActivityQuoteUiState,
    onNavigateToActivity: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Content(
            activityQuote = activityQuote,
            onClickStartButton = {
                onNavigateToActivity(activityQuote.id)
            }
        )
    }
}

@Preview
@Composable
fun IntroActivityScreenTest() {
    val quoteImageBurstBalloon: String = "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"
    val activityQuote = ActivityQuoteUiState(
        id = 0,
        title = "Reventar el globo",
        quote = "No es el estrés lo que nos mata, sino cómo reaccionamos ante él.",
        authorQuote = "Hans Selye",
        quoteImage = Images.base64ToBitmap(quoteImageBurstBalloon)
    )

    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            IntroActivityScreen(
                activityQuote = activityQuote,
                onNavigateToActivity = {}
            )
        }
    }
}
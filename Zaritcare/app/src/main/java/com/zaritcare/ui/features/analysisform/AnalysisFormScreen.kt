package com.zaritcare.ui.features.analysisform

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaritcare.R
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile
import com.zaritcare.ui.features.components.ZaritcareNavBar

@Composable
fun WithoutResults(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextTile(title = "No has rellenado los formularios")
        TextBody(text = "Parece que aún no has completado los formularios de evaluación emocional. Te animamos a hacerlo para cuidar de ti mientras cuidas a otros.")
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.notebook),
            contentDescription = "notebook"
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = onClickStart
        ) {
            Text(
                text = "Empezar",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun AnalysisFormScreen(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                WithoutResults(
                    modifier = Modifier.padding(16.dp),
                    onClickStart = onClickStart
                )
            }
        },
        bottomBar = {
            ZaritcareNavBar(
                selectedPage = 0,
                onNavigateToForms = { /*TODO*/ },
                onNavigateToActivities = { /*TODO*/ },
                onNavigateToTips = { /*TODO*/ },
                onNavigateToSettings = { /*TODO*/}
            )
        }
    )
}

@Preview
@Composable
fun PreviewScaffold() {
    AnalysisFormScreen(
        onClickStart = {}
    )
}
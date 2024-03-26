package com.zaritcare.ui.features.activities.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.composables.CircularProgressBar
import com.zaritcare.ui.theme.ZaritcareTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
private fun ChronometerIndicator(
    modifier: Modifier = Modifier,
    minutes: Int,
    seconds: Int,
    isEditable: Boolean = true,
    onChangeMinutes: (Int) -> Unit,
    onChangeSeconds: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressBar(
            currentProgress = minutes,
            radius = 50.dp,
            indicatorText = "minutos",
            isEditable = isEditable,
            onChangeValue = onChangeMinutes
        )
        CircularProgressBar(
            currentProgress = seconds,
            radius = 50.dp,
            isEditable = isEditable,
            indicatorText = "segundos",
            onChangeValue = onChangeSeconds
        )
    }
}

@Composable
private fun ChronometerButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String?
) {
    SmallFloatingActionButton(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun ChronometerControls(
    modifier: Modifier = Modifier,
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChronometerButton(
            onClick = onReset,
            icon = Icons.Filled.Stop,
            contentDescription = "stop"
        )
        if(!isRunning) {
            ChronometerButton(
                onClick = onStart,
                icon = Icons.Filled.PlayArrow,
                contentDescription = "play"
            )
        } else {
            ChronometerButton(
                onClick = onPause,
                icon = Icons.Filled.Pause,
                contentDescription = "pause"
            )
        }
    }
}

@Composable
fun Chronometer(
    modifier: Modifier = Modifier
) {
    var job: Job? by remember { mutableStateOf<Job?>(null) }
    var minutes: Int by remember { mutableIntStateOf(0) }
    var seconds: Int by remember { mutableIntStateOf(0) }
    var initialMinutes: Int by remember { mutableIntStateOf(0) }
    var initialSeconds: Int by remember { mutableIntStateOf(0) }
    var isEditable: Boolean by remember { mutableStateOf(true) }
    var isRunning: Boolean by remember { mutableStateOf(false) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChronometerIndicator(
            modifier = modifier,
            minutes = minutes,
            seconds = seconds,
            isEditable = isEditable,
            onChangeMinutes = { minutes = it },
            onChangeSeconds = { seconds = it }
        )
        ChronometerControls(
            modifier = modifier,
            isRunning = isRunning,
            onStart = {
                initialMinutes = minutes
                initialSeconds = seconds
                job = coroutineScope.launch {
                    isEditable = false
                    isRunning = true
                    while (isRunning) {
                        delay(1000)
                        if (seconds > 0) {
                            seconds--
                        } else if (minutes > 0) {
                            minutes--
                            seconds = 59
                        } else {
                            isRunning = false
                            job?.cancel()
                        }
                    }
                }
            },
            onPause = {
                isRunning = false
                job?.cancel()
            },
            onReset = {
                if(isRunning) {
                    minutes = initialMinutes
                    seconds = initialSeconds
                    isRunning = false
                    isEditable = true
                    job?.cancel()
                }
            }
        )
    }
}

@Preview
@Composable
fun ChronometerPreview() {
    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Chronometer()
            }
        }
    }
}
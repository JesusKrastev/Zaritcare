package com.zaritcare.ui.composables

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaritcare.utilities.error_handling.InformationStateUiState

@Composable
fun CoroutineManagementSnackBar(
    informationState : InformationStateUiState,
    snackbarHostState: SnackbarHostState
) {
    val context: Context = LocalContext.current

    LaunchedEffect(
        key1 = informationState
    ) {
        when (informationState) {
            is InformationStateUiState.Information -> snackbarHostState.showSnackbar(
                message = informationState.message,
                duration = SnackbarDuration.Indefinite
            )
            is InformationStateUiState.Error -> snackbarHostState.showSnackbar(
                message = informationState.message,
                duration = SnackbarDuration.Indefinite
            )
            is InformationStateUiState.Hidden -> snackbarHostState.currentSnackbarData?.dismiss()
            is InformationStateUiState.Success -> snackbarHostState.showSnackbar(
                message = informationState.message,
                duration = SnackbarDuration.Short
            )
        }
    }
}

@Composable
fun SnackbarError(
    errorMessage: String,
    onDismissError: () -> Unit = {}
) {
    Snackbar(
        modifier = Modifier.padding(bottom = 16.dp),
        containerColor = MaterialTheme.colorScheme.onErrorContainer,
        contentColor = MaterialTheme.colorScheme.onError,
        dismissAction = {
            IconButton(
                onClick = onDismissError,
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "Cancelar",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
            }
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = errorMessage)
        }
    }
}

@Composable
fun SnackbarInfo(
    informationMessage: String,
    showProgress: Boolean = false,
) {
    Snackbar(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Información",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = informationMessage)
            if (showProgress) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        color = MaterialTheme.colorScheme.onSecondary,
                        trackColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun SnackbarSuccess(
    informationMessage: String,
) {
    Snackbar(
        modifier = Modifier.padding(bottom = 16.dp),
        containerColor = Color(0xFF57B159),
        contentColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "success",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = informationMessage)
        }
    }
}

@Composable
fun SnackbarCommon(
    informationState: InformationStateUiState
) {
    val context: Context = LocalContext.current

    when(informationState) {
        is InformationStateUiState.Information -> {
            SnackbarInfo(
                informationMessage = informationState.message,
                showProgress = informationState.showProgress
            )
        }
        is InformationStateUiState.Error -> {
            SnackbarError(
                errorMessage = informationState.message,
                onDismissError = informationState.onDismiss
            )
        }
        is InformationStateUiState.Success -> {
            SnackbarSuccess(
                informationMessage = informationState.message
            )
        }
        is InformationStateUiState.Hidden -> {
            // Doesn't show Snackbar in the SnackbarHost of the Scaffold
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
fun SnackBarErrorPreview() {
    SnackbarError(errorMessage = "Error")
}

@Preview(
    showBackground = true,
)
@Composable
fun SnackBarInfoPreview() {
    SnackbarInfo(informationMessage = "Loading...", showProgress = true)
}

@Preview(
    showBackground = true,
)
@Composable
fun SnackBarSuccessPreview() {
    SnackbarSuccess(informationMessage = "Saved successfully!")
}
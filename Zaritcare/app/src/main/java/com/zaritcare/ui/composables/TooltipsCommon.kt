package com.zaritcare.ui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltipWithoutAction(
    modifier: Modifier = Modifier,
    title: String,
    text: AnnotatedString
) {
    RichTooltip(
        modifier = modifier,
        title = {
            Text(text = title)
        }
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltipWithoutAction(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    RichTooltip(
        modifier = modifier,
        title = {
            Text(text = title)
        }
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltipWithAction(
    modifier: Modifier = Modifier,
    title: String,
    text: AnnotatedString,
    actionText: String,
    onClick: () -> Unit
) {
    RichTooltip(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        action = {
            TextButton(onClick = onClick) {
                Text(text = actionText)
            }
        }
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltipWithAction(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    actionText: String,
    onClick: () -> Unit
) {
    RichTooltip(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        action = {
            TextButton(onClick = onClick) {
                Text(text = actionText)
            }
        }
    ) {
        Text(text = text)
    }
}
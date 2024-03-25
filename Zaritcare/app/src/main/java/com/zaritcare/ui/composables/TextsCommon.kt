package com.zaritcare.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextTile(
    title: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        color = color,
        fontWeight = FontWeight.Bold,
        style = style,
        modifier = modifier
    )
}

@Composable
fun TextBody(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextBody(
    text: AnnotatedString,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextLabel(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun DefaultText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        modifier = modifier
    )
}
package com.zaritcare.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaritcare.ui.theme.ZaritcareTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
private fun ProgressCircle(
    currentProgress: Int,
    maxProgress: Int,
    radius: Dp,
    activeBarColor: Color,
    handleColor: Color,
    inactiveBarColor: Color,
    strokeWidth: Dp
) {
    Canvas(
        modifier = Modifier.size(radius * 2f)
    ) {
        val progressAngle: Float = 360f * currentProgress / maxProgress
        val pointX: Float = size.width / 2f + (size.width / 2f) * cos(Math.toRadians(90 + progressAngle.toDouble())).toFloat()
        val pointY: Float = size.height / 2f + (size.height / 2f) * sin(Math.toRadians(90 + progressAngle.toDouble())).toFloat()
        drawArc(
            color = inactiveBarColor,
            startAngle = 90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx())
        )
        drawArc(
            color = activeBarColor,
            startAngle = 90f,
            sweepAngle = 360f * currentProgress / maxProgress,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
        drawCircle(
            color = handleColor,
            radius = (radius * 0.15f).value.dp.toPx(),
            center = Offset(pointX, pointY)
        )
    }
}

@Composable
private fun EditableProgressField(
    currentProgress: Int,
    maxProgress: Int,
    radius: Dp,
    textColor: Color,
    isEditable: Boolean = true,
    indicatorText: String,
    onChangeValue: (Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = currentProgress.toString(),
            onValueChange = {
                val numberFormat = Regex("\\d{0,${maxProgress.toString().length}}")

                if(it.isEmpty()) {
                    onChangeValue(0)
                } else if(currentProgress == 0 && numberFormat.matches(it)) {
                    onChangeValue(it.replace("0", "").toInt())
                } else if(numberFormat.matches(it) && it.toInt() <= maxProgress) {
                    onChangeValue(it.toInt())
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontSize = (radius * 0.5f).value.sp
            ),
            enabled = isEditable,
            singleLine = true,
            maxLines = 1
        )
        Text(
            text = indicatorText,
            color = textColor,
            fontSize = (radius * 0.3f).value.sp
        )
    }
}

@Composable
fun CircularProgressBar(
    currentProgress: Int,
    maxProgress: Int = 60,
    indicatorText: String,
    radius: Dp = 50.dp,
    isEditable: Boolean = true,
    activeBarColor: Color = MaterialTheme.colorScheme.primary,
    handleColor: Color = MaterialTheme.colorScheme.onPrimary,
    inactiveBarColor: Color = MaterialTheme.colorScheme.secondary,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    strokeWidth: Dp = (radius * 0.15f).value.dp,
    onChangeValue: (Int) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        ProgressCircle(
            currentProgress = currentProgress,
            maxProgress = maxProgress,
            radius = radius,
            activeBarColor = activeBarColor,
            handleColor = handleColor,
            inactiveBarColor = inactiveBarColor,
            strokeWidth = strokeWidth
        )
        EditableProgressField(
            currentProgress = currentProgress,
            maxProgress = maxProgress,
            radius = radius,
            textColor = textColor,
            isEditable = isEditable,
            indicatorText = indicatorText,
            onChangeValue = onChangeValue
        )
    }
}

@Preview
@Composable
fun TimerPreview() {
    ZaritcareTheme(
        darkTheme = true
    ) {
        var progress: Int by remember { mutableIntStateOf(10) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressBar(
                    currentProgress = progress,
                    radius = 50.dp,
                    activeBarColor = MaterialTheme.colorScheme.primary,
                    inactiveBarColor = MaterialTheme.colorScheme.secondary,
                    textColor = MaterialTheme.colorScheme.onBackground,
                    indicatorText = "minutos",
                    onChangeValue = {
                        progress = it
                    }
                )
            }
        }
    }
}
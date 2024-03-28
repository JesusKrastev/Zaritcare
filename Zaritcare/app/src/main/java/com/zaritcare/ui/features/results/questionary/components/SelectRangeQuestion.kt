package com.zaritcare.ui.features.results.questionary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.zaritcare.ui.theme.ZaritcareTheme

@Composable
fun Question(
    question: String
) {
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = question,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun RangeSlider(
    range: ClosedFloatingPointRange<Float>,
    steps: Int,
    initialValue: Float,
    onValueChange: (Float) -> Unit
) {
    var sliderPosition: Float by remember { mutableFloatStateOf(initialValue) }

    Slider(
        value = sliderPosition,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary
        ),
        valueRange = range,
        steps = steps,
        onValueChange = { value ->
            sliderPosition = value
            onValueChange(value)
        }
    )
}

@Composable
fun MinMaxIndicators(
    textMinimumSelection: String,
    textMaximumSelection: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textMinimumSelection,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = textMaximumSelection,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun SelectRangeQuestion(
    modifier: Modifier = Modifier,
    textMinimumSelection: String,
    textMaximumSelection: String,
    initialValue: Float,
    question: String,
    range:  ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = modifier.wrapContentHeight()
    ) {
        Question(
            question = question
        )
        RangeSlider(
            initialValue = initialValue,
            steps = steps,
            onValueChange = onValueChange,
            range = range
        )
        MinMaxIndicators(
            textMinimumSelection = textMinimumSelection,
            textMaximumSelection = textMaximumSelection
        )
    }
}

@Preview
@Composable
fun SelectRangeQuestionPreview(
) {
    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SelectRangeQuestion(
                textMinimumSelection = "No severo",
                textMaximumSelection = "Severo",
                question = "How much do you like this feature?",
                range = 0f..10f,
                steps = 9,
                onValueChange = {},
                initialValue = 0f
            )
        }
    }
}
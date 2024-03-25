package com.zaritcare.ui.features.results.questionary.zaritform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.features.results.questionary.QuestionUiState
import com.zaritcare.ui.features.results.questionary.components.SelectRangeQuestion

@Composable
fun Form(
    modifier: Modifier = Modifier,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit
)  {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        questions.forEach { question ->
            SelectRangeQuestion(
                question = question.question,
                range = question.range,
                steps = question.steps,
                onValueChange = { value ->
                    onChangeAnswer(question.copy(answer = value.toString()))
                },
                textMinimumSelection = question.minimumValueIndicator,
                textMaximumSelection = question.maximumValueIndicator,
                initialValue = question.answer.toFloat()
            )
        }
    }
}

@Composable
fun ZaritScaleScreen(
    modifier: Modifier = Modifier,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Form(
            onChangeAnswer = onChangeAnswer,
            questions = questions
        )
    }
}
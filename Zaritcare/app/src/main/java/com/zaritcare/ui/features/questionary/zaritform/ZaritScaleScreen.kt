package com.zaritcare.ui.features.questionary.zaritform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.features.questionary.QuestionUiState
import com.zaritcare.ui.features.questionary.selectrangequestion.SelectRangeQuestion

@Composable
fun Form(
    modifier: Modifier = Modifier,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit
)  {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
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
                textMaximumSelection = question.maximumValueIndicator
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
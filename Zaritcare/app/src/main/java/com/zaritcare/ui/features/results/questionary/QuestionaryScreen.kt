package com.zaritcare.ui.features.results.questionary

import com.zaritcare.ui.composables.TextSwitch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.composables.CollapsingLayout
import com.zaritcare.ui.features.components.ZaritcareNavBar
import com.zaritcare.ui.features.results.questionary.wellbeingform.EmotionUiState
import com.zaritcare.ui.features.results.questionary.wellbeingform.WellbeingScaleScreen
import com.zaritcare.ui.features.results.questionary.zaritform.ZaritScaleScreen

@Composable
fun ListQuestions(
    selectedIndex: Int,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit,
    emotions: List<EmotionUiState>
) {
    when (selectedIndex) {
        0 -> WellbeingScaleScreen(
            questions = questions,
            onChangeAnswer = onChangeAnswer,
            emotions = emotions
        )
        1 -> ZaritScaleScreen(
            questions = questions,
            onChangeAnswer = onChangeAnswer
        )
    }
}

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            contentDescription = "Save"
        )
    }
}

@Composable
fun Form(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit,
    emotions: List<EmotionUiState>,
    onClickSave: () -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ListQuestions(
            selectedIndex = selectedIndex,
            questions = questions,
            onChangeAnswer = onChangeAnswer,
            emotions = emotions
        )
        SaveButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onClickSave
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    questions: List<QuestionUiState>,
    onNavigateToResults: () -> Unit,
    emotions: List<EmotionUiState>,
    onQuestionaryEvent: (QuestionaryEvent) -> Unit,
    categories: List<String>,
) {
    CollapsingLayout(
        modifier = modifier.fillMaxWidth(),
        collapsingTop = { },
        bodyContent = {
            Column(
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextSwitch(
                    selectedIndex = selectedIndex,
                    items = categories.map { it.lowercase().replaceFirstChar { it.uppercase() } },
                    onSelectionChange = { onQuestionaryEvent(QuestionaryEvent.OnSelectionChange(it))  }
                )
                Form(
                    selectedIndex = selectedIndex,
                    questions = questions,
                    onChangeAnswer = { onQuestionaryEvent(QuestionaryEvent.OnChangeAnswer(it)) },
                    emotions = emotions,
                    onClickSave = { onQuestionaryEvent(QuestionaryEvent.OnClickSave(onNavigateToResults)) }
                )
            }
        }
    )
}

@Composable
fun QuestionaryScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onQuestionaryEvent: (QuestionaryEvent) -> Unit,
    questions: List<QuestionUiState>,
    emotions: List<EmotionUiState>,
    categories: List<String>,
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .padding(paddingValues = paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Content(
                    selectedIndex = selectedIndex,
                    categories = categories,
                    emotions = emotions,
                    questions = questions,
                    onQuestionaryEvent = onQuestionaryEvent,
                    onNavigateToResults = onNavigateToResults
                )
            }
        },
        bottomBar = {
            ZaritcareNavBar(
                onNavigateToForms = onNavigateToResults,
                onNavigateToActivities = onNavigateToActivities,
                onNavigateToTips = onNavigateToTips,
                onNavigateToSettings = onNavigateToSettings,
                selectedPage = 0
            )
        }
    )
}
package com.zaritcare.ui.features.questionary

import androidx.compose.foundation.ExperimentalFoundationApi
import com.zaritcare.ui.composables.TextSwitch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaritcare.ui.features.components.ZaritcareNavBar
import com.zaritcare.ui.features.questionary.wellbeingform.EmotionUiState
import com.zaritcare.ui.features.questionary.wellbeingform.WellbeingScaleScreen
import com.zaritcare.ui.features.questionary.zaritform.ZaritScaleScreen

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
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            contentDescription = "Save"
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit,
    emotions: List<EmotionUiState>,
    onClickSave: () -> Unit,
    onSelectionChange: (Int) -> Unit,
    categories: List<CategoryUiState>,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextSwitch(
            selectedIndex = selectedIndex,
            items = categories.map { it.name },
            onSelectionChange = onSelectionChange
        )
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
fun QuestionaryScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onQuestionaryEvent: (QuestionaryEvent) -> Unit,
    questions: List<QuestionUiState>,
    emotions: List<EmotionUiState>,
    categories: List<CategoryUiState>,
    onNavigateToForms: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToTips: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val questionsByCategory: List<QuestionUiState> = questions.filter { it.category == categories[selectedIndex].name }

    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Content(
                    selectedIndex = selectedIndex,
                    onSelectionChange = { onQuestionaryEvent(QuestionaryEvent.OnSelectionChange(it))  },
                    categories = categories,
                    emotions = emotions,
                    onChangeAnswer = { onQuestionaryEvent(QuestionaryEvent.OnChangeAnswer(it)) },
                    questions = questionsByCategory,
                    onClickSave = { onQuestionaryEvent(QuestionaryEvent.OnClickSave({})) }
                )
            }
        },
        bottomBar = {
            ZaritcareNavBar(
                selectedPage = 0,
                onNavigateToForms = onNavigateToForms,
                onNavigateToActivities = onNavigateToActivities,
                onNavigateToTips = onNavigateToTips,
                onNavigateToSettings = onNavigateToSettings
            )
        }
    )
}
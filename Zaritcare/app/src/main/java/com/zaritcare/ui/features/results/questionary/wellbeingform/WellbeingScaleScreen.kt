package com.zaritcare.ui.features.results.questionary.wellbeingform

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaritcare.models.Type
import com.zaritcare.ui.composables.TextTile
import com.zaritcare.ui.features.results.questionary.QuestionUiState
import com.zaritcare.ui.features.results.questionary.components.SelectRangeQuestion
import com.zaritcare.ui.theme.ZaritcareTheme
import com.zaritcare.utilities.images.Images

@Composable
fun Emotion(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    isSelected: Boolean,
    emotion: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
                .clickable { onClick() }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                bitmap = image,
                contentDescription = "emotion",
                contentScale = ContentScale.FillBounds
            )
        }
        Text(
            text = emotion,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
    }
}

@Composable
fun QuestionText(
    modifier: Modifier = Modifier,
    question: String
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground,
        text = question,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun EmotionQuestion(
    modifier: Modifier = Modifier,
    emotions: List<EmotionUiState>,
    selectedEmotion: Int,
    question: String,
    onEmotionSelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        QuestionText(
            modifier = Modifier.padding(bottom = 16.dp),
            question = question
        )
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(emotions) { emotion ->
                Emotion(
                    image = emotion.image,
                    isSelected = emotion.id == selectedEmotion,
                    emotion = emotion.name,
                    onClick = { onEmotionSelected(emotion.id) }
                )
            }
        }
    }
}

@Composable
fun Form(
    modifier: Modifier = Modifier,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit,
    emotions: List<EmotionUiState>
)  {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        questions.forEach { question ->
            if(question.type == Type.EMOCION) {
                EmotionQuestion(
                    emotions = emotions,
                    selectedEmotion = question.answer.toInt(),
                    question = question.question,
                    onEmotionSelected = { onChangeAnswer(question.copy(answer = it.toString())) }
                )
            } else {
                SelectRangeQuestion(
                    question = question.question,
                    range = question.range,
                    initialValue = question.answer.toFloat(),
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
}

@Composable
fun WellbeingScaleScreen(
    modifier: Modifier = Modifier,
    questions: List<QuestionUiState>,
    onChangeAnswer: (QuestionUiState) -> Unit,
    emotions: List<EmotionUiState>
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Form(
            onChangeAnswer = onChangeAnswer,
            emotions = emotions,
            questions = questions
        )
    }
}

@Preview
@Composable
fun SelectionRangeQuestionTest() {
    val range: ClosedFloatingPointRange<Float> = 0.0f..10.0f
    val steps: Int = 9

    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                SelectRangeQuestion(
                    modifier = Modifier.padding(16.dp),
                    question = "How happy are you?",
                    onValueChange = { value ->
                        // Do something with the value
                    },
                    range = range,
                    steps = steps,
                    textMinimumSelection = "Not happy",
                    textMaximumSelection = "Very happy",
                    initialValue = 0f
                )
            }
        }
    }
}

@Preview
@Composable
fun EmotionPreview() {
    val smilePhoto: String = "iVBORw0KGgoAAAANSUhEUgAAACYAAAAiCAYAAAAzrKu4AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAEESURBVHgB7ZY7CsJAEIZ/I4KQiKKIoIWNlY32djb22usttPQG3kFvoeAdtLDTxkpJIYLEFxgT1iYgrjM+SDEfhCHDwH7szC4bcRd1FyHEQEgRMSoiRkXEqIgYFRGjwhfLdoDyVMV38v8TawejLk+EL2YPg1GXJxKRZw8Rnpg/P4alr4ta7FnjiaVb3qKmvi6WU7UMeGKnFZCo6eviJeC8BAeemH/iMq3X7TRM1cbdBBx4Ys4cuGyBfPe5nC9V6Kmd3Y/BgX9d+INdHKhZs0fA8dEyq+rNVRO4boB1H7gdwOHzeyzZAFLeZ1bUvzNT7WPu1PfEfoQ8e6iIGBURoyJiVEIrdgdzhz3nL/O3BAAAAABJRU5ErkJggg=="

    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Emotion(
                    image = Images.base64ToBitmap(smilePhoto),
                    emotion = "Happy",
                    isSelected = true,
                    onClick = {}
                )
            }
        }
    }
}
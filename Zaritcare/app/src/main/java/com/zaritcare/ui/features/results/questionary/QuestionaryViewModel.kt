package com.zaritcare.ui.features.results.questionary

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.AnswerRepository
import com.zaritcare.data.EmotionRepository
import com.zaritcare.data.QuestionRepository
import com.zaritcare.models.Category
import com.zaritcare.models.Type
import com.zaritcare.ui.features.results.questionary.wellbeingform.EmotionUiState
import com.zaritcare.ui.features.results.questionary.wellbeingform.toEmotionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class QuestionaryViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val emotionRepository: EmotionRepository,
    private val answerRepository: AnswerRepository
): ViewModel() {
    private var questionsState: List<QuestionUiState> by mutableStateOf(emptyList())
    var emotionsState: List<EmotionUiState> by mutableStateOf(emptyList())
    var categoriesState: List<String> by mutableStateOf(Category.values().map { it.name })
    val questionsByCategoryState: List<QuestionUiState> by derivedStateOf { questionsState.filter { it.category.name == categoriesState[selectedTab] } }
    var selectedTab by mutableIntStateOf(0)

    suspend fun getQuestions() = questionRepository.get().map { question -> question.toQuestionUiState() }
    suspend fun getEmotions() = emotionRepository.get().map { emotion -> emotion.toEmotionUiState() }

    fun loadQuestions() {
        viewModelScope.launch {
            try {
                val emotions: List<EmotionUiState> = getEmotions()
                questionsState = getQuestions().map { it.copy(answer = if(it.type == Type.EMOCION) emotions.first().id.toString() else "0") }
            } catch (e: Exception) {
                Log.d("QuestionaryViewModel", "Error loading questions", e)
            }
        }
    }

    fun loadEmotions() {
        viewModelScope.launch {
            try {
                emotionsState = getEmotions()
            } catch (e: Exception) {
                Log.d("QuestionaryViewModel", "Error loading emotions", e)
            }
        }
    }

    fun clearQuestionaryState() {
        loadQuestions()
        loadEmotions()
    }

    init {
        loadQuestions()
        loadEmotions()
    }

    fun onQuestionaryEvent(event: QuestionaryEvent) {
        when (event) {
            is QuestionaryEvent.OnSelectionChange -> {
                selectedTab = event.index
            }
            is QuestionaryEvent.OnClickSave -> {
                viewModelScope.launch {
                    val todaysDate: LocalDate = LocalDate.now()
                    questionsState.map { answerRepository.insert(it.toAnswer().copy(date = todaysDate)) }
                }
                clearQuestionaryState()
                event.onNavigateToResults()
            }
            is QuestionaryEvent.OnChangeAnswer -> {
                val question: QuestionUiState = event.question
                val index: Int = questionsState.indexOfFirst { it.id == question.id }
                questionsState = questionsState.toMutableList().apply {
                    set(index, question)
                }
            }
        }
    }
}
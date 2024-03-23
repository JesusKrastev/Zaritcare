package com.zaritcare.ui.features.questionary

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.CategoryRepository
import com.zaritcare.data.EmotionRepository
import com.zaritcare.data.QuestionRepository
import com.zaritcare.ui.features.questionary.wellbeingform.EmotionUiState
import com.zaritcare.ui.features.questionary.wellbeingform.toEmotionUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionaryViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val emotionRepository: EmotionRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {
    var questionsState: List<QuestionUiState> by mutableStateOf(emptyList())
    var questionsByCategoryState: List<QuestionUiState> by mutableStateOf(emptyList())
    var emotionsState: List<EmotionUiState> by mutableStateOf(emptyList())
    var categoriesState: List<CategoryUiState> by mutableStateOf(emptyList())
    var selectedTab by mutableIntStateOf(0)

    suspend fun getQuestions() = questionRepository.get().map { question -> question.toQuestionUiState() }
    suspend fun getQuestionsByCategory(category: String) = questionRepository.get().map { question -> question.toQuestionUiState() }.filter { it.category == category }
    suspend fun getEmotions() = emotionRepository.get().map { emotion -> emotion.toEmotionUiState() }
    suspend fun getCategories() = categoryRepository.get().map { category -> category.toCategoryUiState() }

    fun loadQuestions() {
        viewModelScope.launch {
            try {
                val emotions: List<EmotionUiState> = getEmotions()
                questionsState = getQuestions().map { it.copy(answer = if(it.type == QuestionUiState.QuestionUiType.EMOTION) emotions.first().name else "0") }
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

    fun loadCategories() {
        viewModelScope.launch {
            try {
                categoriesState = getCategories()
            } catch (e: Exception) {
                Log.d("QuestionaryViewModel", "Error loading categories", e)
            }
        }
    }

    init {
        loadQuestions()
        loadEmotions()
        loadCategories()
    }

    fun onQuestionaryEvent(event: QuestionaryEvent) {
        when (event) {
            is QuestionaryEvent.OnSelectionChange -> {
                selectedTab = event.index
            }
            is QuestionaryEvent.OnClickSave -> {
                // TODO: Save answers
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
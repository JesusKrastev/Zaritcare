package com.zaritcare.ui.features.results

import android.util.Log
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.AnswerRepository
import com.zaritcare.data.EmotionRepository
import com.zaritcare.data.services.authentication.AuthServiceImplementation
import com.zaritcare.models.Category
import com.zaritcare.models.Type
import com.zaritcare.ui.features.results.questionary.wellbeingform.toEmotionUiState
import com.zaritcare.utilities.error_handling.InformationStateUiState
import com.zaritcare.utilities.images.Images
import com.zaritcare.utilities.images.saveAsPdf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val answerRepository: AnswerRepository,
    private val emotionRepository: EmotionRepository,
    private val authService: AuthServiceImplementation
): ViewModel() {
    var answersByCategory: Map<Category, List<AnswerUiState>> by mutableStateOf(hashMapOf())
        private set
    private var user: String by mutableStateOf("")
    var informationState: InformationStateUiState by mutableStateOf(InformationStateUiState.Hidden())
        private set

    fun getAnswers() = answerRepository.get(LocalDate.now(), user)

    fun loadUser() {
        runBlocking {
            user = authService.getCurrentUser()?.uid ?: ""
        }
    }

    fun loadAnswers() {
        viewModelScope.launch {
            try {
                getAnswers().collect { todaysAnswers ->
                    answersByCategory = todaysAnswers
                        .map {
                            when(it.toAnswerUiState().type) {
                                Type.RANGO -> it.toAnswerUiState()
                                Type.EMOCION -> {
                                    val emotion = emotionRepository.get(it.answer.toInt())
                                    it.toAnswerUiState().copy(image = Images.base64ToBitmap(emotion.image), answer = emotion.name.lowercase())
                                }
                            }
                        }.groupBy {
                            it.category
                        }
                }
            } catch (e: Exception) {
                Log.d("ResultsViewModel", "Error loading answers", e)
            }
        }
    }

    init {
        loadUser()
        loadAnswers()
    }

    @OptIn(ExperimentalComposeApi::class)
    fun onResultsEvent(event: ResultsEvent) {
        when(event) {
            is ResultsEvent.OnClickDownloadPDF -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        informationState = InformationStateUiState.Information("Descargando PDF...")
                        val imageBitmap = event.captureController.captureAsync().await()
                        Images.convertHardwareBitmapToSoftwareBitmap(imageBitmap)?.saveAsPdf()
                        informationState = InformationStateUiState.Success("PDF descargado correctamente")
                    } catch (error: Throwable) {
                        informationState = InformationStateUiState.Error(
                            message = "Error al descargar PDF",
                            onDismiss = { informationState = InformationStateUiState.Hidden() }
                        )
                        Log.d("ListScreen", "Error capturing screenshot: $error")
                    }
                }
            }
        }
    }
}
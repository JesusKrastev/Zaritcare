package com.zaritcare.ui.features.tips

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaritcare.data.AdviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    private val adviceRepository: AdviceRepository
): ViewModel() {
    var tipsState: List<AdviceUiState> by mutableStateOf(emptyList())

    suspend fun getTips() = adviceRepository.get().map { advice -> advice.toAdviceUiState() }

    fun loadTips() {
        viewModelScope.launch {
            try {
                tipsState = getTips()
            } catch (e: Exception) {
                Log.d("TipsViewModel", "Error loading tips", e)
            }
        }
    }

    init {
        loadTips()
    }
}
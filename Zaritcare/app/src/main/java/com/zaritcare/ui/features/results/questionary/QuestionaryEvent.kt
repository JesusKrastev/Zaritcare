package com.zaritcare.ui.features.results.questionary

sealed interface QuestionaryEvent {
    data class OnSelectionChange(val index: Int): QuestionaryEvent
    data class OnClickSave(val onNavigateToResults: () -> Unit): QuestionaryEvent
    data class OnChangeAnswer(val question: QuestionUiState): QuestionaryEvent
}
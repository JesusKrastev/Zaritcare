package com.zaritcare.ui.features.questionaryform

sealed interface QuestionaryEvent {
    data class OnSelectionChange(val index: Int): QuestionaryEvent
    data class OnClickSave(val onNavigateToResults: () -> Unit): QuestionaryEvent
    data class OnChangeAnswer(val question: QuestionUiState): QuestionaryEvent
}
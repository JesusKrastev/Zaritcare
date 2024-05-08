package com.zaritcare.utilities.error_handling

sealed class InformationStateUiState(val visible: Boolean, val message: String = "") {
    class Hidden :
        InformationStateUiState(
            visible = false
        )

    class Information(message: String, val showProgress: Boolean = false) :
        InformationStateUiState(
            visible = true,
            message = message
        )

    class Error(message: String, val onDismiss: () -> Unit) :
        InformationStateUiState(
            visible = true,
            message = message
        )
    class Success(message: String) :
        InformationStateUiState(
            visible = true,
            message = message
        )
}

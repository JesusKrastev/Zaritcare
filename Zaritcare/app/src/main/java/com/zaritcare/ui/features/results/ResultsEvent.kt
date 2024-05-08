package com.zaritcare.ui.features.results

import dev.shreyaspatil.capturable.controller.CaptureController
import kotlinx.coroutines.CoroutineScope

sealed interface ResultsEvent {
    data class OnClickDownloadPDF(val captureController: CaptureController): ResultsEvent
}
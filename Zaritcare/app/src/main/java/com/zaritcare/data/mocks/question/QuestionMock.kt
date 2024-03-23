package com.zaritcare.data.mocks.question

import com.zaritcare.models.Question

data class QuestionMock(
    val id: Int,
    val question: String,
    val category: Int,
    val type: QuestionType = QuestionType.RANGE
) {
    enum class QuestionType {
        RANGE,
        EMOTION
    }
}
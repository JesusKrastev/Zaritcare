package com.zaritcare.data.mocks.question

data class QuestionMock(
    val id: Int,
    val question: String,
    val category: String
) {
    enum class Categories {
        WellBeing, Zarit
    }
}
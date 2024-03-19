package com.zaritcare.data.mocks.question

data class QuestionMock(
    val id: Int,
    val question: String,
    val category: Category
) {
    enum class Category {
        WELLBEING, ZARIT, ERROR
    }
}
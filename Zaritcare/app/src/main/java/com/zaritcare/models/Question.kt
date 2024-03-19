package com.zaritcare.models

data class Question(
    val id: Int,
    val question: String,
    val category: List<Category>
) {
    enum class Category {
        WellBeing, Zarit
    }
}
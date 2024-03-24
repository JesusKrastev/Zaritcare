package com.zaritcare.ui.features.questionaryform

import com.zaritcare.models.Category

data class CategoryUiState(
    val id: Int,
    val name: String
)

fun Category.toCategoryUiState() = CategoryUiState(
    id = id,
    name = name
)
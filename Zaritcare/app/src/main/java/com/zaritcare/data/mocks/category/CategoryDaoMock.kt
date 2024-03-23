package com.zaritcare.data.mocks.category

import javax.inject.Inject

class CategoryDaoMock @Inject constructor() {
    private val categories = mutableListOf<CategoryMock>(
        CategoryMock(
            id = 1,
            name = "Bienestar"
        ),
        CategoryMock(
            id = 2,
            name = "Zarit"
        )
    )

    fun get(): List<CategoryMock> = categories
    fun get(id: Int): CategoryMock? = categories.find { it.id == id }
    fun count(): Int = categories.size
}
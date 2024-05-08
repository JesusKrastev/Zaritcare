package com.zaritcare.ui.features.results.questionary

import com.zaritcare.models.Category

enum class CategoryUiState(
    val description: String = "",
    val range: ClosedFloatingPointRange<Float> = 0f..10f
) {
    BIENESTAR(
        description = "1. Fatiga\n2. Insomnio\n3. Dolor de cabeza\n4. Depresión.\n0 = No severo\n10 = Severo",
    ),
    ZARIT(
        description = "1. ¿Sientes que tu familiar dependiente te está controlando demasiado?\n2. ¿Sientes que tu vida social ha sido restringida debido a tu situación de cuidador?\n3. ¿Te sientes frustado/a por la falta de apoyo de otras personas en el cuidado de tu familiar?\n 4. ¿Te sientes tenso/a entre tú y tu familiar dependiente?\n5. ¿Te sientes culpable por no poder hacer más por tu familiar?\n6. ¿Sientes que tu salud ha sufrido debido a tu situación de cuidador?\n0 = Nunca\n1 = Casi nunca\n2 = A veces\n3 = Frecuentemente\n10 = Casi siempre",
        range = 0f..4f
    )
}

fun Category.toCategoryUiState(): CategoryUiState =
    when(this) {
        Category.BIENESTAR -> CategoryUiState.BIENESTAR
        Category.ZARIT -> CategoryUiState.ZARIT
    }

fun CategoryUiState.toCategory(): Category =
    when(this) {
        CategoryUiState.BIENESTAR -> Category.BIENESTAR
        CategoryUiState.ZARIT -> Category.ZARIT
    }
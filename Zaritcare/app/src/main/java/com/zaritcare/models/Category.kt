package com.zaritcare.models

enum class Category(
    val range: ClosedFloatingPointRange<Float> = 0f..10f
) {
    BIENESTAR,
    ZARIT(0f..4f)
}
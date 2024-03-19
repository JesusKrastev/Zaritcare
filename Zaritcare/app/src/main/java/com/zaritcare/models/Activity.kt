package com.zaritcare.models

data class Activity(
    val id: Int,
    val image: String,
    val name: String,
    val description: String,
    val authorQuote: String?,
    val quote: String?,
    val action: String
)
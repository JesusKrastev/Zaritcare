package com.zaritcare.models

data class Activity(
    val id: Int,
    val frontPage: String,
    val banner: String,
    val title: String,
    val description: String,
    val quoteImage: String?,
    val authorQuote: String?,
    val quote: String?,
    val actions: List<Action>,
    val action: String
)
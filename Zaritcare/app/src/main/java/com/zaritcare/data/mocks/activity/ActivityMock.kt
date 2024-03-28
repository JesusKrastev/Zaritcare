package com.zaritcare.data.mocks.activity

import com.zaritcare.models.Action

data class ActivityMock(
    val id: Int,
    val frontPage: String,
    val banner: String,
    val title: String,
    val description: String,
    val quoteImage: String?,
    val authorQuote: String?,
    val quote: String?,
    val action: String,
    val actions: List<Action>
)
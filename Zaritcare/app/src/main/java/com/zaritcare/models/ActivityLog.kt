package com.zaritcare.models

import java.time.LocalDate

data class ActivityLog(
    val id: Int,
    val activity: Int,
    val date: LocalDate,
    val user: String
)

package com.example.androidcourse.model

import com.example.androidcourse.services.TempService

data class City (
    val id: Int,
    val name: String,
    val temperature: Double,
    val wind_deg: String,
    val wind_speed: Double
)
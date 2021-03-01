package com.example.androidcourse.weatherUtils

import com.google.gson.annotations.SerializedName

data class WeatherResponseByList (
    @SerializedName("list")
    var cities: List<WeatherResponse>
)
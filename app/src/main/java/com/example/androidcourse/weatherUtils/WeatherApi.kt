package com.example.androidcourse.weatherUtils

import com.example.androidcourse.weatherUtils.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query;

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @Query("q")cityName: String
    ) : WeatherResponse
}
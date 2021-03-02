package com.example.androidcourse.weatherUtils

import com.example.androidcourse.weatherUtils.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query;

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @Query("q")cityName: String
    ) : WeatherResponse

    @GET("find?units=metric&lang=ru")
    suspend fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
        ): WeatherResponseByList

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeatherById(
        @Query("id") id: Int): WeatherResponse
}
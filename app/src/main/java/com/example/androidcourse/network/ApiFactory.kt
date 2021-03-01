package com.example.androidcourse.network

import com.example.androidcourse.BuildConfig
import com.example.androidcourse.weatherUtils.WeatherApi
import com.example.androidcourse.weatherUtils.WeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {
    private const val QUERY_API_KEY = "appid"

    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .addQueryParameter(
                QUERY_API_KEY,
                BuildConfig.API_KEY
            )
            .build()
            .let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(LoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApi: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    suspend fun getWeathersByLocation (lat: Double, lon: Double ): List<WeatherResponse>{
        return  weatherApi.getWeatherByLocation(lat,lon, 15).cities
    }

    suspend fun getWeatherByCityName (name:String): WeatherResponse {
        return weatherApi.getWeather(name)
    }
}
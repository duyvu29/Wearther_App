package com.example.weatherapp_kotlinmvvm.service

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ForecastAPI::class.java)

    fun getWeatherForecast(city: String, apiKey: String, callback: Callback<WeatherResponse>) {
        val call = apiService.getForecast(city, 40, apiKey)
        call.enqueue(callback)
    }
}
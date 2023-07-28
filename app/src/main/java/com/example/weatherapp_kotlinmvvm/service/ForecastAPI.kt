package com.example.weatherapp_kotlinmvvm.service

import com.example.weatherapp_kotlinmvvm.model.Wind
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherForecast(val date: String, val temperature: Double, val description: String, val icon: String, val wind: String, val humidity: String)
    interface ForecastAPI {
        @GET("forecast")
        fun getForecast(
            @Query("q") city: String,
            @Query("cnt") cnt: Int,
            @Query("appid") apiKey: String
        ): Call<WeatherResponse>
    }



    data class WeatherResponse(val list: List<WeatherData>)

    data class WeatherData(val dt: Long, val main: MainData, val weather: List<WeatherDetail>, val dt_txt: String , val wind : Wind)

    data class MainData(val temp: Double, val humidity: String)

    data class WeatherDetail(val description: String, val icon: String)

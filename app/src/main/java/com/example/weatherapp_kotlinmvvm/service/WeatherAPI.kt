package com.example.weatherapp_kotlinmvvm.service

import com.example.weatherapp_kotlinmvvm.model.WeatherModel
import com.example.weatherapp_kotlinmvvm.model.forecast.Forecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //http://api.openweathermap.org/data/2.5/weather?q=bingöl&APPID=04a42b96398abc8e4183798ed22f9485

    //http://api.openweathermap.org/data/2.5/weather?q=Saigon&APPID=04a42b96398abc8e4183798ed22f9485

    //https://api.openweathermap.org/data/2.5/forecast?q=Saigon&APPID=04a42b96398abc8e4183798ed22f9485

    @GET("weather?&units=metric&APPID=04a42b96398abc8e4183798ed22f9485")
    fun getData(
        @Query("q") cityName: String
    ): Single<WeatherModel>




}
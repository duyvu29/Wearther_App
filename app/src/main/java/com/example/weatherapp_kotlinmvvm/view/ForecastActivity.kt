package com.example.weatherapp_kotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp_kotlinmvvm.R
import com.example.weatherapp_kotlinmvvm.adapter.ForecastAdapter
import com.example.weatherapp_kotlinmvvm.service.WeatherForecast
import com.example.weatherapp_kotlinmvvm.service.WeatherRepository
import com.example.weatherapp_kotlinmvvm.service.WeatherResponse
import kotlinx.android.synthetic.main.activity_forecast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {
    private val repository = WeatherRepository()
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)



        btnGetForecast.setOnClickListener {
            val apiKey = "04a42b96398abc8e4183798ed22f9485"
            val city = edtCity.text.toString()

            repository.getWeatherForecast(city, apiKey, object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        Log.d("TAG", "onResponse:$weatherResponse")
                        val forecastList = mutableListOf<WeatherForecast>()
                        //val forecastList =
                        weatherResponse?.list?.forEach{ weatherData ->
                            val forecast = WeatherForecast(
                                date = weatherData.dt_txt,
                                temperature = weatherData.main.temp,
                                description = weatherData.weather.first().description,
                                icon = weatherData.weather.first().icon,
                                wind = weatherData.wind.speed.toString(),
                                humidity = weatherData.main.humidity
                            )
                            forecastList.add(forecast)
                        }

                        forecastAdapter = ForecastAdapter(forecastList)
                        rvForecast.layoutManager = LinearLayoutManager(this@ForecastActivity)
                        rvForecast.adapter = forecastAdapter
                    } else {
                        Toast.makeText(this@ForecastActivity, "Failed to get forecast", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@ForecastActivity, "Network error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}
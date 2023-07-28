package com.example.weatherapp_kotlinmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp_kotlinmvvm.R
import com.example.weatherapp_kotlinmvvm.service.WeatherData
import com.example.weatherapp_kotlinmvvm.service.WeatherForecast
import com.example.weatherapp_kotlinmvvm.service.WeatherResponse
import kotlinx.android.synthetic.main.item_weather_forecast.view.*

class ForecastAdapter(private val forecastList: List<WeatherForecast>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_forecast, parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val currentForecast = forecastList[position]
        holder.bind(currentForecast)
    }

    override fun getItemCount() = forecastList.size

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecast: WeatherForecast) {
            itemView.tvDate.text = forecast.date.toString()
            itemView.tvTemperature.text = "${forecast.temperature}°C"
            itemView.tvDescription.text = forecast.description
            itemView.tvWindSpeed.text  = forecast.wind
            itemView.tvHumidity.text = forecast.humidity
            Glide.with(itemView.context)
                .load("https://openweathermap.org/img/w/${forecast.icon}.png")
                .into(itemView.ivIcon)
        }
    }
}
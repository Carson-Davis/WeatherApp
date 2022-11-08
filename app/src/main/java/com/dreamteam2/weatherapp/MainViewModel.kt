package com.dreamteam2.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val weatherApi = WeatherApi()

    val status = MutableStateFlow<Status?>(null)
    val gridPointEndpoints = MutableStateFlow<GridPointEndpoints?>(null)
    val forecast = MutableStateFlow<Forecast?>(null)
    val forecastHourly =  MutableStateFlow<Forecast?>(null)
    val gridpointsProperties = MutableStateFlow<Gridpoints?>(null)

    init{
        viewModelScope.launch {
            kotlin.runCatching {
               // weatherApi.getForecast(31, 80)
                weatherApi.getGirdEndpoints(45.5648,-94.3180) // 39.0473 -95.6752 vs 45.5648 -94.3180
            }.onSuccess {
                gridPointEndpoints.value = it
            }.onFailure {
                gridPointEndpoints.value = null
            }

            kotlin.runCatching {
                weatherApi.getForecast("MPX", 72, 98) // TOP 31 80 vw MPX 72 98
            }.onSuccess {
                forecast.value = it
            }.onFailure {
                forecast.value = null
            }

            kotlin.runCatching {
                weatherApi.getForecastHourly("MPX", 72, 98)
            }.onSuccess {
                forecastHourly.value = it
            }.onFailure {
                forecastHourly.value = null
            }

            kotlin.runCatching {
                weatherApi.getGridpointProperties("MPX", 72, 98)
            }.onSuccess {
                gridpointsProperties.value = it
            }.onFailure {
                gridpointsProperties.value = null
            }

            kotlin.runCatching {
                weatherApi.getStatus()
            }.onSuccess {
                status.value = it
            }.onFailure {
                status.value = null
            }


        }
    }

}

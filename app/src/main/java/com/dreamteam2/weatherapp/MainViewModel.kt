package com.dreamteam2.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val weatherApi = WeatherApi()

    val status = MutableStateFlow<Status?>(null)
    val gridPointEndpoints = MutableStateFlow<GridPointEndpoints?>(null)
    val forecastEndPoints = MutableStateFlow<ForecastEndpoints?>(null)

    init{
        viewModelScope.launch {
            kotlin.runCatching {
               // weatherApi.getForecast(31, 80)
                weatherApi.getGirdEndpoints(39.7456,-97.0892)
            }.onSuccess {
                gridPointEndpoints.value = it
            }.onFailure {
                gridPointEndpoints.value = null
            }

            kotlin.runCatching {
                weatherApi.getForecast(31, 80)
            }.onSuccess {
                forecastEndPoints.value = it
            }.onFailure {
                forecastEndPoints.value = null
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

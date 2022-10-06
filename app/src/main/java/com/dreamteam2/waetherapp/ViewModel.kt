package com.dreamteam2.waetherapp

import com.dreamteam2.weatherapp.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    var gridPointEndpoints: GridPointEndpoints = GridPointEndpoints()

    fun getGirdEndpoints() = viewModelScope.launch {
        gridPointEndpoints = WeatherApi().getGirdEndpoints(39.7456,-97.0892)
    }
}
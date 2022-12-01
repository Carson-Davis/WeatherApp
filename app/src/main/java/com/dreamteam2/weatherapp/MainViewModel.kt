package com.dreamteam2.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

    /*
    MainViewModel
    -------------------------------------------------------------
    The class establishes the corresponding data classes needed for all the api calls.
    It then launches our viewmodel by getting the grid endpoints from the api.
    The viewmodel holds the fetched information along with updating when another call is made.
    @Returns it - This is the current state of the fetched API data that is updated and maintained by this class

     */
class MainViewModel: ViewModel() {

    private val weatherApi = WeatherApi()

    val status = MutableStateFlow<Status?>(null)
    val gridPointEndpoints = MutableStateFlow<GridPointEndpoints?>(null)
    val forecast = MutableStateFlow<Forecast?>(null)
    val forecastHourly =  MutableStateFlow<Forecast?>(null)
    val gridpointsProperties = MutableStateFlow<Gridpoints?>(null)

    private val coordinatesApi = CoordinatesAPI()
    val coordinates = MutableStateFlow<List<CoordinatesData>?>(null)

    init{
        //Launches viewmodel with a call to the WeatherAPI that fetches the standard generic output of the API
        viewModelScope.launch {
            kotlin.runCatching {
               // weatherApi.getForecast(31, 80)
                weatherApi.getGirdEndpoints(45.5648,-94.3180) // 39.0473 -95.6752 vs 45.5648 -94.3180
            }.onSuccess {
                gridPointEndpoints.value = it
            }.onFailure {
                gridPointEndpoints.value = null
            }

        //
            kotlin.runCatching {
                weatherApi.getForecast("MPX", 72, 98) // TOP 31 80 vw MPX 72 98
            }.onSuccess {
                forecast.value = it
            }.onFailure {
                forecast.value = null
            }

        //Call to WeatherAPI which fetches the hourly forecast
            kotlin.runCatching {
                weatherApi.getForecastHourly("MPX", 72, 98)
            }.onSuccess {
                forecastHourly.value = it
            }.onFailure {
                forecastHourly.value = null
            }

        //Call to WeatherAPI that gets the properties of a gridpoint
            kotlin.runCatching {
                weatherApi.getGridpointProperties("MPX", 72, 98)
            }.onSuccess {
                gridpointsProperties.value = it
            }.onFailure {
                gridpointsProperties.value = null
            }

            //Call to CoordinatesAPI for the city and state
            kotlin.runCatching {
                coordinatesApi.getCoordinates("Willmar", "Minnesota")
            }.onSuccess {
                coordinates.value = it
            }.onFailure {
                coordinates.value = null
            }

            //Call to WeatherAPI for the current status of the API
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

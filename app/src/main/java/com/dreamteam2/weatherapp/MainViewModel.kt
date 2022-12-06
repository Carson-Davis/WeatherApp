package com.dreamteam2.weatherapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

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

    /**
     * Design pattern implementation - State
     */
    val status = MutableStateFlow<Status?>(null)
    val gridPointEndpoints = MutableStateFlow<GridPointEndpoints?>(null)
    val forecast = MutableStateFlow<Forecast?>(null)
    val forecastHourly =  MutableStateFlow<Forecast?>(null)
    val gridpointsProperties = MutableStateFlow<Gridpoints?>(null)
    var lat = MutableStateFlow<Double?>(0.0)
    var long = MutableStateFlow<Double?>(0.0)
    private val coordinatesApi = CoordinatesAPI()
    val coordinates = MutableStateFlow<List<CoordinatesData>?>(null)
    val isCelsius = MutableStateFlow<Boolean?>(false)

    suspend fun getStatus(){
        //Call to WeatherAPI for the current status of the API
        kotlin.runCatching {
            weatherApi.getStatus()
        }.onSuccess {
            status.value = it
        }.onFailure {
            status.value = null
        }
    }

    suspend fun getGridEndpoints(){
        kotlin.runCatching {
            weatherApi.getGirdEndpoints(
                this.coordinates.value?.get(0)!!.lon?.toDouble()!!,
                this.coordinates.value?.get(0)!!.lat?.toDouble()!!
            ) // 39.0473 -95.6752 vs 45.5648 -94.3180
        }.onSuccess {
            gridPointEndpoints.value = it

        }.onFailure {
            gridPointEndpoints.value = null
        }
    }

    suspend fun getDailyForecast(){
        kotlin.runCatching {
            weatherApi.getForecast(this.gridPointEndpoints.value?.properties?.cwa!!, this.gridPointEndpoints.value?.properties?.gridX!!, this.gridPointEndpoints.value?.properties?.gridY!!) // TOP 31 80 vw MPX 72 98
        }.onSuccess {
            forecast.value = it
        }.onFailure {
            forecast.value = null
        }
    }

    suspend fun getHourlyForecast(){
        //Call to WeatherAPI which fetches the hourly forecast
        kotlin.runCatching {
            weatherApi.getForecastHourly(this.gridPointEndpoints.value?.properties?.cwa!!, this.gridPointEndpoints.value?.properties?.gridX!!, this.gridPointEndpoints.value?.properties?.gridY!!)
        }.onSuccess {
            forecastHourly.value = it
        }.onFailure {
            forecastHourly.value = null
        }
    }

    suspend fun getGridpointProperties(){
        //Call to WeatherAPI that gets the properties of a gridpoint
        kotlin.runCatching {
            weatherApi.getGridpointProperties(this.gridPointEndpoints.value?.properties?.cwa!!, this.gridPointEndpoints.value?.properties?.gridX!!, this.gridPointEndpoints.value?.properties?.gridY!!)
        }.onSuccess {
            gridpointsProperties.value = it
        }.onFailure {
            gridpointsProperties.value = null
        }
    }

    suspend fun fetchByString(searchString: String){
        //Call to CoordinatesAPI for the city and state
        kotlin.runCatching {
            coordinatesApi.getCoordinates(searchString)
        }.onSuccess {
            coordinates.value = it
        }.onFailure {
            coordinates.value = null
        }
        getStatus()
        getGridEndpoints()
        getDailyForecast()
        getHourlyForecast()
        getGridpointProperties()
    }

}


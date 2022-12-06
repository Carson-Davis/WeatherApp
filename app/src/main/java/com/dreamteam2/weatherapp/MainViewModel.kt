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
    var lat = MutableStateFlow<Double>(0.0)
    var long = MutableStateFlow<Double>(0.0)
    private val coordinatesApi = CoordinatesAPI()
    val coordinates = MutableStateFlow<List<CoordinatesData>?>(null)
    val coordinatesData = MutableStateFlow<CoordinatesData?>(null)
    val isCelsius = MutableStateFlow<Boolean?>(false)

    var loadStatus = MutableStateFlow(LoadStatus.notStarted)
    enum class LoadStatus{
        attempt, error, success, notStarted
    }


    suspend fun getStatus(){
        //Call to WeatherAPI for the current status of the API
        kotlin.runCatching {
            loadStatus.value = LoadStatus.attempt
            weatherApi.getStatus()
        }.onSuccess {
            loadStatus.value = LoadStatus.success
            status.value = it
        }.onFailure {
            loadStatus.value = LoadStatus.error
            status.value = null
        }
    }

    suspend fun getGridEndpoints(){
        kotlin.runCatching {
            loadStatus.value = LoadStatus.attempt
            weatherApi.getGirdEndpoints(
                this.coordinates.value?.get(0)!!.lon?.toDouble()!!,
                this.coordinates.value?.get(0)!!.lat?.toDouble()!!
            ) // 39.0473 -95.6752 vs 45.5648 -94.3180
        }.onSuccess {
            loadStatus.value = LoadStatus.success
            gridPointEndpoints.value = it
        }.onFailure {
            loadStatus.value = LoadStatus.error
            gridPointEndpoints.value = null
        }
    }

    suspend fun getDailyForecast(){
        kotlin.runCatching {
            loadStatus.value = LoadStatus.attempt
            weatherApi.getForecast(this.gridPointEndpoints.value?.properties?.cwa!!, this.gridPointEndpoints.value?.properties?.gridX!!, this.gridPointEndpoints.value?.properties?.gridY!!) // TOP 31 80 vw MPX 72 98
        }.onSuccess {
            loadStatus.value = LoadStatus.success
            forecast.value = it
        }.onFailure {
            loadStatus.value = LoadStatus.error
            forecast.value = null
        }
    }

    suspend fun getHourlyForecast(){
        //Call to WeatherAPI which fetches the hourly forecast
        kotlin.runCatching {
            loadStatus.value = LoadStatus.attempt
            weatherApi.getForecastHourly(this.gridPointEndpoints.value?.properties?.cwa!!, this.gridPointEndpoints.value?.properties?.gridX!!, this.gridPointEndpoints.value?.properties?.gridY!!)
        }.onSuccess {
            loadStatus.value = LoadStatus.success
            forecastHourly.value = it
        }.onFailure {
            loadStatus.value = LoadStatus.error
            forecastHourly.value = null
        }
    }

    suspend fun getGridpointProperties(){
        //Call to WeatherAPI that gets the properties of a gridpoint
        kotlin.runCatching {
            loadStatus.value = LoadStatus.attempt
            weatherApi.getGridpointProperties(this.gridPointEndpoints.value?.properties?.cwa!!, this.gridPointEndpoints.value?.properties?.gridX!!, this.gridPointEndpoints.value?.properties?.gridY!!)
        }.onSuccess {
            loadStatus.value = LoadStatus.success
            gridpointsProperties.value = it
        }.onFailure {
            loadStatus.value = LoadStatus.error
            gridpointsProperties.value = null
        }
    }

    suspend fun fetchByString(searchString: String){
        //Call to CoordinatesAPI for the city and state
        kotlin.runCatching {
            loadStatus.value = LoadStatus.attempt
            coordinatesApi.getCoordinates(searchString)
        }.onSuccess {
            loadStatus.value = LoadStatus.success
            coordinates.value = it
            getStatus()
            getGridEndpoints()
            getDailyForecast()
            getHourlyForecast()
            getGridpointProperties()
        }.onFailure {
            loadStatus.value = LoadStatus.error
            coordinates.value = null
        }

    }
//    suspend fun fetchByCoordinates(lat : Double, long : Double){
//        kotlin.runCatching {
//            weatherApi.getGirdEndpoints(long, lat)
//        }.onSuccess {
//            gridPointEndpoints.value = it
//            getStatus()
//            getDailyForecast()
//            getHourlyForecast()
//            getGridpointProperties()
//        }.onFailure {
//            gridPointEndpoints.value = null
//        }
//
//    }
}


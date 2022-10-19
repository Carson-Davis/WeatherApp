package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastEndpoints(

    @SerialName("type")
    var properties: String? = "",
    @SerialName("geometry")
    var geometry: Geometry? = Geometry(),
    //@SerialName("coordinates")
    //var coordinates: List<List<Double>>? = emptyList(),
    @SerialName("properties")
    var propertiesInForecast: PropertiesInForecast? = PropertiesInForecast(),

    )
package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridPointEndpointsPropertiesRelativeLocationGeometry(
    @SerialName("type")
    var type: String? = "",
    //@SerialName("coordinates")
    //var coordinates: List<List<Double>> = arrayListOf()
)
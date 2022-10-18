package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerialName("type")
    var type: String? = "",
    @SerialName("coordinates")
    var coordinates: ArrayList<Double> = arrayListOf()
)
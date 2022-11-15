package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesAPIGeometry(
    @SerialName("type")
    val type: String? = "",
    @SerialName("coordinates")
    val coords: List<Double>? = emptyList(),

    )
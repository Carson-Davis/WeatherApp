package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Elevation(
    @SerialName("unitCode")
    val unitCode: String? = "",
    @SerialName("value")
    val value: Double? = 0.0,
)
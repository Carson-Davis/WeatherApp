package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CoordinatesAPIFeatures(
    @SerialName("type")
    val type: String? = "",
    @SerialName("geometry")
    val geometry: CoordinatesAPIGeometry? = CoordinatesAPIGeometry(),

    )
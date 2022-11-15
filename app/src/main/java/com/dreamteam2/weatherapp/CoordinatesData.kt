package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesData(
    @SerialName("zip_code")
    val zipCode: String? = "",
    @SerialName("lat")
    val lat: Double? = 0.0,
    @SerialName("long")
    val lng: Double? = 0.0,
    @SerialName("city")
    val city: String? = "",
    @SerialName("state")
    val state: String? = "",
    @SerialName("timezone_abbr")
    val timezone_abbr: String? = "",
)
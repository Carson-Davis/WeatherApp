package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelativeLocationProperties(
    @SerialName("city")
    var city: String? = "",
    @SerialName("state")
    var state: String? = "",
    @SerialName("distance")
    var geometry: Distance? = Distance(),
    @SerialName("bearing")
    var bearing: Bearing? = Bearing()
)
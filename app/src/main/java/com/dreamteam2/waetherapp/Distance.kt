package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Distance(
    @SerialName("unitCode")
    var unitCode: String? = null,
    @SerialName("value")
    var value: Double? = null
)
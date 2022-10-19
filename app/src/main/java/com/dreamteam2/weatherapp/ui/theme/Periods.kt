package com.dreamteam2.weatherapp.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Periods(
    @SerialName("unitCode")
    var unitCode: String? = null,
    @SerialName("value")
    var value: Int? = null
)
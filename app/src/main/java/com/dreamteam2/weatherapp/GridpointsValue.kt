package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GridpointsValue (
        @SerialName("validTime")
        var validTime : String? = "",
        @SerialName("value")
        var value : Double? = 0.0
        )


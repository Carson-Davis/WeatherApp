package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gridpoints (
    @SerialName("@id")
    var id: String? = "",
    @SerialName("properties")
    var properties: GridpointsProperties? = GridpointsProperties()
)
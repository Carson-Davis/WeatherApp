package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridPointEndpointsPropertiesRelativeLocation(
    @SerialName("type")
    var type: String? = "",
    @SerialName("geometry")
    var geometry: GridPointEndpointsPropertiesRelativeLocationGeometry? = GridPointEndpointsPropertiesRelativeLocationGeometry(),
    @SerialName("properties")
    var properties: GridPointEndpointsPropertiesRelativeLocationProperties? = GridPointEndpointsPropertiesRelativeLocationProperties()
)
package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridPointEndpointsPropertiesRelativeLocationProperties(
    @SerialName("city")
    var city: String? = "",
    @SerialName("state")
    var state: String? = "",
    @SerialName("distance")
    var distance: GridPointEndpointsPropertiesRelativeLocationPropertiesDistance? = GridPointEndpointsPropertiesRelativeLocationPropertiesDistance(),
    @SerialName("bearing")
    var bearing: GridPointEndpointsPropertiesRelativeLocationPropertiesBearing? = GridPointEndpointsPropertiesRelativeLocationPropertiesBearing()
)
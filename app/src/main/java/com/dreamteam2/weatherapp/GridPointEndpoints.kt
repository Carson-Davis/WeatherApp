package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridPointEndpoints(
    @SerialName("id")
    var id: String? = "",
    @SerialName("properties")
    var properties: GridPointEndpointsProperties? = GridPointEndpointsProperties(),
    @SerialName("type")
    var type: String? = "",
    @SerialName("geometry")
    var geometry: GridPointEndpointsPropertiesRelativeLocationGeometry? = GridPointEndpointsPropertiesRelativeLocationGeometry()
//    ignoring @context
//    @SerialName("@context")
//    var context: ArrayList<String> = arrayListOf(),
)
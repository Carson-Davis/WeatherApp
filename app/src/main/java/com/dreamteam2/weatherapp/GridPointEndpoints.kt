package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridPointEndpoints(
    @SerialName("@context")
    var context: ArrayList<String> = arrayListOf(),
    @SerialName("id")
    var id: String? = null,
    @SerialName("type")
    var type: String? = null,
    @SerialName("geometry")
    var geometry: Geometry? = Geometry(),
    @SerialName("properties")
    var properties: Properties? = Properties()
)
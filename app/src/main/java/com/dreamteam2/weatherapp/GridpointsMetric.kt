package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridpointsMetric (
    @SerialName("uom")
    var uom: String? = "",
    @SerialName("values")
    var values: List<GridpointsValue>? = emptyList()
)
package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("@id")
    var id: String? = null,
    @SerialName("@type")
    var type: String? = null,
    @SerialName("cwa")
    var cwa: String? = null,
    @SerialName("forecastOffice")
    var forecastOffice: String? = null,
    @SerialName("gridId")
    var gridId: String? = null,
    @SerialName("gridX")
    var gridX: Int? = null,
    @SerialName("gridY")
    var gridY: Int? = null,
    @SerialName("forecast")
    var forecast: String? = null,
    @SerialName("forecastHourly")
    var forecastHourly: String? = null,
    @SerialName("forecastGridData")
    var forecastGridData: String? = null,
    @SerialName("observationStations")
    var observationStations: String? = null,
    @SerialName("relativeLocation")
    var relativeLocation: RelativeLocation? = RelativeLocation(),
    @SerialName("forecastZone")
    var forecastZone: String? = null,
    @SerialName("county")
    var county: String? = null,
    @SerialName("fireWeatherZone")
    var fireWeatherZone: String? = null,
    @SerialName("timeZone")
    var timeZone: String? = null,
    @SerialName("radarStation")
    var radarStation: String? = null
)
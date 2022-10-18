package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("@id")
    var id: String? = "",
    @SerialName("@type")
    var type: String? = "",
    @SerialName("cwa")
    var cwa: String? = "",
    @SerialName("forecastOffice")
    var forecastOffice: String? = "",
    @SerialName("gridId")
    var gridId: String? = "",
    @SerialName("gridX")
    var gridX: Int? = 0,
    @SerialName("gridY")
    var gridY: Int? = 0,
    @SerialName("forecast")
    var forecast: String? = "",
    @SerialName("forecastHourly")
    var forecastHourly: String? = "",
    @SerialName("forecastGridData")
    var forecastGridData: String? = "",
    @SerialName("observationStations")
    var observationStations: String? = "",
    @SerialName("forecastZone")
    var forecastZone: String? = "",
    @SerialName("county")
    var county: String? = "",
    @SerialName("fireWeatherZone")
    var fireWeatherZone: String? = "",
    @SerialName("timeZone")
    var timeZone: String? = "",
    @SerialName("radarStation")
    var radarStation: String? = "",
    @SerialName("relativeLocation")
    var relativeLocation: RelativeLocation? = RelativeLocation(),
)
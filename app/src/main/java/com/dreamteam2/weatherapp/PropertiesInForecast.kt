package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PropertiesInForecast(
    @SerialName("updated")
    var id: String? = "",
    @SerialName("units")
    var type: String? = "",
    @SerialName("forecastGenerator")
    var cwa: String? = "",
    @SerialName("generatedAt")
    var forecastOffice: String? = "",
    @SerialName("updateTime")
    var gridId: String? = "",
    @SerialName("validTimes")
    var gridX: String? = "",
    @SerialName("elevation")
    var elevation: Elevation? = Elevation(),
    @SerialName("periods")
    var period: List<Period>?= emptyList(),
)

package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Period(
    @SerialName("number")
    var number: Int? = 0,
    @SerialName("name")
    var name: String? = "",
    @SerialName("startTime")
    var startTime: String? = "",
    @SerialName("endTime")
    var endTime: String? = "",
    @SerialName("isDaytime")
    var isDaytime: Boolean? = null,
    @SerialName("temperature")
    var temperature: Int? = 0,
    @SerialName("temperatureUnit")
    var temperatureUnit: String? = "",
    @SerialName("temperatureTrend")
    var temperatureTrend: String? = null,
    @SerialName("windSpeed")
    var windSpeed: String? = "",
    @SerialName("windDirection")
    var windDirection: String? = "",
    @SerialName("icon")
    var icon: String? = "",
    @SerialName("shortForecast")
    var shortForecast: String? = "",
    @SerialName("detailedForecast")
    var detailedForecast: String? = "",
)
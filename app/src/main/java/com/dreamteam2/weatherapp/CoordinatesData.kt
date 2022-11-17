package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesData(

        @SerialName("place_id")
        val place_id: Long? = 0,
        @SerialName("license")
        val license: String? = "",
        @SerialName("osm_type")
        val osmType: String? = "",
        @SerialName("osm_id")
        val osmId: Long? = 0,
        @SerialName("boundingbox")
        val boundingbox: List<Double>? = emptyList(),
        @SerialName("lat")
        val lat: String? = "",
        @SerialName("lon")
        val lon: String? = "",
        @SerialName("display_name")
        val displayName: String? = "",
        @SerialName("class")
        val classVar: String? = "",
        @SerialName("type")
        val display_name: String? = "",
        @SerialName("importance")
        val importance: Double? = 0.0,
        @SerialName("icon")
        val icon: String? = "",

    )
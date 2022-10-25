package com.dreamteam2.weatherapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GridpointsProperties (
    @SerialName("id")
    var id: String? = "",
//  @SerialName("@type") 
//  var @type: String?                           = null,
//  @SerialName("updateTime") 
//  var updateTime: String?                           = null,
//  @SerialName("validTimes") 
//  var validTimes: String?                           = null,
//  @SerialName("elevation") 
//  var elevation: Elevation?                        = Elevation(),
//  @SerialName("forecastOffice") 
//  var forecastOffice: String?                           = null,
//  @SerialName("gridId") 
//  var gridId                           : String?                           = null,
//  @SerialName("gridX") 
//  var gridX                            : String?                           = null,
//  @SerialName("gridY") 
//  var gridY                            : String?                           = null,

    @SerialName("temperature")
    var temperature: GridpointsMetric? = GridpointsMetric(),
    @SerialName("dewpoint")
    var dewpoint: GridpointsMetric? = GridpointsMetric(),
    @SerialName("maxTemperature")
    var maxTemperature: GridpointsMetric? = GridpointsMetric(),
    @SerialName("minTemperature")
    var minTemperature: GridpointsMetric? = GridpointsMetric(),
    @SerialName("relativeHumidity")
    var relativeHumidity: GridpointsMetric? = GridpointsMetric(),
    @SerialName("apparentTemperature")
    var apparentTemperature: GridpointsMetric? = GridpointsMetric(),
    @SerialName("heatIndex")
    var heatIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("windChill")
    var windChill: GridpointsMetric? = GridpointsMetric(),
    @SerialName("skyCover")
    var skyCover: GridpointsMetric? = GridpointsMetric(),
    @SerialName("windDirection")
    var windDirection: GridpointsMetric? = GridpointsMetric(),
    @SerialName("windSpeed")
    var windSpeed: GridpointsMetric? = GridpointsMetric(),
    @SerialName("windGust")
    var windGust: GridpointsMetric? = GridpointsMetric(),

    //@SerialName("weather")
    //var weather                          : Weather?                          = Weather(),
    //@SerialName("hazards")
    //var hazards                          : Hazards?                          = Hazards(),

    @SerialName("probabilityOfPrecipitation")
    var probabilityOfPrecipitation: GridpointsMetric? = GridpointsMetric(),
    @SerialName("quantitativePrecipitation")
    var quantitativePrecipitation: GridpointsMetric? = GridpointsMetric(),
    @SerialName("iceAccumulation")
    var iceAccumulation: GridpointsMetric? = GridpointsMetric(),
    @SerialName("snowfallAmount")
    var snowfallAmount: GridpointsMetric? = GridpointsMetric(),
    @SerialName("snowLevel")
    var snowLevel: GridpointsMetric? = GridpointsMetric(),
    @SerialName("ceilingHeight")
    var ceilingHeight: GridpointsMetric? = GridpointsMetric(),
    @SerialName("visibility")
    var visibility: GridpointsMetric? = GridpointsMetric(),
    @SerialName("transportWindSpeed")
    var transportWindSpeed: GridpointsMetric? = GridpointsMetric(),
    @SerialName("transportWindDirection")
    var transportWindDirection: GridpointsMetric? = GridpointsMetric(),
    @SerialName("mixingHeight")
    var mixingHeight: GridpointsMetric? = GridpointsMetric(),
    @SerialName("hainesIndex")
    var hainesIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("lightningActivityLevel")
    var lightningActivityLevel: GridpointsMetric? = GridpointsMetric(),
    @SerialName("twentyFootWindSpeed")
    var twentyFootWindSpeed: GridpointsMetric? = GridpointsMetric(),
    @SerialName("twentyFootWindDirection")
    var twentyFootWindDirection: GridpointsMetric? = GridpointsMetric(),
    @SerialName("waveHeight")
    var waveHeight: GridpointsMetric? = GridpointsMetric(),
    @SerialName("wavePeriod")
    var wavePeriod: GridpointsMetric? = GridpointsMetric(),
    @SerialName("waveDirection")
    var waveDirection: GridpointsMetric? = GridpointsMetric(),
    @SerialName("primarySwellHeight")
    var primarySwellHeight: GridpointsMetric? = GridpointsMetric(),
    @SerialName("primarySwellDirection")
    var primarySwellDirection: GridpointsMetric? = GridpointsMetric(),
    @SerialName("secondarySwellHeight")
    var secondarySwellHeight: GridpointsMetric? = GridpointsMetric(),
    @SerialName("secondarySwellDirection")
    var secondarySwellDirection: GridpointsMetric? = GridpointsMetric(),
    @SerialName("wavePeriod2")
    var wavePeriod2: GridpointsMetric? = GridpointsMetric(),
    @SerialName("windWaveHeight")
    var windWaveHeight: GridpointsMetric? = GridpointsMetric(),
    @SerialName("dispersionIndex")
    var dispersionIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("pressure")
    var pressure: GridpointsMetric? = GridpointsMetric(),
    @SerialName("probabilityOfTropicalStormWinds")
    var probabilityOfTropicalStormWinds: GridpointsMetric? = GridpointsMetric(),
    @SerialName("probabilityOfHurricaneWinds")
    var probabilityOfHurricaneWinds: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf15mphWinds")
    var potentialOf15mphWinds: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf25mphWinds")
    var potentialOf25mphWinds: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf35mphWinds")
    var potentialOf35mphWinds: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf45mphWinds")
    var potentialOf45mphWinds: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf20mphWindGusts")
    var potentialOf20mphWindGusts: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf30mphWindGusts")
    var potentialOf30mphWindGusts: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf40mphWindGusts")
    var potentialOf40mphWindGusts: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf50mphWindGusts")
    var potentialOf50mphWindGusts: GridpointsMetric? = GridpointsMetric(),
    @SerialName("potentialOf60mphWindGusts")
    var potentialOf60mphWindGusts: GridpointsMetric? = GridpointsMetric(),
    @SerialName("grasslandFireDangerIndex")
    var grasslandFireDangerIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("probabilityOfThunder")
    var probabilityOfThunder: GridpointsMetric? = GridpointsMetric(),
    @SerialName("davisStabilityIndex")
    var davisStabilityIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("atmosphericDispersionIndex")
    var atmosphericDispersionIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("lowVisibilityOccurrenceRiskIndex")
    var lowVisibilityOccurrenceRiskIndex: GridpointsMetric? = GridpointsMetric(),
    @SerialName("stability")
    var stability: GridpointsMetric? = GridpointsMetric(),
    @SerialName("redFlagThreatIndex")
    var redFlagThreatIndex: GridpointsMetric? = GridpointsMetric()

)
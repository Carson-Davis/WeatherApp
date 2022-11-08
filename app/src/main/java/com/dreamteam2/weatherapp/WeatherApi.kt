package com.dreamteam2.weatherapp

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.engine.cio.*

class WeatherApi {
    private val httpClient = HttpClient(CIO) {
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.weather.gov"
            }
            header("X-Custom-Header", "WeatherApp")
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        install(Logging){
            logger = object : Logger {
                override fun log(message: String){
                    Log.i("Network", message)
                }
            }
            level = LogLevel.INFO
        }
    }

    @Throws(Exception::class)
    suspend fun getStatus(): Status {
        val status: Status = httpClient.get {
            url {
                appendPathSegments("")
            }
        }.body()
        return status
    }

    @Throws(Exception::class)
    suspend fun getGirdEndpoints(long: Double, lat: Double): GridPointEndpoints {
        val girdPontEndpoints: GridPointEndpoints = httpClient.get {
            url {
                appendPathSegments("points", "$long,$lat")
            }
        }.body()
        return girdPontEndpoints
    }

    @Throws(Exception::class)
    suspend fun getForecast(cwa: String, x: Int, y: Int): Forecast {
        val foreCast: Forecast = httpClient.get {
            url {
                appendPathSegments("gridpoints", cwa, "$x,$y", "forecast")
            }
        }.body()
        return foreCast
    }

    @Throws(Exception::class)
    suspend fun getForecastHourly(cwa: String, x: Int, y: Int): Forecast {
        val foreCast: Forecast = httpClient.get {
            url {
                appendPathSegments("gridpoints", cwa, "$x,$y", "forecast", "hourly")
            }
        }.body()
        return foreCast
    }

    @Throws(Exception::class)
    suspend fun getGridpointProperties(cwa: String, x: Int, y: Int): Gridpoints {
        val gridpointsProperties: Gridpoints = httpClient.get {
            url {
                appendPathSegments("gridpoints", cwa, "$x,$y")
            }
        }.body()
        return gridpointsProperties
    }
}

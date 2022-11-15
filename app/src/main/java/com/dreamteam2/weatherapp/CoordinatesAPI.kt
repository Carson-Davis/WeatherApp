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

class CoordinatesAPI {
    private val httpClient = HttpClient(CIO) {
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "nominatim.openstreetmap"
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

    //https://nominatim.openstreetmap.org/search?q=willmar+Minnesota&format=geocodejson
    @Throws(Exception::class)
    suspend fun getCoordinates(city: String?, state: String?): CoordinatesData {

        val coord: CoordinatesData = httpClient.get {
            url {
                host = "nominatim.openstreetmap.org/search/"
                //&format=geocodejson
                parameters.append("q", "Willmar")
                parameters.append("format", "geocodejson")
            }
        }.body()
        return coord
    }


}

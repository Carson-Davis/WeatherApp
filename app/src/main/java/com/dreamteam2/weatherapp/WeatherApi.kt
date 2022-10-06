package com.dreamteam2.weatherapp

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

class WeatherApi {
    private val httpClient = HttpClient {
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
    }

    @Throws(Exception::class)
    suspend fun testText(): String {
        val girdPointEndpoints: GridPointEndpoints = getGirdEndpoints(39.7456,-97.0892)
        return "${girdPointEndpoints.properties?.radarStation} 🚀"
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
}

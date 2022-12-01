package com.dreamteam2.weatherapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WeatherApiTest {
    val weatherApi = WeatherApi()
//    var weatherApi: WeatherApi? = null
//    @Before
//    fun before() = runTest {
//        weatherApi = WeatherApi()
//    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    suspend fun fetchStatus(): Status {
//        val status = weatherApi.getStatus()
//        return status
//    }
    @Test
    fun testGetStatus()  = runTest {
//        val weatherApi = WeatherApi()
//        val status = weatherApi.getStatus()
//        runBlocking { val status = weatherApi.getStatus() }
        val status = weatherApi?.getStatus()
        assertEquals(4, 2 + 2)
    }

    suspend fun fetchData(): String {
        delay(1000L)
        return "Hello world"
    }

    @Test
    fun dataShouldBeHelloWorld() = runTest {
        val data = fetchData()
        assertEquals("Hello world", data)
    }
}
package com.dreamteam2.weatherapp

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.testng.annotations.BeforeTest
//import org.testng.log4testng.Logger

class WeatherApiTest {
    private val weatherApi = WeatherApi()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testGetStatus()  = runTest {
        val status = weatherApi.getStatus()
        assertEquals(status.status, "OK")
    }

    @Test
    fun testGetGirdEndpoints()  = runTest {
        val gridPointEndpoints = weatherApi.getGirdEndpoints(-97.0892, 39.7456)
        assertEquals("TOP", gridPointEndpoints?.properties?.cwa)
    }

    @Test
    fun testGetForecast()  = runTest {
        val getForecast = weatherApi.getForecast("TOP", 80, 31)
        assertEquals("us", getForecast?.propertiesInForecast?.type)
    }git

    @Test
    fun testGetForecastHourly()  = runTest {
        val getForecastHourly = weatherApi.getForecastHourly("TOP", 80, 31)
        assertEquals("us", getForecastHourly?.propertiesInForecast?.type)
    }

    @Test
    fun testGetGridpointProperties()  = runTest {
        val getGridPointEndpoints = weatherApi.getGridpointProperties("TOP", 80, 31)
        assertEquals("wmoUnit:degC", getGridPointEndpoints?.properties?.temperature?.uom)
    }

}
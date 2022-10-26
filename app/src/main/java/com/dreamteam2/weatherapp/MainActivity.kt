package com.dreamteam2.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.dreamteam2.weatherapp.ui.theme.WeatherAppTheme


class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel = MainViewModel()
    val secondView: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        //today(viewModel)
                        dailyForecast(viewModel)
                        hourly(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun dailyForecast(viewModel: MainViewModel) {
    //for (i in 0..13){
    val foreCastPointEndpointTemperature by viewModel.forecast.collectAsState()
    Column() {
        foreCastPointEndpointTemperature?.let {
            for (i in 0..13) {
                Text(text = it.propertiesInForecast?.period?.get(i)?.name.toString() + ": " + it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F")
            }
        } ?: Text(text = "Loading...")
    }
}

@Composable
fun today(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    val gridpointProperties by viewModel.gridpointsProperties.collectAsState()
    Column(){
        Text(text = "Daily Forecast: " + forecastHourly?.propertiesInForecast?.period?.get(0)?.temperature.toString() + "°F")
        Text(text = "Humidity: %" + gridpointProperties?.properties?.relativeHumidity?.values?.get(0)?.value.toString())
        Text(text = "Dewpoint: %" + gridpointProperties?.properties?.dewpoint?.values?.get(0)?.value.toString())
        Text(text = "Cloud Coverage: %" + gridpointProperties?.properties?.skyCover?.values?.get(0)?.value.toString())

    }
}

@Composable
fun hourly(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    Column() {
        forecastHourly?.let {
            for (i in 0..13) {
                Text(text = it.propertiesInForecast?.period?.get(i)?.startTime.toString() + ": " + it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F")
            }
        } ?: Text(text = "Loading...")
    }
}

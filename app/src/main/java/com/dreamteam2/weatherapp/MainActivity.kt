package com.dreamteam2.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dreamteam2.weatherapp.ui.theme.WeatherAppTheme


class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel = MainViewModel()
    val secondView: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(

                    color = MaterialTheme.colors.background


                ) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        today(viewModel)
                        dailyForecast(viewModel = viewModel)
                        hourly(viewModel = viewModel)
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
    Column(modifier = Modifier.absoluteOffset(0.dp, 300.dp)) {
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
    val gridPointEndpoints by viewModel.gridPointEndpoints.collectAsState()
    Column(modifier = Modifier.absoluteOffset(0.dp, 30.dp)){
        Text(
            text = "City: " + gridPointEndpoints?.properties?.relativeLocation.toString(),
            //text = "Minnesooota",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .offset(-6.dp, 0.dp)
        )
        Text(
            text = forecastHourly?.propertiesInForecast?.period?.get(0)?.temperature.toString() + "°",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 50.sp,
            //fontStyle = GenericFontFamily("monospace")
        )
        Text(
            text = forecastHourly?.propertiesInForecast?.period?.get(0)?.shortForecast.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp

        )

        Row(
            modifier = Modifier.padding(0.dp)
        ){
            Text(
                text = "H: " + gridpointProperties?.properties?.minTemperature?.values?.get(0)?.value.toString().subSequence(0, 1) + "°",
                modifier = Modifier.offset(15.dp, 0.dp))
            Text(
                text = "L: " + gridpointProperties?.properties?.maxTemperature?.values?.get(0)?.value.toString().subSequence(0, 1) + "°",
                modifier = Modifier.offset(45.dp, 0.dp)
            )
        }
        //Text(text = "Humidity: %" + gridpointProperties?.properties?.relativeHumidity?.values?.get(0)?.value.toString())
       // Text(text = "Dewpoint: %" + gridpointProperties?.properties?.dewpoint?.values?.get(0)?.value.toString())
        //Text(text = "Cloud Coverage: %" + gridpointProperties?.properties?.skyCover?.values?.get(0)?.value.toString())
    }
}

@Composable
fun hourly(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    Column(modifier = Modifier.absoluteOffset(0.dp, 500.dp)) {
        forecastHourly?.let {
            for (i in 0..13) {
                Text(text = it.propertiesInForecast?.period?.get(i)?.startTime.toString() + ": " + it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F")
            }
        } ?: Text(text = "Loading...")
    }
}

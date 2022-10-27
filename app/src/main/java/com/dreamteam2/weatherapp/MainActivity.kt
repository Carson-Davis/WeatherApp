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
import kotlin.math.roundToInt


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
    Column(modifier = Modifier.absoluteOffset(0.dp, 30.dp)) {
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
            modifier = Modifier.padding(0.dp).align(alignment = Alignment.CenterHorizontally)
        ) {
            var minTemp: Double? =
                gridpointProperties?.properties?.minTemperature?.values?.get(0)?.value
            var minTempString: String
            if (minTemp == null) {
                minTempString = "Loading"
            } else {
                minTempString = (minTemp * 1.8 + 32).toString()
            }

            var maxTemp: Double? =
                gridpointProperties?.properties?.maxTemperature?.values?.get(0)?.value
            var maxTempString: String
            if (maxTemp == null) {
                maxTempString = "Loading"
            } else {
                maxTempString = (maxTemp * 1.8 + 32).toString()
            }
            Text(
                text = "H: " + maxTempString + "°           "//,
                //modifier = Modifier.offset(15.dp, 0.dp))

                //.subSequence(0, 1)
            )
            Text(
                text = "L: " + minTempString + "°"//,
                //modifier = Modifier.offset(45.dp, 0.dp)
            )
        }
        var aTemp: Double? =
            gridpointProperties?.properties?.apparentTemperature?.values?.get(0)?.value
        var aTempString: String
        if (aTemp == null) {
            aTempString = "Loading"
        } else {
            aTempString = (aTemp * 1.8 + 32).toString()
        }
        Text(
            text = "Feels Like: " + aTempString + "°F",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp

        )
        var windS: Double? = gridpointProperties?.properties?.windSpeed?.values?.get(0)?.value
        var windSString: String
        if (windS == null){
            windSString = "Loading"
        }else{
            windSString = ((windS * 0.621371192).roundToInt()).toString()
        }
        var windD: Double? = gridpointProperties?.properties?.windDirection?.values?.get(0)?.value
        var windDString: String
        if (aTemp == null) {
            windDString = "Loading"
        }else if (22.0 < windD!! && windD!! <  68.0){
            windDString = "NE"
        }else if (67.0 < windD!! && windD!! <  113.0){
            windDString = "E"
        }else if (112.0 < windD!! && windD!! <  158.0){
            windDString = "SE"
        }else if (157.0 < windD!! && windD!! <  203.0){
            windDString = "S"
        }else if (202.0 < windD!! && windD!! <  248.0){
            windDString = "SW"
        }else if (247.0 < windD!! && windD!! <  293.0){
            windDString = "W"
        }else if (292.0 < windD!! && windD!! <  338.0){
            windDString = "NW"
        }else{
            windDString = "N"
        }
        Text(
            text = "Wind: " + windSString + " mph " + windDString,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp

        )
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

package com.dreamteam2.weatherapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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

                    Column(modifier = Modifier
                        .fillMaxSize()
                        //.background(Color.Red)  //COME BACK HERE
                        .verticalScroll(rememberScrollState()),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        city(viewModel)
                        Spacer(modifier = Modifier.height(20.dp))
                        hourly(viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        today2(viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        dailyForecast(viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        bottom(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun city(viewModel: MainViewModel){
    val gridPointEndpoints by viewModel.gridPointEndpoints.collectAsState()
    Text(
        text = gridPointEndpoints?.properties?.relativeLocation?.properties?.city.toString() + ", " + gridPointEndpoints?.properties?.relativeLocation?.properties?.state.toString(),
        //text = "Minnesooota",
        textAlign = TextAlign.Center,
        modifier = Modifier
            //.align(alignment = Alignment.CenterHorizontally)
            .offset(-6.dp, 0.dp),
        fontSize = 30.sp
    )
}


@Composable
fun dailyForecast(viewModel: MainViewModel) {
    //for (i in 0..13){
    val foreCastPointEndpointTemperature by viewModel.forecast.collectAsState()
    Row(
        modifier = Modifier
            .size(360.dp, 400.dp)
            .background(Color.Cyan, shape = RoundedCornerShape(25.dp))
            .horizontalScroll(rememberScrollState())
            .offset(0.dp, 0.dp)
            .padding(25.dp)
            .alpha(1.0F)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.offset(45.dp, 0.dp),

        ) {
            foreCastPointEndpointTemperature?.let {
                for (i in 0..13) {
                    Text(
                        text = it.propertiesInForecast?.period?.get(i)?.name.toString(),
                        fontSize = 19.sp
                        //textAlign = TextAlign.Center,
                        //modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    Divider(color = Color.Red, thickness = 4.dp)
                } //For loop bracket

            } ?: Text(text = "Loading...")
        }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(90.dp, 0.dp),
                                    //.padding(.dp)
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                foreCastPointEndpointTemperature?.let {
                    for (i in 0..13) {
                        Text(
                            text = it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                        Divider(color = Color.Red, thickness = 1.dp)
                    } //For loop bracket
                } ?: Text(text = "Loading...")

            }

    }
}

@Composable
fun bottom(viewModel: MainViewModel){
    val gridpointProperties by viewModel.gridpointsProperties.collectAsState()
    Row(){

        Text(text = "Humidity: " + gridpointProperties?.properties?.relativeHumidity?.values?.get(0)?.value.toString()+"%" ,
            textAlign = TextAlign.Center,
            modifier = Modifier,
            fontSize = 20.sp)

        var dewpoint: Double? =
            gridpointProperties?.properties?.dewpoint?.values?.get(0)?.value
        var dewpointString: String
        if (dewpoint == null) {
            dewpointString = "Loading"
        } else {
            dewpointString = (dewpoint * 1.8 + 32).toString()
        }
        /*
        Text(text = "Dewpoint: " + dewpointString+" °F",
            textAlign = TextAlign.Center,
            modifier = Modifier,
            fontSize = 20.sp)
            */

        if(!(gridpointProperties?.properties?.potentialOf50mphWindGusts?.values.isNullOrEmpty())){
            Text(text = "Wind Advisory: " + gridpointProperties?.properties?.potentialOf50mphWindGusts?.values)
        }
    }
    Row(){
        Text(text = "Cloud Coverage: " + gridpointProperties?.properties?.skyCover?.values?.get(0)?.value.toString()+ "%",
            textAlign = TextAlign.Center,
            modifier = Modifier,
            fontSize = 20.sp)
    }
}

@Composable
fun today2(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    val gridpointProperties by viewModel.gridpointsProperties.collectAsState()
    val gridPointEndpoints by viewModel.gridPointEndpoints.collectAsState()
    Row(
        modifier = Modifier
                .height(IntrinsicSize.Max)
    ){
        Column(
            modifier = Modifier.width(130.dp)
                //.align(alignment = Alignment.CenterVertically)
                //.offset(0.dp, 12.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,

        ) {
            Text(
                text = forecastHourly?.propertiesInForecast?.period?.get(0)?.temperature.toString() + "°",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                fontSize = 70.sp,
                //fontStyle = GenericFontFamily("monospace")
            )
        }
        Column(
            modifier = Modifier.width(220.dp)
        ) {
            Row(

        ) {
                Text(
                    text = forecastHourly?.propertiesInForecast?.period?.get(0)?.shortForecast.toString(),
                    //textAlign = TextAlign.Center,
                    modifier = Modifier,
                    fontSize = 34.sp,
                    //fontStyle = GenericFontFamily("monospace")
                )
            }
            Row() {
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
                    text = "H: " + maxTempString + "°    ",
                    //modifier = Modifier.offset(15.dp, 0.dp))
                    fontSize = 25.sp
                    //.subSequence(0, 1)
                )
                Text(
                    text = "L: " + minTempString + "°",
                    //modifier = Modifier.offset(45.dp, 0.dp)
                    fontSize = 25.sp
                )
            }

            Row() {
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
                    modifier = Modifier,
                    fontSize = 20.sp
                )
            }
            Row(){
                var aTemp: Double? =
                    gridpointProperties?.properties?.apparentTemperature?.values?.get(0)?.value
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
                    modifier = Modifier,
                    fontSize = 20.sp

                )
            }
        }
    }
}

@Composable
fun today(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    val gridpointProperties by viewModel.gridpointsProperties.collectAsState()
    val gridPointEndpoints by viewModel.gridPointEndpoints.collectAsState()
    Column(
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp)
    ) {

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
            modifier = Modifier
                .padding(0.dp)
                .align(alignment = Alignment.CenterHorizontally)
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
        Text(text = "Humidity: " + gridpointProperties?.properties?.relativeHumidity?.values?.get(0)?.value.toString()+"%" ,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp)

        var dewpoint: Double? =
            gridpointProperties?.properties?.dewpoint?.values?.get(0)?.value
        var dewpointString: String
        if (dewpoint == null) {
            dewpointString = "Loading"
        } else {
            dewpointString = (dewpoint * 1.8 + 32).toString()
        }
        Text(text = "Dewpoint: " + dewpointString+" °F",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp)
        Text(text = "Cloud Coverage: " + gridpointProperties?.properties?.skyCover?.values?.get(0)?.value.toString()+ "%",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp)
        if(!(gridpointProperties?.properties?.potentialOf50mphWindGusts?.values.isNullOrEmpty())){
            Text(text = "Wind Advisory: " + gridpointProperties?.properties?.potentialOf50mphWindGusts?.values)
        }
    }
}

@Composable
fun hourly(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    //Column(
       // modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp)
    //) {
        /*
        Box(
            modifier = Modifier
                .size(300.dp, 180.dp)
                .background(Color.Cyan, shape = RoundedCornerShape(25.dp))
                .horizontalScroll(rememberScrollState())

        //    ){

         */
    var primaryBlue = Color(66,185,249)
    Row( modifier = Modifier
        .size(360.dp, 110.dp)
        .background(primaryBlue, shape = RoundedCornerShape(25.dp))
        .horizontalScroll(rememberScrollState())
        .offset(0.dp, 3.dp)
        .padding(10.dp)
        .alpha(1.0F)


            ) {
                forecastHourly?.let {
                    for (i in 0..13) {
                        var allTimeStuff: String = it.propertiesInForecast?.period?.get(i)?.startTime.toString()
                        var ending = "AM"
                        allTimeStuff = allTimeStuff.substring(11, 13)
                        if(allTimeStuff.toInt() > 11){
                            ending = "PM"
                        }
                        if(allTimeStuff.toInt() % 12 == 0){
                            allTimeStuff = "12"
                        }
                        else
                        {
                            allTimeStuff = (allTimeStuff.toInt() % 12).toString()
                        }
                        if(i == 0){
                            ending = ""
                            allTimeStuff = "Now"
                        }
                        Column() {
                            Row(modifier = Modifier.padding(7.dp)) {
                                Text(text = "$allTimeStuff",
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp
                                    //fontWeight = FontWeight.Bold
                                )
                                Text(text = "$ending",
                                    //textAlign = TextAlign.Center,
                                    fontSize = 15.sp,
                                    modifier = Modifier.offset(0.dp, 9.dp)
                                )



                            }
                            Row(modifier = Modifier.padding(0.dp)) {
                                Text(text = it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F   ",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.offset(5.dp, 0.dp),
                                    fontSize = 24.sp
                                )
                            }
                        }
                        /*
                        Text(text = allTimeStuff + ":00 " + ending + ": " + it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F",
                            textAlign = TextAlign.Center,
                            //modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        )
                        */
                    }
                } ?: Text(text = "Loading...")
            }
       // }
   // }
}

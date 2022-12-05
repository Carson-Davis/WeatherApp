package com.dreamteam2.weatherapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dreamteam2.weatherapp.ui.theme.WeatherAppTheme
import kotlin.math.roundToInt

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.app.Person
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.*
import kotlinx.coroutines.Delay
import kotlinx.coroutines.launch

var lat : Double = 0.0
var long : Double = 0.0
/*
MainActivity
-------------------------------------------------------------
The class creates an instance of the MainViewModel class and takes all the data that MainViewModel
pulls from the API and builds the UI of the application.

 */
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        requestNewLocationData()
        getLastLocation()
        setContent {
            WeatherAppTheme {
                mainLayout(viewModel)
            }
        }
    }
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        lat = location.latitude
                        long = location.longitude
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Looper.myLooper()?.let {
            mFusedLocationClient!!.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                it
            )
        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            lat = mLastLocation.latitude
            long = mLastLocation.longitude
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun mainLayout(viewModel: MainViewModel){
    val navController = rememberNavController()
    //var change: Int = 0
    //Scaffolding helps keep the top bar always at the top of the screen

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
//                                    MaterialTheme.colors.primaryVariant allows us to use
//                                    different color themes for light and dark mode
                    .background(MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .height(IntrinsicSize.Min)

            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start)
                ){
                    city(viewModel)
                }
            }
        },
        bottomBar = {
            bottomNavBar(navController)

        },

    ){
        navigation(navController = navController, viewModel)
    }
    LaunchedEffect(true){
        viewModel.fetchByString("Key West, Florida")
    }
}



@Composable
fun homeScreen(viewModel: MainViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        today(viewModel)
        Spacer(modifier = Modifier.height(15.dp))
        hourly(viewModel)
        Spacer(modifier = Modifier.height(15.dp))
        dailyForecast(viewModel)
        Spacer(modifier = Modifier.height(15.dp))
        bottom(viewModel)
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun locs(viewModel: MainViewModel){
    val coordinates by viewModel.coordinates.collectAsState()
    /*
    var firsPer = com.dreamteam2.weatherapp.Location("name")
    var list: ArrayList<com.dreamteam2.weatherapp.Location>? = ArrayList<com.dreamteam2.weatherapp.Location>()
    list?.add(firsPer)
     */
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
                locButton(viewModel)
                locButton(viewModel)
            }

}

@Composable
fun locButton(viewModel: MainViewModel){
    val coordinates by viewModel.coordinates.collectAsState()
    Button(modifier = Modifier
        .fillMaxSize()
        .height(IntrinsicSize.Max)
        .padding(15.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = Color.Black)
    ) {
        Text(textAlign = TextAlign.Center, text = coordinates?.get(0)?.displayName.toString(), fontSize = 30.sp)
    }
}

/*
* fun City
* @param MainViewModel
* Displays location name and coordinates on UI.
*
 */
@Composable
fun city(viewModel: MainViewModel){
    val gridPointEndpoints by viewModel.gridPointEndpoints.collectAsState()
    val coordinates by viewModel.coordinates.collectAsState()
    Text(
        //text = gridPointEndpoints?.properties?.relativeLocation?.properties?.city.toString() + ", " + gridPointEndpoints?.properties?.relativeLocation?.properties?.state.toString(),
        //text = coordinates?.get(0)?.lat.toString() + " " + coordinates?.get(0)?.lon.toString(),
        text = lat.toString() + " " + long.toString(),
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}

/*
* fun today
* This function displays data relevant for "today" which includes the current
* temperature, a description of the weather, a feels like temperature, a wind speed and
* direction, and a high-low temperature for the day. Along with styling elements.
* @param MainViewModel
*
 */
@Composable
fun today(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    val gridpointProperties by viewModel.gridpointsProperties.collectAsState()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant, shape = RoundedCornerShape(25.dp))
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .padding(20.dp)
                .height(IntrinsicSize.Max)
        ){
            Column(
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = forecastHourly?.propertiesInForecast?.period?.get(0)?.temperature.toString() + "°",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    fontSize = 70.sp,
                )
            }
            Column{
                Row{
                    Text(
                        text = forecastHourly?.propertiesInForecast?.period?.get(0)?.shortForecast.toString(),
                        modifier = Modifier,
                        fontSize = 25.sp,
                    )
                }
                Row {
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
                Row{
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
                Row {
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
                        text = "H: " + maxTempString + "°    " + "L: " + minTempString + "°",
                        fontSize = 15.sp
                    )

                }
            }
        }
    }
}

/*
 * fun hourly
 *
 * This function displays the hourly data for the specified location.
 * It displays the information in a 12 hour format with horizontal scroll and
 * uses degrees Fahrenheit for units.
 * The information is organized in a Jetpack Compose Row
 *
 * @param MainViewModel
 */
@Composable
fun hourly(viewModel: MainViewModel){
    val forecastHourly by viewModel.forecastHourly.collectAsState()
    Column( modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primaryVariant, shape = RoundedCornerShape(25.dp))
        .horizontalScroll(rememberScrollState())
        .alpha(1.0F)
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
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
                }
            } ?: Text(text = "Loading...")
        }
    }
}

/*
 * fun dailyForecast
 *
 * This function displays the daily data for the specified location.
 * It displays the information for the morning and evening of each day for 7 days.
 * It displays the information in a Jetpack Compose Column
 * All units are in degrees Fahrenheit
 *
 * @param MainViewModel
 */
@Composable
fun dailyForecast(viewModel: MainViewModel) {
    val foreCastPointEndpointTemperature by viewModel.forecast.collectAsState()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant, shape = RoundedCornerShape(25.dp))
    ){
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            foreCastPointEndpointTemperature?.let {
                for (i in 0..13) {
                    dataRow(it.propertiesInForecast?.period?.get(i)?.name.toString(), it.propertiesInForecast?.period?.get(i)?.temperature.toString() + "°F")
                }
            } ?: Text(text = "Loading...")
        }
    }
}
/*
* fun bottom
* @param MainViewModel
* Grabs the Dewpoint, Cloud Coverage, and Humididity data points from gridpointProperties and
* passes the to the function dataRow to display them at the bottom of the screen
 */
@Composable
fun bottom(viewModel: MainViewModel){
    val gridpointProperties by viewModel.gridpointsProperties.collectAsState()
    Column( modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primaryVariant, shape = RoundedCornerShape(25.dp))
        .alpha(1.0F)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            dataRow("Humidity", gridpointProperties?.properties?.relativeHumidity?.values?.get(0)?.value.toString() + " %")
            var dewpoint: Double? =
                gridpointProperties?.properties?.dewpoint?.values?.get(0)?.value
            var dewpointString: String
            if (dewpoint == null) {
                dewpointString = "Loading"
            } else {
                dewpointString = (dewpoint * 1.8 + 32).toString()
            }
            dataRow("Dewpoint", dewpointString + " °F")
            dataRow("Cloud Coverage", gridpointProperties?.properties?.skyCover?.values?.get(0)?.value.toString() + " %")
            if (!(gridpointProperties?.properties?.potentialOf50mphWindGusts?.values.isNullOrEmpty())) {
                dataRow("Wind Advisory", gridpointProperties?.properties?.potentialOf50mphWindGusts?.values.toString())
            }
        }
    }
}

/*
* fun dataRow
* @param String, String
* Styling elements to display the Dewpoint, Cloud Coverage, and Humidity
 */
@Composable
fun dataRow(leftText: String, rightText: String,){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
                .height(27.dp)
        ){
            Text(
                text = leftText,
                fontSize = 19.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
                .height(27.dp)
        ){
            Text(
                text = rightText,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun bottomNavBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Locations,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White,

        ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        Modifier.size(30.dp)
                    )
                },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
                //modifier = Modifier.size(5.dp, 5.dp)
            )
        }
    }
}

@Composable
fun navigation(navController: NavHostController, viewModel: MainViewModel) {
    //val viewModel: MainViewModel = MainViewModel()
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            homeScreen(viewModel)
        }
        composable(NavigationItem.Locations.route) {
            locs(viewModel)
        }
    }
}
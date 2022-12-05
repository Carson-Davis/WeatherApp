package com.dreamteam2.weatherapp

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home: NavigationItem("home", R.drawable.home_house_svgrepo_com, "Home")
    object Locations: NavigationItem("locations", R.drawable.location_svgrepo_com__1_, "Locations")
}
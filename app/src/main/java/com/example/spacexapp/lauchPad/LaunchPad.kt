package com.example.spacexapp.lauchPad

import com.google.gson.annotations.SerializedName

data class LaunchPad(

    @SerializedName("status")
    val status: String,
    @SerializedName("details")
    val details: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("attempted_launches")
    val attemptedLaunches: Int,
    @SerializedName("successful_launches")
    val successfulLaunches: Int
)

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
package com.example.spacexapp.lauchPad

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://api.spacexdata.com/v3/"

interface LaunchPadApi {

    @GET("launchpads")
    fun getLaunchPads(): Call<List<LaunchPad>>


    companion object {
        operator fun invoke(): LaunchPadApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LaunchPadApi::class.java)
        }
    }


}
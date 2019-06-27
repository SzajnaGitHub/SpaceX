package com.example.spacexapp.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexapp.CheckInternetConnectivityClass
import com.example.spacexapp.lauchPad.LaunchPad
import com.example.spacexapp.lauchPad.LaunchPadAdapter
import com.example.spacexapp.lauchPad.LaunchPadApi
import com.example.spacexapp.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkInternet = CheckInternetConnectivityClass()

        if (checkInternet.check(this)) {
            LaunchPadApi().getLaunchPads().enqueue(object : Callback<List<LaunchPad>> {

                override fun onFailure(call: Call<List<LaunchPad>>, t: Throwable) {
                    spacexMainTextVIew.text = "No internet connection!"
                }

                override fun onResponse(call: Call<List<LaunchPad>>, response: Response<List<LaunchPad>>) {
                    val launchpads = response.body()

                    launchpads?.let {
                        showLaunchPad(it)

                    }
                }
            })
        } else {
            spacexMainTextVIew.text = "No internet connection!"
        }

    }

    private fun showLaunchPad(launchpads: List<LaunchPad>) {
        recyclerViewLaunchPads.layoutManager = LinearLayoutManager(this)
        recyclerViewLaunchPads.adapter =
            LaunchPadAdapter(launchpads) { launchpad: LaunchPad ->
                partItemClicked(launchpad)
            }
    }

    private fun partItemClicked(launchpad: LaunchPad) {
        val intent = Intent(this, SingleLaunchPadActivity::class.java)
            intent.putExtra("lstatus", launchpad.status)
            intent.putExtra("lname", launchpad.location.name)
            intent.putExtra("ldetails", launchpad.details)
            intent.putExtra("lregion", launchpad.location.region)
            intent.putExtra("llongitude", launchpad.location.longitude)
            intent.putExtra("llatitude", launchpad.location.latitude)
            intent.putExtra("lwikipedia", launchpad.wikipedia)
            intent.putExtra("lattemptedLaunches", launchpad.attemptedLaunches.toString())
            intent.putExtra("lsuccessfulLaunches", launchpad.successfulLaunches.toString())
        startActivity(intent)

    }


}
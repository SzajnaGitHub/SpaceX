package com.example.spacexapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexapp.task.CheckInternetConnectivityClass
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
                    setContentView(R.layout.activity_no_internet_connection)
                }

                override fun onResponse(call: Call<List<LaunchPad>>, response: Response<List<LaunchPad>>) {
                    val launchpads = response.body()

                    launchpads?.let {
                        showLaunchPad(it)

                    }
                }
            })
        } else {
            setContentView(R.layout.activity_no_internet_connection)
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
        Intent(this, SingleLaunchPadActivity::class.java).apply {
            putExtra("lstatus", launchpad.status)
            putExtra("lname", launchpad.location.name)
            putExtra("ldetails", launchpad.details)
            putExtra("lregion", launchpad.location.region)
            putExtra("llongitude", launchpad.location.longitude)
            putExtra("llatitude", launchpad.location.latitude)
            putExtra("lwikipedia", launchpad.wikipedia)
            putExtra("lattemptedLaunches", launchpad.attemptedLaunches.toString())
            putExtra("lsuccessfulLaunches", launchpad.successfulLaunches.toString())
        }.let {
            startActivity(intent)
        }
    }


}
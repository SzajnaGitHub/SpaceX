package com.example.spacexapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacexapp.task.CheckInternetConnectivityClass
import com.example.spacexapp.task.ImageDownloadTask
import com.example.spacexapp.R
import kotlinx.android.synthetic.main.activity_single_launchad.*

class SingleLaunchPadActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_launchad)

        val checkInternet = CheckInternetConnectivityClass()


        if (checkInternet.check(this)) {
            //val init
            val imageUrl = intent.getStringExtra("lwikipedia")
            val longitude: Double = intent.getDoubleExtra("llongitude", 0.0)
            val latitude: Double = intent.getDoubleExtra("llatitude", 0.0)
            val name: String = intent.getStringExtra("lname")

            //textView initialization
            nameText.text = name
            status.text = intent.getStringExtra("lstatus")
            details.text = intent.getStringExtra("ldetails")
            region.text = intent.getStringExtra("lregion")
            longitudeText.text = longitude.toString()
            latitudeText.text = latitude.toString()
            attemptedLaunches.text = intent.getStringExtra("lattemptedLaunches")
            successfulLaunches.text = intent.getStringExtra("lsuccessfulLaunches")


            goToMapButton?.setOnClickListener {
                Intent(this, MapsActivity::class.java).apply {
                    putExtra("singleLatitude", latitude)
                    putExtra("singleLongitude", longitude)
                    putExtra("singleName", name)
                }.let {
                    startActivity(intent)
                }
            }

            ImageDownloadTask(imageView, noInternetProgressBar).execute(imageUrl)
        } else {
            setContentView(R.layout.activity_no_internet_connection)
        }

    }

}

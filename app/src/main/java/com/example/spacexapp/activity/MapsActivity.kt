package com.example.spacexapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacexapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        // init values
        val latitude: Double = intent.getDoubleExtra("singleLatitude", -34.0)
        val longitude: Double = intent.getDoubleExtra("singleLongitude", 151.0)
        val name: String = intent.getStringExtra("singleName")
        val location = LatLng(latitude, longitude)

        //setting map marker
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.addMarker(
            MarkerOptions().position(location).title(name)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )


    }
}

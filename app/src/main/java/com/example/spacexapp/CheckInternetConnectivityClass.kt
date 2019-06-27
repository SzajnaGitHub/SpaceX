package com.example.spacexapp

import android.content.Context
import android.net.ConnectivityManager

class CheckInternetConnectivityClass {

    fun check(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null
    }
}
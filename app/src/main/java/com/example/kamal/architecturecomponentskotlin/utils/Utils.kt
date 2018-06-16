package com.example.kamal.architecturecomponentskotlin.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    //Companion object to call this method statically
    companion object {

        //Method to check if internet is available for not
        fun isConnectedOrConnecting(context: Context): Boolean {
            val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }


}
package com.example.mybooks.utils

import android.content.Context

open class NetworkSystem(private val appContext : Context){

    companion object {
        var mIsNetworkAvailable = true

        fun setNetworkAvailable (isNetworkAvailable: Boolean){
            mIsNetworkAvailable = isNetworkAvailable
        }
    }
}
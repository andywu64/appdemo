package com.example.stockdemo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    val result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return result
}

fun networkCallbackChange(
    context: Context,
    registerState: Boolean = true,
    onConnection: (Boolean) -> Unit
) {
    val networkCallback = object : ConnectivityManager.NetworkCallback () {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            onConnection(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            onConnection(false)
        }
    }
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val builder = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

    val networkRequest = builder.build()
    if (registerState) {
        connectivityManager.registerNetworkCallback(
            networkRequest,
            networkCallback
        )
    } else {
        connectivityManager.unregisterNetworkCallback(
            networkCallback
        )
    }
}

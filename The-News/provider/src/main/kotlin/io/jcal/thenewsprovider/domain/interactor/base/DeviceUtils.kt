package io.jcal.thenewsprovider.domain.interactor.base

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import javax.inject.Inject

class DeviceUtils @Inject constructor(private val context: Context) {

    /**
     * Detects the availability of a working network connection to open a
     * http/https connection.
     *
     * @return true if network is available, false otherwise.
     */
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null
        }
}
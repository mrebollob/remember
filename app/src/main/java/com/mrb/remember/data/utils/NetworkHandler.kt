package com.mrb.remember.data.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
@Singleton
class NetworkHandler
@Inject constructor(private val context: Application) {
  val isConnected
    get() = (context.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager).activeNetworkInfo.isConnected
}

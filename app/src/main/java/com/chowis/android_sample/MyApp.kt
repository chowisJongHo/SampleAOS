package com.chowis.android_sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import com.chowis.android_sample.utils.SharedPreferencesManager
import com.chowis.android_sample.utils.WifiUtils
import com.chowis.sdk.newcnd.CNDManager
import com.chowis.sdk.newcnd.CNDSetupManager

class MyApp: Application() {
    companion object {
        lateinit var context: Context
        lateinit var prefsManager: SharedPreferencesManager
        @SuppressLint("StaticFieldLeak")
        lateinit var wifiUtils: WifiUtils

        @SuppressLint("StaticFieldLeak")
        lateinit var cndManager: CNDManager
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        prefsManager = SharedPreferencesManager(applicationContext)

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiUtils = WifiUtils(applicationContext, wifiManager)

        cndManager = CNDManager.getInstance(applicationContext)
    }
}
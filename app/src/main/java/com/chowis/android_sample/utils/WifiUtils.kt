package com.chowis.android_sample.utils

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.util.Log

class WifiUtils(private val context: Context, private val wifiManager: WifiManager) {

    val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val success = intent!!.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    fun getNetworkName(): String {
        val wifiInfo = wifiManager.connectionInfo
        return wifiInfo.ssid
    }

    @SuppressLint("MissingPermission")
    fun wifiCheck() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(wifiScanReceiver, intentFilter)
        if (wifiManager.wifiState == WifiManager.WIFI_STATE_ENABLED) {
            val result = wifiManager.scanResults
            Log.d("TAG", "wifiCheck: $result")
        }
    }


    @SuppressLint("MissingPermission")
    private fun scanSuccess() {
        val results = wifiManager.scanResults
        Log.d("TAG", "scanSuccess: $results")
    }

    @SuppressLint("MissingPermission")
    private fun scanFailure() {
        val results = wifiManager.scanResults
        Log.d("TAG", "scanFailure: ${results[0].BSSID}")
    }
}
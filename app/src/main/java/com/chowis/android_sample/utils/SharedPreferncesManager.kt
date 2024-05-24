package com.chowis.android_sample.utils

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("android_sample", Context.MODE_PRIVATE)

    companion object {
        val OPTIC_NUMBER = "OPTIC_NUMBER"
        val WIFI_GHZ_STRING = "WIFI_GHZ_STRING"
        val CAPTURE_IS_FIRST = "CAPTURE_IS_FIRST"
        val MY_SKIN_TYPE = "MY_SKIN_TYPE"
    }

    fun setOpticNumber(opticNumber: String) {
        prefs.edit().putString(OPTIC_NUMBER, opticNumber).apply()
    }

    fun getOpticNumber(): String {
        return prefs.getString(OPTIC_NUMBER, "").toString()
    }

    fun setWifiGhz(value: Boolean) {
        prefs.edit().putBoolean(WIFI_GHZ_STRING, value).apply()
    }

    fun getWifiGhz(): Boolean {
        return prefs.getBoolean(WIFI_GHZ_STRING, false)
    }

    fun setCaptureIsFirst(check: Boolean) {
        prefs.edit().putBoolean(CAPTURE_IS_FIRST, check).apply()
    }

    fun getCaptureIsFirst(): Boolean {
        return prefs.getBoolean(CAPTURE_IS_FIRST, true)
    }

    fun setMySkinType(type: Int) {
        prefs.edit().putInt(MY_SKIN_TYPE, type).apply()
    }

    fun getMySkinType(): Int {
        return prefs.getInt(MY_SKIN_TYPE, 1001)
    }
}
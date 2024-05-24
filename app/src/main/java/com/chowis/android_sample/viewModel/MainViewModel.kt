package com.chowis.android_sample.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chowis.android_sample.Repository
import com.chowis.android_sample.model.SkinGroup
import com.chowis.sdk.newcnd.CNDManager

class MainViewModel: ViewModel() {
    private val repository = Repository()

    private val _analysisButtonClick = MutableLiveData<Boolean>()
    val analysisButtonClick: LiveData<Boolean>
        get() = _analysisButtonClick

    private val _functionButtonClick = MutableLiveData<Boolean>()
    val functionButtonClick: LiveData<Boolean>
        get() = _functionButtonClick

    private val _firmwareVersion = MutableLiveData<String>()
    val firmwareVersion: LiveData<String>
        get() = _firmwareVersion

    private val _firmwareButtonClick = MutableLiveData<Boolean>()
    val firmwareButtonClick: LiveData<Boolean>
        get() = _firmwareButtonClick

    private val _wifiGHzString = MutableLiveData<Boolean>()
    val wifiGHzString: LiveData<Boolean>
        get() = _wifiGHzString

    var batteryLevel = 0

    private val _selectSkin = MutableLiveData<Int>()
    val selectSkin: LiveData<Int>
        get() = _selectSkin

    private val _getWifiNameCheck = MutableLiveData<Boolean>()
    val getWifiNameCheck: LiveData<Boolean>
        get() = _getWifiNameCheck

    fun setOpticNumber(opticNumber: String) {
        repository.setOpticNumber(opticNumber)
    }

    fun setCndManager(): CNDManager {
        return repository.getCNDManager()
    }

    fun analysisButtonClick(click: Boolean) {
        _analysisButtonClick.value = click
        _functionButtonClick.value = false
    }

    fun functionButtonClick(click: Boolean) {
        _functionButtonClick.value = click
    }

    fun firmwareButtonClick(click: Boolean) {
        _firmwareButtonClick.value = click
    }

    fun firmwareVersion(version: String) {
        _firmwareVersion.postValue(version)
    }

    fun getWifiGHzString(): String {
        if (!repository.getWifiGHz()) {
            return "2.5GHz"
        } else {
            return "5GHz"
        }
    }

    fun setWifiGHzString(GHz: Boolean) {
        repository.setWifiGHz(GHz)
        _wifiGHzString.value = GHz
    }

    fun setNowBatteryLevel(): Int {
        return batteryLevel
    }

    fun setSkinGroupData(): ArrayList<SkinGroup> {
        return repository.getSkinGroupData()
    }

    fun getSelectSkin(position: Int) {
        _selectSkin.value = position
    }

    fun getWifiName() {
        val wifiName = repository.getWifiName()
        val cidString = "CID"
        _getWifiNameCheck.value = wifiName.contains(cidString)
    }
}
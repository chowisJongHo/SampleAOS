package com.chowis.android_sample.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chowis.android_sample.Repository
import com.chowis.android_sample.model.SampleDB
import kotlinx.coroutines.launch

class SampleViewModel: ViewModel() {
    private val repository = Repository()

    private var _skinGroupText = MutableLiveData<String>()
    val skinGroup: LiveData<String>
        get() = _skinGroupText
    private var _sampleList = MutableLiveData<ArrayList<SampleDB>>()
    val sampleList: LiveData<ArrayList<SampleDB>>
        get() = _sampleList

    fun setActivityTitle(title: String) {
        _skinGroupText.value = title
    }

    fun getSampleList(skinGroup: String, measurement: String) = viewModelScope.launch {
        if (measurement == "WRINKLE") {
            _sampleList.value = repository.getSampleWrinkles(skinGroup)
        } else {
            _sampleList.value = repository.getSamplePores(skinGroup)
        }
    }
}
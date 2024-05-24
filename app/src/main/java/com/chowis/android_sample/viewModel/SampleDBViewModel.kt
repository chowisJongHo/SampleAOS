package com.chowis.android_sample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleDBViewModel: ViewModel() {
    private val _titleText = MutableLiveData<String>()
    val titleText: LiveData<String>
        get() = _titleText

    var nowSkinGroup = ""

    fun setActivityTitle(title: String) {
        nowSkinGroup = title
        _titleText.value = title
    }
}
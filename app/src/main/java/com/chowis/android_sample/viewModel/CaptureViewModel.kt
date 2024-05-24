package com.chowis.android_sample.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chowis.android_sample.Repository
import com.chowis.jniimagepro.JNIImageProCWCNDPSkinLocal
import com.chowis.sdk.newcnd.CNDManager

class CaptureViewModel: ViewModel() {
    private val repository = Repository()

    private val _batteryValue = MutableLiveData<Int>()
    val batteryValue: LiveData<Int>
        get() = _batteryValue

    private val _captureImage = MutableLiveData<Bitmap?>()
    val captureImage: LiveData<Bitmap?>
        get() = _captureImage

    fun setCNDManager(): CNDManager {
        return repository.getCNDManager()
    }

    fun setBatteryValue(batteryValue: Int) {
        _batteryValue.postValue(batteryValue)
    }

    fun setCaptureImage(bitmap: Bitmap?) {
        _captureImage.postValue(bitmap)
    }

    fun mySkinType(filePath: String) {
        val proCWCNDPSkinLocal = JNIImageProCWCNDPSkinLocal()
        val skinType = proCWCNDPSkinLocal.FizpatrickSG102Jni(filePath, "/sdcard/Pictures/NEWCND/skin.jpg").toInt()

        repository.setMySkinType(skinType)
        Log.d("TAG", "mySkinType: ${repository.getMySkinType()}")
    }
}
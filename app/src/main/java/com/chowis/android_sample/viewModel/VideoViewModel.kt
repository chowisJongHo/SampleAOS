package com.chowis.android_sample.viewModel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chowis.android_sample.MyApp
import com.chowis.android_sample.Repository
import com.chowis.android_sample.model.MyResult
import com.chowis.android_sample.model.ResultData
import com.chowis.android_sample.model.SkinGroup
import com.chowis.android_sample.utils.CameraType
import com.chowis.jniimagepro.JNIImageProCWCNDPSkinLocal
import com.chowis.sdk.newcnd.CNDManager

class VideoViewModel : ViewModel() {
    private val repository = Repository()

    private val myData = ArrayList<ResultData>()
    private val resultData = ArrayList<MyResult>()

    private var selectSkinGroup: CNDManager.SkinGroup
    private val _selectSkin = MutableLiveData<String>()
    val selectSkin: LiveData<String>
        get() = _selectSkin

    private val _selectType = MutableLiveData<String>()
    val selectType: LiveData<String>
        get() = _selectType

    private val _selectButton = MutableLiveData<Int>()
    val selectButton: LiveData<Int>
        get() = _selectButton

    private val _batteryValue = MutableLiveData<Int>()
    val batteryValue: LiveData<Int>
        get() = _batteryValue

    private val _tZoneResult = MutableLiveData<String>()
    val tZoneResult: LiveData<String>
        get() = _tZoneResult

    private val _uZoneResult = MutableLiveData<String>()
    val uZoneResult: LiveData<String>
        get() = _uZoneResult

    private val _moistureCheck = MutableLiveData<Boolean>()
    val moistureCheck: LiveData<Boolean>
        get() = _moistureCheck

    private val _moistureLayerHide = MutableLiveData<Boolean>()
    val moistureLayerHide: LiveData<Boolean>
        get() = _moistureLayerHide

    private val _captureImage = MutableLiveData<Bitmap>()
    val captureImage: LiveData<Bitmap>
        get() = _captureImage

    private val _nextType = MutableLiveData<Int>()
    val nextType: LiveData<Int>
        get() = _nextType

    private val _beforeDataCheck = MutableLiveData<String>()
    val beforeDataCheck: LiveData<String>
        get() = _beforeDataCheck

    private val _resultData = MutableLiveData<ArrayList<MyResult>>()
    val myResultData: LiveData<ArrayList<MyResult>>
        get() = _resultData

    init {
        selectSkinGroup = CNDManager.SkinGroup.SKIN_GROUP_12

        for (i in 0..8) {
            myData.add(ResultData("", false, null))
            when (i) {
                CameraType.MOISTURE_T.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.MOISTURE_T.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.MOISTURE_U.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.MOISTURE_U.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.SPOT.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.SPOT.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.PORES.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.PORES.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.IMPURITIES.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.IMPURITIES.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.WRINKLE.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.WRINKLE.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.KERATIN.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.KERATIN.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.SENSITIVITY.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.SENSITIVITY.contentName),
                        "",
                        "",
                        ""
                    )
                )

                CameraType.SEBUM.typeCode -> resultData.add(
                    MyResult(
                        repository.getResultTypeName(CameraType.SEBUM.contentName),
                        "",
                        "",
                        ""
                    )
                )
            }
        }
    }

    fun setCndManager(): CNDManager {
        return repository.getCNDManager()
    }

    fun setCaptureIsFirst(check: Boolean) {
        if (check) {
            repository.setMySkinType(1001)
        }
        repository.setCaptureIsFirst(check)
    }

    fun getCaptureIsFirst(): Boolean {
        return repository.getCaptureIsFirst()
    }

    fun setBatteryValue(batteryValue: Int) {
        _batteryValue.postValue(batteryValue)
    }

    fun getMySkinType(): Int {
        return repository.getMySkinType()
    }

    fun setSkinDialogData(): ArrayList<SkinGroup> {
        return repository.getSkinGroupData()
    }

    fun setSkinGroup(select: Int) {
        Log.d("TAG", "setSkinGroup: $select")
        when (select) {
            0, 1 -> selectSkinGroup = CNDManager.SkinGroup.SKIN_GROUP_12
            2, 3 -> selectSkinGroup = CNDManager.SkinGroup.SKIN_GROUP_34
            4, 5 -> selectSkinGroup = CNDManager.SkinGroup.SKIN_GROUP_56
        }

        _selectSkin.value = "SG ${select + 1}"
    }

    fun getSkinGroup(): CNDManager.SkinGroup {
        return selectSkinGroup
    }

    fun setButtonSelected(buttonTag: Int) {
        _selectButton.value = buttonTag
        if (buttonTag == 1) {
            if (resultData[0].scoreValue != "" || resultData[1].scoreValue != "") {
                beforeDataChecking(buttonTag)
            }
        } else {
            if (myData[buttonTag].imagePath != "") {
                beforeDataChecking(buttonTag)
            }
        }

        var x = ""
        when(buttonTag) {
            CameraType.MOISTURE_U.typeCode -> x = "Moisture"
            CameraType.SPOT.typeCode -> x = repository.getResultTypeName(CameraType.SPOT.contentName)
            CameraType.PORES.typeCode -> x = repository.getResultTypeName(CameraType.PORES.contentName)
            CameraType.IMPURITIES.typeCode -> x = repository.getResultTypeName(CameraType.IMPURITIES.contentName)
            CameraType.WRINKLE.typeCode -> x = repository.getResultTypeName(CameraType.WRINKLE.contentName)
            CameraType.KERATIN.typeCode -> x = repository.getResultTypeName(CameraType.KERATIN.contentName)
            CameraType.SENSITIVITY.typeCode -> x = repository.getResultTypeName(CameraType.SENSITIVITY.contentName)
            CameraType.SEBUM.typeCode -> x = repository.getResultTypeName(CameraType.SEBUM.contentName)
        }
        _selectType.value = x
    }

    fun cameraModeChange(cameraType: Int): CNDManager.CNDCameraMode {
        var result = CNDManager.CNDCameraMode.SKIN_SPOT
        when (cameraType) {
            CameraType.SPOT.typeCode -> result = CNDManager.CNDCameraMode.SKIN_SPOT

            CameraType.PORES.typeCode,
            CameraType.WRINKLE.typeCode,
            CameraType.SEBUM.typeCode -> result = CNDManager.CNDCameraMode.SKIN_VSL

            CameraType.IMPURITIES.typeCode -> result = CNDManager.CNDCameraMode.SKIN_UVL

            CameraType.KERATIN.typeCode -> result = CNDManager.CNDCameraMode.SKIN_KERATIN

            CameraType.SENSITIVITY.typeCode -> result = CNDManager.CNDCameraMode.SKIN_SENSITIVITY
        }

        moistureLayerSetting(true)
        return result
    }

    fun nextType(nowTypeCode: Int) {
        if (nowTypeCode != 8) {
            _nextType.value = nowTypeCode + 1
        } else {
            _nextType.value = 1
        }
    }

    fun moistureLayerSetting(status: Boolean) {
        _moistureLayerHide.value = status
    }

    fun setMoistureValue(typeCode: Int, result: String) {
        myData[typeCode] = ResultData("", true, result)
        when (typeCode) {
            CameraType.MOISTURE_T.typeCode -> {
                resultData[0] = MyResult(
                    repository.getResultTypeName(CameraType.MOISTURE_T.contentName),
                    result,
                    resultScore(CameraType.MOISTURE_T.typeCode, result.toInt()),
                    null
                )
                _tZoneResult.postValue(result)
            }

            CameraType.MOISTURE_U.typeCode -> {
                resultData[1] = MyResult(
                    repository.getResultTypeName(CameraType.MOISTURE_U.contentName),
                    result,
                    resultScore(CameraType.MOISTURE_U.typeCode, result.toInt()),
                    null
                )
                _uZoneResult.postValue(result)
            }
        }
        checkMoisture()
    }

    private fun checkMoisture() {
        _moistureCheck.postValue(
            myData[CameraType.MOISTURE_T.typeCode].check &&
                    myData[CameraType.MOISTURE_U.typeCode].check
        )
    }

    fun setCaptureImage(bitmap: Bitmap) {
        _captureImage.postValue(bitmap)
    }

    fun settingResult() {
        val proCWCNDPSkinLocal = JNIImageProCWCNDPSkinLocal()

        for (i in 0 until myData.size) {
            if (myData[i].check) {
                when (i) {
                    CameraType.SPOT.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinSpots212Jni(
                            myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.SPOT.contentName),
                            x.toString(),
                            resultScore(CameraType.SPOT.typeCode, x.toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }

                    CameraType.PORES.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinPores208Jni(
                            myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.PORES.contentName),
                            x.toString(),
                            resultScore(CameraType.PORES.typeCode, x.toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }

                    CameraType.IMPURITIES.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinImpurities208Jni(
                            myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.IMPURITIES.contentName),
                            x.toString(),
                            resultScore(CameraType.IMPURITIES.typeCode, x.toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }

                    CameraType.WRINKLE.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinWrinkles217Jni(myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg",
                            "/sdcard/Pictures/NEWCND/$i-1.jpg",
                            "/sdcard/Pictures/NEWCND/$i-2.jpg",
                            "/sdcard/Pictures/NEWCND/$i-3.jpg",
                            "/sdcard/Pictures/NEWCND/$i-4.jpg"
                        )

                        val myScore = x.split("_")
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.WRINKLE.contentName),
                            myScore[0],
                            resultScore(CameraType.WRINKLE.typeCode, myScore[0].toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }

                    CameraType.KERATIN.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinKeratin128Jni(
                            myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.KERATIN.contentName),
                            x.toString(),
                            resultScore(CameraType.KERATIN.typeCode, x.toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }

                    CameraType.SENSITIVITY.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinSensitivityRedness111Jni(
                            myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.SENSITIVITY.contentName),
                            x.toString(),
                            resultScore(CameraType.SENSITIVITY.typeCode, x.toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }

                    CameraType.SEBUM.typeCode -> {
                        val x = proCWCNDPSkinLocal.CNDPskinSebum125Jni(
                            myData[i].imagePath,
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                        resultData[i] = MyResult(
                            repository.getResultTypeName(CameraType.SEBUM.contentName),
                            x.toString(),
                            resultScore(CameraType.SEBUM.typeCode, x.toInt()),
                            "/sdcard/Pictures/NEWCND/$i.jpg"
                        )
                    }
                }
            }
        }
        _resultData.postValue(resultData)
//        return resultData
    }

    private fun resultScore(type: Int, score: Int): String {
        var s = ""
        when (type) {
            CameraType.MOISTURE_T.typeCode,
            CameraType.MOISTURE_U.typeCode -> {
                when (score) {
                    in 0..5 -> s = "Very Dehydrated"
                    in 6..15 -> s = "Dehydrated"
                    in 16..48 -> s = "Normal"
                    in 49..80 -> s = "Hydrated"
                    in 81..99 -> s = "Very Hydrated"
                }
            }

            CameraType.SPOT.typeCode,
            CameraType.PORES.typeCode,
            CameraType.IMPURITIES.typeCode,
            CameraType.WRINKLE.typeCode,
            CameraType.KERATIN.typeCode,
            CameraType.SENSITIVITY.typeCode -> {
                when (score) {
                    in 0..5 -> s = "Clear"
                    in 6..15 -> s = "Almost Clear"
                    in 16..48 -> s = "Mild"
                    in 49..80 -> s = "Moderate"
                    in 81..99 -> s = "Severe"
                }
            }

            CameraType.SEBUM.typeCode -> {
                when (score) {
                    in 0..5 -> s = "Very Dry"
                    in 6..15 -> s = "Dry"
                    in 16..48 -> s = "Normal"
                    in 49..80 -> s = "Oily"
                    in 81..99 -> s = "Very oily"
                }
            }
        }
        return s
    }

    private fun beforeDataChecking(cameraType: Int) {
        if (cameraType == 1) {
            if (resultData[0].scoreValue != "") {
                _tZoneResult.postValue(resultData[0].scoreValue.toString())
            }
            if (resultData[1].scoreValue != "") {
                _uZoneResult.postValue(resultData[1].scoreValue.toString())
            }
        } else {
            _beforeDataCheck.value = myData[cameraType].imagePath
        }
    }

    fun saveAddData(imagePath: String, cameraType: Int) {
        myData[cameraType] = ResultData(imagePath, true, "0")
    }
}
package com.chowis.android_sample

import androidx.core.content.ContextCompat.getString
import com.chowis.android_sample.model.MyResult
import com.chowis.android_sample.model.ResultData
import com.chowis.android_sample.model.SampleDB
import com.chowis.android_sample.model.SkinGroup
import com.chowis.android_sample.utils.CameraType
import com.chowis.sdk.newcnd.CNDManager

class Repository {
    fun getSkinGroupData(): ArrayList<SkinGroup> {
        val skinGroupData = ArrayList<SkinGroup>()
        skinGroupData.add(SkinGroup(R.color.skin_group_background1, "SG1"))
        skinGroupData.add(SkinGroup(R.color.skin_group_background2, "SG2"))
        skinGroupData.add(SkinGroup(R.color.skin_group_background3, "SG3"))
        skinGroupData.add(SkinGroup(R.color.skin_group_background4, "SG4"))
        skinGroupData.add(SkinGroup(R.color.skin_group_background5, "SG5"))
        skinGroupData.add(SkinGroup(R.color.skin_group_background6, "SG6"))

        return skinGroupData
    }

    fun getSamplePores(skinGroup: String): ArrayList<SampleDB> {
        val sampleList = ArrayList<SampleDB>()
        when(skinGroup) {
            "SG1" -> {
                sampleList.add(SampleDB(R.drawable.sample_pores_1))
                sampleList.add(SampleDB(R.drawable.sample_porse_2))
                sampleList.add(SampleDB(R.drawable.sample_porse_3))
            }

            "SG2" -> {
                sampleList.add(SampleDB(R.drawable.sample_porse_4))
                sampleList.add(SampleDB(R.drawable.sample_porse_5))
                sampleList.add(SampleDB(R.drawable.sample_porse_6))
            }

            "SG3" -> {
                sampleList.add(SampleDB(R.drawable.sample_porse_7))
                sampleList.add(SampleDB(R.drawable.sample_porse_8))
                sampleList.add(SampleDB(R.drawable.sample_porse_9))
            }

            "SG4" -> {
                sampleList.add(SampleDB(R.drawable.sample_porse_10))
                sampleList.add(SampleDB(R.drawable.sample_porse_11))
                sampleList.add(SampleDB(R.drawable.sample_porse_12))
            }

            "SG5" -> {
                sampleList.add(SampleDB(R.drawable.sample_porse_13))
                sampleList.add(SampleDB(R.drawable.sample_porse_14))
                sampleList.add(SampleDB(R.drawable.sample_porse_15))
            }

            "SG6" -> {
                sampleList.add(SampleDB(R.drawable.sample_porse_16))
                sampleList.add(SampleDB(R.drawable.sample_porse_17))
                sampleList.add(SampleDB(R.drawable.sample_porse_18))
            }
        }
        return sampleList
    }

    fun getSampleWrinkles(skinGroup: String): ArrayList<SampleDB> {
        val sampleList = ArrayList<SampleDB>()
        when(skinGroup) {
            "SG1" -> {
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_1))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_2))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_3))
            }

            "SG2" -> {
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_4))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_5))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_6))
            }

            "SG3" -> {
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_7))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_8))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_9))
            }

            "SG4" -> {
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_10))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_11))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_12))
            }

            "SG5" -> {
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_13))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_14))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_15))
            }

            "SG6" -> {
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_16))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_17))
                sampleList.add(SampleDB(R.drawable.sample_wrinkles_18))
            }
        }
        return sampleList
    }

    fun setOpticNumber(opticNumber: String) {
        MyApp.prefsManager.setOpticNumber(opticNumber)
    }

    fun setWifiGHz(GHz: Boolean) {
        MyApp.prefsManager.setWifiGhz(GHz)
    }

    fun getWifiGHz(): Boolean {
        return MyApp.prefsManager.getWifiGhz()
    }

    fun setCaptureIsFirst(check: Boolean) {
        MyApp.prefsManager.setCaptureIsFirst(check)
    }

    fun getCaptureIsFirst(): Boolean {
        return MyApp.prefsManager.getCaptureIsFirst()
    }

    fun getWifiName(): String {
        return MyApp.wifiUtils.getNetworkName()
    }

    fun getCNDManager(): CNDManager {
        return MyApp.cndManager
    }

    fun setMySkinType(type: Int) {
        MyApp.prefsManager.setMySkinType(type)
    }

    fun getMySkinType(): Int {
        return MyApp.prefsManager.getMySkinType()
    }

    fun getResultTypeName(type: Int): String {
        return MyApp.context.getString(type)
    }
}
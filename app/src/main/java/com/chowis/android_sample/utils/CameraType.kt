package com.chowis.android_sample.utils

import android.content.Context
import com.chowis.android_sample.R

enum class CameraType(val typeCode: Int, val contentName: Int) {
    MOISTURE_T(0, R.string.result_value_t_zone),
    MOISTURE_U(1, R.string.result_value_u_zone),
    SPOT(2, R.string.result_value_spot),
    PORES(3, R.string.pores_string),
    IMPURITIES(4, R.string.impurities_string),
    WRINKLE(5, R.string.wrinkle_string),
    KERATIN(6, R.string.keratin_string),
    SENSITIVITY(7, R.string.sensitivity_string),
    SEBUM(8, R.string.sebum_string)
}

package com.chowis.android_sample.model

import java.io.Serializable

data class MyResult(
    val measurementsTitle: String,
    val scoreValue: String?,
    val indexTitle: String?,
    val imagePath: String?
): Serializable
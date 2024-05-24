package com.chowis.android_sample.view

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.ActivityTestBinding
import com.chowis.jniimagepro.JNIImageProCWCNDPSkinLocal
import java.io.File

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
//Pictures/NEWCND/20240412newcnd_app_20240412184923
        val x = File("/sdcard/Pictures/NEWCND/20240412/newcnd_app_20240412184923.jpg")
        Log.d("TAG", "onCreate: ${BitmapFactory.decodeFile(x.toString())}")
        binding.image.setImageBitmap(BitmapFactory.decodeFile(x.toString()))
//        JNIImageProCWCNDPSkinLocal().CNDPskinSensitivityRedness111Jni("/sdcard/Pictures/NEWCND/20240412/newcnd_app_20240412184923.jpg", "/sdcard/Pictures/NEWCND/20240412")

        val y = JNIImageProCWCNDPSkinLocal().CNDPskinSensitivityRedness112Jni("/sdcard/Pictures/NEWCND/20240412/newcnd_app_20240412184923.jpg", "/sdcard/Pictures/NEWCND/total.jpg", "/sdcard/Pictures/NEWCND/pink.jpg", "/sdcard/Pictures/NEWCND/red.jpg")
        Log.d("TAG", "onCreate: $y")
    }
}
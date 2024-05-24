package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.chowis.android_sample.adapter.MeasurementViewPagerAdapter
import com.chowis.android_sample.databinding.DialogMeasurementBinding
import com.chowis.android_sample.listener.MeasurementListener
import com.google.android.material.tabs.TabLayoutMediator

class MeasurementDialog(private val context: AppCompatActivity, private val listener: MeasurementListener) {
    private lateinit var binding: DialogMeasurementBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogMeasurementBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.measurementViewpager.adapter = MeasurementViewPagerAdapter(context)
        binding.measurementViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.measurementTab, binding.measurementViewpager) { _, _ -> }.attach()

        binding.measurementProceedButton.setOnClickListener {
            listener.measurementListener()
            dialog.dismiss()
        }

        dialog.show()
    }
}
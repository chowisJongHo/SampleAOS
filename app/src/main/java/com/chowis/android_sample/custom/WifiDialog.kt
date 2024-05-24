package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity
import com.chowis.android_sample.databinding.DialogWifiBinding
import com.chowis.android_sample.listener.WifiSelectListener

class WifiDialog(private val context: FragmentActivity, private val battery: Int, private val listener: WifiSelectListener) {
    private lateinit var binding: DialogWifiBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogWifiBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogHeader.dialogCloseButton.setOnClickListener {
            dialog.dismiss()
        }

        binding.dialogWifiButton1.setOnClickListener {
            listener.wifiSelectListener("2.5 GHz")
            dialog.dismiss()
        }

        binding.dialogWifiButton2.setOnClickListener {
            listener.wifiSelectListener("5.0 GHz")
            dialog.dismiss()
        }

        dialog.show()
    }
}
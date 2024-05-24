package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.chowis.android_sample.databinding.DialogLoadingBinding

class LoadingDialog(private val context: FragmentActivity) {
    private lateinit var binding: DialogLoadingBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogLoadingBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }

    fun hide() {
        Log.d("TAG", "hide: #####")
        dialog.dismiss()
    }
}
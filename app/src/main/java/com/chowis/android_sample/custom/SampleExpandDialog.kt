package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import com.chowis.android_sample.databinding.DialogExpandViewBinding

class SampleExpandDialog(private val context: AppCompatActivity, private val position: Int) {

    private lateinit var binding: DialogExpandViewBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogExpandViewBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogExpandImage.setImageResource(position)

        binding.dialogExpandClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.BitmapFactory
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chowis.android_sample.databinding.DialogResultImageBinding
import java.io.File

class ImageExpansionDialog(private val context: AppCompatActivity, private val imagePath: String) {
    private lateinit var binding: DialogResultImageBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogResultImageBinding.inflate(context.layoutInflater)
        Log.d("TAG", "show: $imagePath")

        val image = File(imagePath)
        val bitmap = BitmapFactory.decodeFile(image.absolutePath)
        binding.dialogResultImage.setImageBitmap(bitmap)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        binding.dialogResultCloseButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
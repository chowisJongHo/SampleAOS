package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.DialogCaptuerBinding
import com.chowis.android_sample.listener.CaptureTypeSelectListener

class CaptureDialog(private val context: AppCompatActivity, private val listener: CaptureTypeSelectListener) :
    View.OnClickListener {
    private lateinit var binding: DialogCaptuerBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogCaptuerBinding.inflate(context.layoutInflater)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogHeader.dialogCloseButton.setOnClickListener {
            listener.captureTypeSelectListener("close")
            dialog.dismiss()
        }

        binding.dialogCaptureButton.setOnClickListener(this)
        binding.dialogCaptureManualButton.setOnClickListener(this)

        dialog.show()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.dialog_capture_button -> listener.captureTypeSelectListener("capture")
            R.id.dialog_capture_manual_button -> listener.captureTypeSelectListener("captureManual")
        }

        dialog.dismiss()
    }
}
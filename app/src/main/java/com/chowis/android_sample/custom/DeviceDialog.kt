package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.chowis.android_sample.databinding.DialogDeviceBinding
import com.chowis.android_sample.listener.OptionNumberListener
import com.chowis.sdk.newcnd.CNDSetupManager

class DeviceDialog(private val context: FragmentActivity,
                   private val listener: OptionNumberListener
) {

    private lateinit var binding: DialogDeviceBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogDeviceBinding.inflate(context.layoutInflater)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogHeader.dialogCloseButton.setOnClickListener {
            dialog.dismiss()
        }

        binding.wbLinear.setOnClickListener {
            val setupManager = CNDSetupManager.getInstance(context)
            setupManager.initialize(binding.dialogEditText.text.toString())

            setupManager.downloadFiles(false, object : CNDSetupManager.DownloadFileListener {
                override fun onDownloadFileFailure() {
                    Toast.makeText(context, "Download Failure", Toast.LENGTH_SHORT).show()
                }

                override fun onDownloadFileSucceed() {
                    Toast.makeText(context, "Download Succeed", Toast.LENGTH_SHORT).show()
                }

            })
        }

        binding.dialogSubmitButton.setOnClickListener {
            val optionNumber = binding.dialogEditText.text
            if (optionNumber.length < 8) {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listener.optionNumber(optionNumber.toString())
            dialog.dismiss()
        }
        dialog.show()
    }
}
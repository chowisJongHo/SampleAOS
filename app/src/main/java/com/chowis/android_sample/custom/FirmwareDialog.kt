package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.DialogFirmwareBinding
import com.chowis.android_sample.listener.FirmwareUpdateClickListener

class FirmwareDialog(private val context: FragmentActivity, private val updateStatus: Boolean, private val listener: FirmwareUpdateClickListener) : View.OnClickListener {
    private lateinit var binding: DialogFirmwareBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogFirmwareBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogHeader.dialogCloseButton.setOnClickListener {
            dialog.dismiss()
        }

        if (!updateStatus) {
            binding.dialogFirmwareInfoText.visibility = View.GONE
            binding.dialogFirmwareButtonLinear.visibility = View.GONE

            binding.dialogFirmwareNotNeedText.visibility = View.VISIBLE
            binding.dialogFirmwareNotNeedDismissButton.visibility = View.VISIBLE
        } else {
            binding.dialogFirmwareInfoText.visibility = View.VISIBLE
            binding.dialogFirmwareButtonLinear.visibility = View.VISIBLE

            binding.dialogFirmwareNotNeedText.visibility = View.GONE
            binding.dialogFirmwareNotNeedDismissButton.visibility = View.GONE
        }

        val versionName = "1.0.0"
        binding.dialogFirmwareInfoText.text = setVersionName(versionName)

        binding.dialogFirmwareUpdateButton.setOnClickListener(this)
        binding.dialogFirmwareDismissButton.setOnClickListener(this)
        binding.dialogFirmwareNotNeedDismissButton.setOnClickListener(this)

        dialog.show()
    }

    private fun setVersionName(firmwareVersionName: String): SpannableString {
        val versionNameFormat = String.format(context.resources.getString(R.string.firmware_update_content_string), firmwareVersionName)
        val spannableString = SpannableString(versionNameFormat)

        val startString = versionNameFormat.indexOf(firmwareVersionName)
        val endString = startString + firmwareVersionName.length

        spannableString.setSpan(StyleSpan(Typeface.BOLD), startString, endString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dialog_firmware_update_button -> {
                listener.firmwareUpdateClick()
                dialog.dismiss()
            }

            R.id.dialog_firmware_dismiss_button,
            R.id.dialog_firmware_not_need_dismiss_button ->
                dialog.dismiss()
        }
    }
}
package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.FragmentActivity
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.DialogBatteryBinding

class BatteryDialog(private val context: FragmentActivity) {
    private lateinit var binding: DialogBatteryBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogBatteryBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val batteryLevelString = "L2"
        binding.dialogBatteryText.text = setBatteryLevelText(batteryLevelString)

//        binding.dialogHeader.dialogCloseButton.setOnClickListener {
//            dialog.dismiss()
//        }

        binding.dialogBatteryDismissButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setBatteryLevelText(batteryLevelString: String): SpannableString {
        val batteryLevelFormat = String.format(context.resources.getString(R.string.battery_content_string), batteryLevelString)
        val spannableString = SpannableString(batteryLevelFormat)

        val startString = batteryLevelFormat.indexOf(batteryLevelString)
        val endString = startString + batteryLevelString.length

        spannableString.setSpan(StyleSpan(Typeface.BOLD), startString, endString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.rgb(95, 200, 143)), startString, endString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(AbsoluteSizeSpan(80), startString, endString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannableString
    }
}
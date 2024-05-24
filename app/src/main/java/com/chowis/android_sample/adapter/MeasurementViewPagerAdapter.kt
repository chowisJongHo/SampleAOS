package com.chowis.android_sample.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.ItemViewPagerBinding

class MeasurementViewPagerAdapter(private val context: Context): RecyclerView.Adapter<MeasurementViewPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MeasurementViewPagerAdapter.ViewHolder {
        return ViewHolder(ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MeasurementViewPagerAdapter.ViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.itemPagerTitle.text = context.getString(R.string.measurement_moisture)

            holder.binding.itemPagerContentText.text = spannableString("moisture", context.getString(R.string.measurement_moisture_content_string))

            holder.binding.itemPagerImg1.setImageResource(R.drawable.moisture_measurement_1)
            holder.binding.itemPagerImg2.setImageResource(R.drawable.moisture_measurement_2)

        } else {
            holder.binding.itemPagerTitle.text = context.getString(R.string.measurement_sebum)

            holder.binding.itemPagerContentText.text = spannableString("sebum", context.getString(R.string.measurement_sebum_content_string))

            holder.binding.itemPagerGuideText.visibility = View.VISIBLE
            holder.binding.itemPagerGuideText.text = spannableString("guide", context.getString(R.string.measurement_guide_string))

            holder.binding.itemPagerImg1.setImageResource(R.drawable.sebum_measurement_1)
            holder.binding.itemPagerImg2.setImageResource(R.drawable.sebum_measurement_2)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    private fun spannableString(type: String, text: String): SpannableString {
        val spannableString = SpannableString(text)

        when(type) {
            "moisture" -> {
                val tZoneStartString = text.indexOf("T zone")
                val tZoneEndString = tZoneStartString + "T zone".length

                spannableString.setSpan(StyleSpan(Typeface.BOLD), tZoneStartString, tZoneEndString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            "sebum" -> {
                val tZoneStartString = text.indexOf("T zone")
                val tZoneEndString = tZoneStartString + "T zone".length

                val uZoneStartString = text.indexOf("U zone")
                val uZoneEndString = uZoneStartString + "U zone".length

                spannableString.setSpan(StyleSpan(Typeface.BOLD), tZoneStartString, tZoneEndString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(StyleSpan(Typeface.BOLD), uZoneStartString, uZoneEndString, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            "guide" -> {
                var stringIndex = text.indexOf("*")
                while (stringIndex != -1) {
                    spannableString.setSpan(ForegroundColorSpan(Color.rgb(255, 109, 109)), stringIndex, stringIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    stringIndex = text.indexOf("*", stringIndex + 1)
                }
            }
        }

        return spannableString
    }


    class ViewHolder(val binding: ItemViewPagerBinding): RecyclerView.ViewHolder(binding.root) {}
}
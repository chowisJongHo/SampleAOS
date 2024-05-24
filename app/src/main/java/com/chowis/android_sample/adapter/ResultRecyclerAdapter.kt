package com.chowis.android_sample.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.ItemResultBinding
import com.chowis.android_sample.listener.ResultImageListener
import com.chowis.android_sample.model.MyResult

class ResultRecyclerAdapter(
    private val context: Context,
    private val resultData: ArrayList<MyResult>,
    private val listener: ResultImageListener
) : RecyclerView.Adapter<ResultRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultRecyclerAdapter.ViewHolder {
        return ViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ResultRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(context, resultData[position])

        holder.binding.itemResultImageText.setOnClickListener {
            listener.resultImageListener(resultData[position].imagePath!!)
        }
    }

    override fun getItemCount(): Int {
        return resultData.size
    }

    class ViewHolder(val binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, data: MyResult) {
            binding.itemResultTitle.text = data.measurementsTitle
            binding.itemResultValue.text = data.scoreValue
            binding.itemResultIndex.text = data.indexTitle
            if (data.imagePath?.length != 0) {
                if (data.imagePath != null) {
                    binding.itemResultImageText.text = context.getString(R.string.result_view_image)
                } else {
                    binding.itemResultImageText.visibility = View.GONE
                }
            } else {
                binding.itemResultImageText.visibility = View.GONE
            }
            binding.itemResultImageText.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
    }
}
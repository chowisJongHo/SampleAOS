package com.chowis.android_sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chowis.android_sample.databinding.ItemSampleBinding
import com.chowis.android_sample.model.SampleDB

class SampleViewRecyclerAdapter(context: Context, val sampleData: ArrayList<SampleDB>, private val listener: SampleSelectListener): RecyclerView.Adapter<SampleViewRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SampleViewRecyclerAdapter.ViewHolder {
        return ViewHolder(ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(sampleData[position])
        holder.binding.itemSampleImage.setOnClickListener {
            listener.sampleSelectListener(sampleData[position].sampleImage)
        }
    }

    override fun getItemCount(): Int {
        return sampleData.size
    }

    class ViewHolder(val binding: ItemSampleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SampleDB) {
            binding.itemSampleImage.setImageResource(data.sampleImage)
        }
    }

    interface SampleSelectListener {
        fun sampleSelectListener(position: Int)
    }

}
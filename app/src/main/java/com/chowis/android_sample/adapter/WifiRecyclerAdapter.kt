package com.chowis.android_sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chowis.android_sample.databinding.ItemWifiBinding

class WifiRecyclerAdapter(val context: Context): RecyclerView.Adapter<WifiRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WifiRecyclerAdapter.ViewHolder {
        return ViewHolder(ItemWifiBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WifiRecyclerAdapter.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 5
    }


    class ViewHolder(val binding: ItemWifiBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }
}
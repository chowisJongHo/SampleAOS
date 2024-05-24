package com.chowis.android_sample.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.ItemSkinBinding
import com.chowis.android_sample.model.SkinGroup

class SkinRecyclerAdapter(
    val context: Context,
    val skinGroupData: ArrayList<SkinGroup>,
    val listener: OnSkinItemClickListener
): RecyclerView.Adapter<SkinRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SkinRecyclerAdapter.ViewHolder {
        return ViewHolder(ItemSkinBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SkinRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(skinGroupData[position], context)
        holder.binding.skinGroupRelative.setOnClickListener {
            listener.onSkinItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return skinGroupData.size
    }

    class ViewHolder(val binding: ItemSkinBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SkinGroup, context: Context) {
            binding.skinGroupTitle.text = data.skinName
            val drawable = ContextCompat.getDrawable(context, R.drawable.item_skin_background) as GradientDrawable
            drawable.setColor(ContextCompat.getColor(context, data.skinBackgroundColor))
            binding.skinGroupRelative.background = drawable
        }
    }

    interface OnSkinItemClickListener {
        fun onSkinItemClick(position: Int)
    }
}
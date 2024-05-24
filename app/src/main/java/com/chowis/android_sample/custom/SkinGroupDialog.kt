package com.chowis.android_sample.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.chowis.android_sample.R
import com.chowis.android_sample.adapter.SkinRecyclerAdapter
import com.chowis.android_sample.databinding.DialogSkinGroupBinding
import com.chowis.android_sample.listener.SkinItemListener
import com.chowis.android_sample.model.SkinGroup

class SkinGroupDialog(private val context: FragmentActivity, private val skinGroupData: ArrayList<SkinGroup>, private val listener: SkinItemListener) {

    private lateinit var binding: DialogSkinGroupBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogSkinGroupBinding.inflate(context.layoutInflater)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogHeader.dialogCloseButton.setOnClickListener {
            listener.close()
            dialog.dismiss()
        }

        val recyclerView = binding.skinRecycler
        val adapter = SkinRecyclerAdapter(context, skinGroupData, object : SkinRecyclerAdapter.OnSkinItemClickListener {
            override fun onSkinItemClick(position: Int) {
                listener.getSelectSkin(position)
                dialog.dismiss()
            }
        })
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = adapter

        dialog.show()
    }
}
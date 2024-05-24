package com.chowis.android_sample.fragment

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.chowis.android_sample.custom.FirmwareDialog
import com.chowis.android_sample.custom.LoadingDialog
import com.chowis.android_sample.databinding.FragmentFirmwareBinding
import com.chowis.android_sample.listener.FirmwareUpdateClickListener
import com.chowis.android_sample.viewModel.MainViewModel
import com.chowis.sdk.newcnd.CNDManager

class FirmwareFragment : Fragment(), FirmwareUpdateClickListener {

    private lateinit var binding: FragmentFirmwareBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirmwareBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.firmwareVersionText.text = "V ${viewModel.firmwareVersion.value}"

        viewModel.firmwareVersion.observe(this, Observer {
            binding.firmwareVersionText.text = "V. $it"
        })

//        binding.firmwareUpdateButton.setOnClickListener {
//            val dialog = FirmwareDialog(requireActivity(), true, this)
//            dialog.show()
//        }
    }

    override fun firmwareUpdateClick() {
        val dialog = LoadingDialog(requireActivity())
        dialog.show()
    }
}
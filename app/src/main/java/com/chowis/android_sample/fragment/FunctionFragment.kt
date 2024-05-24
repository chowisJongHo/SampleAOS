package com.chowis.android_sample.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.chowis.android_sample.R
import com.chowis.android_sample.custom.BatteryDialog
import com.chowis.android_sample.custom.WifiDialog
import com.chowis.android_sample.databinding.FragmentFunctionBinding
import com.chowis.android_sample.listener.WifiSelectListener
import com.chowis.android_sample.viewModel.MainViewModel
import com.chowis.sdk.newcnd.CNDManager

class FunctionFragment : Fragment(), WifiSelectListener, CNDManager.CNDManagerListener {

    private lateinit var binding: FragmentFunctionBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFunctionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cndManager = viewModel.setCndManager()
        cndManager.initialize(this)

        binding.functionFirmwareButton.setOnClickListener {
            viewModel.firmwareButtonClick(true)
            viewModel.firmwareVersion(cndManager.getSDKFirmwareVersion())
        }

//        binding.functionWifiButton.text = String.format(requireContext().resources.getString(R.string.function_wifi_string), viewModel.getWifiGHzString())
        binding.functionWifiButton.setOnClickListener {
            val dialog = WifiDialog(requireActivity(), viewModel.setNowBatteryLevel(), this)
            dialog.show()
        }

        binding.functionBatteryButton.setOnClickListener {
            val dialog = BatteryDialog(requireActivity())
            dialog.show()
        }

        viewModel.wifiGHzString.observe(requireActivity(), Observer {
            binding.functionWifiButton.text = String.format(requireContext().resources.getString(R.string.function_wifi_string), it)
        })
    }

    override fun wifiSelectListener(GHz: String) {
//        viewModel.setWifiGHzString(GHz)
    }

    /**
     * CndManagerListener
     * */

    override fun on1MinLeftBeforeSleep() {}
    override fun onBatteryValue(batteryValue: Int) {
        viewModel.batteryLevel = batteryValue
    }
    override fun onButtonPressed() {}
    override fun onCameraModeChangeComplete() {}
    override fun onConnected() {}
    override fun onConnectionFailed() {}
    override fun onDeviceCaptured(bitmap: Bitmap?) {}
    override fun onDisconnected() {}
    override fun onLensValue(lensEnum: CNDManager.CNDLens) {}
    override fun onResultResponse(requestCode: Int, result: String) {}
}
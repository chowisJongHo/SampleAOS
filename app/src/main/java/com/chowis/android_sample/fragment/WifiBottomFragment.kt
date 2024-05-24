package com.chowis.android_sample.fragment

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chowis.android_sample.R
import com.chowis.android_sample.adapter.SkinRecyclerAdapter
import com.chowis.android_sample.adapter.WifiRecyclerAdapter
import com.chowis.android_sample.databinding.BottomSheetWifiBinding
import com.chowis.android_sample.utils.WifiUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WifiBottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetWifiBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetWifiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wifiManager = requireContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiUtils = WifiUtils(requireContext(), wifiManager)

        wifiUtils.wifiCheck()

        binding.bottomWifiRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.bottomWifiRecycler.adapter = WifiRecyclerAdapter(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }
}
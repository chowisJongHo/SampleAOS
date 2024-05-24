package com.chowis.android_sample.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chowis.android_sample.custom.DeviceDialog
import com.chowis.android_sample.custom.SkinGroupDialog
import com.chowis.android_sample.databinding.FragmentMainBinding
import com.chowis.android_sample.listener.OptionNumberListener
import com.chowis.android_sample.listener.SkinItemListener
import com.chowis.android_sample.view.VideoViewActivity
import com.chowis.android_sample.viewModel.MainViewModel

class MainFragment : Fragment(), OptionNumberListener, SkinItemListener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragMainRegistrationButton.setOnClickListener {
            val dialog = DeviceDialog(requireActivity(), this)
            dialog.show()
        }

        binding.fragMainAnalysisButton.setOnClickListener {
            viewModel.analysisButtonClick(true)
        }

        binding.fragMainFunctionButton.setOnClickListener {
            viewModel.functionButtonClick(true)
        }

        binding.fragMainSampleButton.setOnClickListener {
            val dialog = SkinGroupDialog(requireActivity(), viewModel.setSkinGroupData(), this)
            dialog.show()
        }
    }

    override fun optionNumber(optionNumber: String) {
        viewModel.setOpticNumber(optionNumber)
    }

    override fun getSelectSkin(position: Int) {
        viewModel.getSelectSkin(position)
    }

    override fun close() {}
}
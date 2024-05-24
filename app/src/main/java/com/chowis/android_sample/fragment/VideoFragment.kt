package com.chowis.android_sample.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.FragmentVideoBinding
import com.chowis.sdk.newcnd.CNDManager
import com.chowis.sdk.newcnd.CNDPlayer

class VideoFragment : Fragment(), CNDManager.CNDManagerListener, CNDPlayer.CNDPlayerListener {

    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cndManager = CNDManager.getInstance(requireContext())
        cndManager.initialize(this)

//        binding.fragVideoCndPlayer.initialize(this, false)
//        cndManager.connectSocket(false)
//        binding.fragVideoCndPlayer.play()
    }

    /**
     * CNDManagerListener
     * */
    override fun on1MinLeftBeforeSleep() {
    }

    override fun onBatteryValue(batteryValue: Int) {
    }

    override fun onButtonPressed() {
    }

    override fun onCameraModeChangeComplete() {
    }

    override fun onConnected() {
        Log.d("TAG", "onConnected: ")
    }

    override fun onConnectionFailed() {
        Log.d("TAG", "onConnectionFailed: ")
        
    }

    override fun onDeviceCaptured(bitmap: Bitmap?) {
    }

    override fun onDisconnected() {
        Log.d("TAG", "onDisconnected: ")
    }

    override fun onLensValue(lensEnum: CNDManager.CNDLens) {
    }

    override fun onResultResponse(requestCode: Int, result: String) {
    }

    /**
     * CNDPlayerListener
     * */
    override fun onPlayFailed() {
    }

    override fun onPlayed() {
        Log.d("TAG", "onPlayed: ")
    }

    override fun onPlayerCaptured(bitmap: Bitmap?) {
    }

    override fun onStopped() {
    }
}
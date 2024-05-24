package com.chowis.android_sample.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chowis.android_sample.R
import com.chowis.android_sample.custom.LoadingDialog
import com.chowis.android_sample.databinding.ActivityCaptureBinding
import com.chowis.android_sample.utils.ImageSaver
import com.chowis.android_sample.viewModel.CaptureViewModel
import com.chowis.sdk.newcnd.CNDManager
import com.chowis.sdk.newcnd.CNDPlayer

class CaptureActivity : AppCompatActivity(), CNDManager.CNDManagerListener,
    CNDPlayer.CNDPlayerListener {

    private lateinit var binding: ActivityCaptureBinding
    private val viewModel: CaptureViewModel by viewModels()

    private lateinit var cndManager: CNDManager

    private lateinit var loadingDialog: LoadingDialog

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@CaptureActivity, R.layout.activity_capture)
        binding.captureViewModel = viewModel
        binding.lifecycleOwner = this@CaptureActivity

        setCndManager()

        binding.activityVideoHeader.videoBackButton.setOnClickListener {
            goingMainIntent()
        }

        binding.captureButton.setOnClickListener {
            binding.captureCndPlayer.requestCapture()
        }

        binding.captureCloseButton.setOnClickListener {
            cndManager.requestCameraMode(CNDManager.CNDCameraMode.SKIN_VSL)

            binding.captureImage.visibility = View.GONE

            binding.captureCloseButton.visibility = View.GONE

            binding.captureButton.visibility = View.VISIBLE

            binding.captureDoneButton.isEnabled = true
            binding.captureDoneButton.backgroundTintList =
                this.resources.getColorStateList(R.color.button_gray_color)
            binding.captureDoneButton.setTextColor(Color.parseColor("#5A5A5A"))
        }

        viewModel.captureImage.observe(this, Observer {
            binding.captureImage.visibility = View.VISIBLE
            binding.captureImage.setImageBitmap(it)

            binding.captureCloseButton.visibility = View.VISIBLE

            binding.captureButton.visibility = View.INVISIBLE

            binding.captureDoneButton.isEnabled = true
            binding.captureDoneButton.backgroundTintList =
                this.resources.getColorStateList(R.color.button_mint_color)
            binding.captureDoneButton.setTextColor(this.getColor(R.color.white))

            cndManager.requestLEDOff()
        })

        viewModel.batteryValue.observe(this, Observer {
            binding.activityVideoHeader.activityBattery.text = "$it%"
        })

        binding.captureDoneButton.isEnabled = false
        binding.captureDoneButton.setOnClickListener {
            val saver = ImageSaver.saveImage(this, viewModel.captureImage.value!!, "app")
            viewModel.mySkinType("/sdcard/$saver.jpg")

            finish()
        }
    }

    private fun setCndManager() {
        loadingDialog = LoadingDialog(this)
        loadingDialog.show()

        cndManager = viewModel.setCNDManager()

        cndManager.initialize(this)
        cndManager.connectSocket(false)

        binding.captureCndPlayer.initialize(this, false)
        binding.captureCndPlayer.play()
    }

    private fun goingMainIntent() {
        val intent = Intent(this@CaptureActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(intent)
        finish()
    }

    private fun reconnectDialogShow() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.reconnect_title)
        builder.setMessage(R.string.reconnect_message)

        builder.setPositiveButton(R.string.reconnect_button, DialogInterface.OnClickListener { _, _ ->
            setCndManager()
        })

        builder.setNeutralButton(R.string.reconnect_not, DialogInterface.OnClickListener { _, _ ->
            goingMainIntent()
        })

        runOnUiThread {
            builder.create()
            builder.show()
        }
    }

    override fun onDestroy() {
        binding.captureCndPlayer.close()

        super.onDestroy()
    }

    /**
     * CNDManagerListener
     * */
    override fun on1MinLeftBeforeSleep() {}

    override fun onBatteryValue(batteryValue: Int) {
        viewModel.setBatteryValue(batteryValue)
    }

    override fun onButtonPressed() {
        if (binding.captureImage.visibility == View.GONE) {
            binding.captureCndPlayer.requestCapture()
        }
    }

    override fun onCameraModeChangeComplete() {
        loadingDialog.hide()
    }

    override fun onConnected() {}

    override fun onConnectionFailed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.connect_failed_title)
        builder.setMessage(R.string.connect_failed_message)

        builder.setNeutralButton(R.string.connect_failed_button, DialogInterface.OnClickListener { _, _ ->
            goingMainIntent()
        })

        runOnUiThread {
            builder.create()
            builder.show()
        }
    }

    override fun onDeviceCaptured(bitmap: Bitmap?) {}

    override fun onDisconnected() {
        reconnectDialogShow()
    }

    override fun onLensValue(lensEnum: CNDManager.CNDLens) {}
    override fun onResultResponse(requestCode: Int, result: String) {}

    /**
     * CNDPlayerListener
     * */
    override fun onPlayFailed() {}
    override fun onPlayed() {
        cndManager.setSkinGroup(CNDManager.SkinGroup.SKIN_GROUP_34)
        cndManager.requestCameraMode(CNDManager.CNDCameraMode.SKIN_VSL)
    }

    @SuppressLint("ResourceAsColor")
    override fun onPlayerCaptured(bitmap: Bitmap?) {
        viewModel.setCaptureImage(bitmap)
    }

    override fun onStopped() {}
}
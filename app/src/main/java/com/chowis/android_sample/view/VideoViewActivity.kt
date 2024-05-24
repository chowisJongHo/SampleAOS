package com.chowis.android_sample.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chowis.android_sample.R
import com.chowis.android_sample.custom.CaptureDialog
import com.chowis.android_sample.custom.LoadingDialog
import com.chowis.android_sample.custom.MeasurementDialog
import com.chowis.android_sample.custom.SkinGroupDialog
import com.chowis.android_sample.databinding.ActivityVideoViewBinding
import com.chowis.android_sample.listener.CaptureTypeSelectListener
import com.chowis.android_sample.listener.MeasurementListener
import com.chowis.android_sample.listener.SkinItemListener
import com.chowis.android_sample.utils.CameraType
import com.chowis.android_sample.utils.ImageSaver
import com.chowis.android_sample.viewModel.VideoViewModel
import com.chowis.sdk.newcnd.CNDManager
import com.chowis.sdk.newcnd.CNDPlayer
import java.io.File

class VideoViewActivity : AppCompatActivity(), CaptureTypeSelectListener,
    CNDManager.CNDManagerListener, CNDPlayer.CNDPlayerListener,
    SkinItemListener, MeasurementListener {

    private lateinit var binding: ActivityVideoViewBinding
    private val viewModel: VideoViewModel by viewModels()

    private lateinit var mCndManager: CNDManager

    private lateinit var loadingDialog: LoadingDialog

    private var selectType = 100

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_view)
        binding.videoViewModel = viewModel
        binding.lifecycleOwner = this@VideoViewActivity

        viewModel.setCaptureIsFirst(true)
        loadingDialog = LoadingDialog(this)

        binding.activityVideoHeader.videoBackButton.setOnClickListener {
            finish()
        }

        binding.videoMoistureButton.tag = CameraType.MOISTURE_U.typeCode
        binding.videoSpotButton.tag = CameraType.SPOT.typeCode
        binding.videoPoresButton.tag = CameraType.PORES.typeCode
        binding.videoImpuritiesButton.tag = CameraType.IMPURITIES.typeCode
        binding.videoWrinkleButton.tag = CameraType.WRINKLE.typeCode
        binding.videoKeratinButton.tag = CameraType.KERATIN.typeCode
        binding.videoSensitivityButton.tag = CameraType.SENSITIVITY.typeCode
        binding.videoSebumButton.tag = CameraType.SEBUM.typeCode

        binding.videoMoistureButton.setOnClickListener {
            selectType = CameraType.MOISTURE_T.typeCode
            viewModel.moistureLayerSetting(false)
            it.isSelected = true
            mCndManager.requestLEDOff()

            viewModel.setButtonSelected(CameraType.MOISTURE_U.typeCode)
        }

        binding.videoTZoneButton.setOnClickListener {
            Toast.makeText(this, "T zone", Toast.LENGTH_SHORT).show()
            selectType = CameraType.MOISTURE_T.typeCode
        }

        binding.videoUZoneButton.setOnClickListener {
            Toast.makeText(this, "U zone", Toast.LENGTH_SHORT).show()
            selectType = CameraType.MOISTURE_U.typeCode
        }

        binding.videoSpotButton.setOnClickListener {
            settingCameraMode(CameraType.SPOT.typeCode)
        }

        binding.videoPoresButton.setOnClickListener {
            settingCameraMode(CameraType.PORES.typeCode)
        }

        binding.videoImpuritiesButton.setOnClickListener {
            settingCameraMode(CameraType.IMPURITIES.typeCode)
        }

        binding.videoWrinkleButton.setOnClickListener {
            settingCameraMode(CameraType.WRINKLE.typeCode)
        }

        binding.videoKeratinButton.setOnClickListener {
            settingCameraMode(CameraType.KERATIN.typeCode)
        }

        binding.videoSensitivityButton.setOnClickListener {
            settingCameraMode(CameraType.SENSITIVITY.typeCode)
        }

        binding.videoSebumButton.setOnClickListener {
            settingCameraMode(CameraType.SEBUM.typeCode)
        }

        binding.videoCaptureCloseButton.setOnClickListener {
            binding.videoCaptureImageView.visibility = View.GONE
            binding.videoCaptureCloseButton.visibility = View.GONE

            binding.videoAnalyzeLinear.isEnabled = false
        }

        binding.videoAnalyzeLinear.isEnabled = false
        binding.videoAnalyzeLinear.setOnClickListener {
            val imageSaver = ImageSaver.saveImage(this, viewModel.captureImage.value!!, "app")
            val pathString = "/sdcard/$imageSaver.jpg"

            viewModel.saveAddData(pathString, selectType)

            binding.videoViewConstraint.findViewWithTag<AppCompatButton>(selectType)
                .setBackgroundResource(R.drawable.video_success_button_background)

            binding.videoAnalyzeLinear.isEnabled = false
            viewModel.nextType(selectType)
        }

        binding.videoResultButton.setOnClickListener {
            loadingDialog.show()

            viewModel.settingResult()
        }

        viewModel.batteryValue.observe(this, Observer {
            binding.activityVideoHeader.activityBattery.text = "$it%"
        })

        viewModel.selectSkin.observe(this, Observer {
            binding.videoSkinSelectText.text = it
        })

        viewModel.selectType.observe(this, Observer {
            binding.videoTypeSelectText.text = it
        })

        viewModel.selectButton.observe(this, Observer {
            for (i in 1..8) {
                if (i == it) {
                    binding.videoViewConstraint.findViewWithTag<AppCompatButton>(it).isSelected =
                        true

                    binding.videoCaptureImageView.visibility = View.GONE
                    binding.videoCaptureCloseButton.visibility = View.GONE
                } else {
                    binding.videoViewConstraint.findViewWithTag<AppCompatButton>(i).isSelected =
                        false
                }
            }
        })

        viewModel.tZoneResult.observe(this, Observer {
            binding.videoTZoneText.text = "T zone: $it"
            binding.videoTZoneButton.setImageResource(R.drawable.moisture_success_img)
        })

        viewModel.uZoneResult.observe(this, Observer {
            binding.videoUZoneText.text = "U zone: $it"
            binding.videoUZoneButton.setImageResource(R.drawable.moisture_success_img)
        })

        viewModel.moistureCheck.observe(this, Observer {
            if (it) {
                binding.videoMoistureButton.setBackgroundResource(R.drawable.video_success_button_background)
            } else {
                binding.videoMoistureButton.setBackgroundResource(R.drawable.video_button_select)
            }
        })

        viewModel.moistureLayerHide.observe(this, Observer {
            if (it) {
                binding.videoZoneContainer.visibility = View.GONE
            } else {
                binding.videoZoneContainer.visibility = View.VISIBLE
            }
        })

        viewModel.captureImage.observe(this, Observer {
            binding.videoCaptureImageView.visibility = View.VISIBLE
            binding.videoCaptureCloseButton.visibility = View.VISIBLE

            binding.videoCaptureImageView.setImageBitmap(it)

            binding.videoAnalyzeLinear.isEnabled = true
        })

        viewModel.nextType.observe(this, Observer {
            binding.videoViewConstraint.findViewWithTag<AppCompatButton>(it).performClick()

            binding.videoCaptureImageView.visibility = View.GONE
            binding.videoCaptureCloseButton.visibility = View.GONE
        })

        viewModel.beforeDataCheck.observe(this, Observer {
            binding.videoCaptureImageView.visibility = View.VISIBLE
            binding.videoCaptureCloseButton.visibility = View.VISIBLE

            val image = File(it)
            val bitmap = BitmapFactory.decodeFile(image.absolutePath)

            binding.videoCaptureImageView.setImageBitmap(bitmap)
        })

        viewModel.myResultData.observe(this, Observer {
            loadingDialog.hide()

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("myResult", it)

            startActivity(intent)
        })
    }

    private fun showCaptureDialog() {
        val dialog = CaptureDialog(this@VideoViewActivity, this)
        dialog.show()
    }

    private fun setCndManager() {
        viewModel.setCaptureIsFirst(false)

        mCndManager = viewModel.setCndManager()
        mCndManager.initialize(this)
        mCndManager.connectSocket(false)

        binding.videoCaptureImageView.visibility = View.GONE
        binding.videoCaptureCloseButton.visibility = View.GONE

        binding.videoCndPlayer.initialize(this, false)
        binding.videoCndPlayer.play()

        if (viewModel.getMySkinType() != 1001) {
            viewModel.setSkinGroup(viewModel.getMySkinType() - 1)
        }
        mCndManager.setSkinGroup(viewModel.getSkinGroup())

        for (i in 1..8) {
            binding.videoViewConstraint.findViewWithTag<AppCompatButton>(i).isSelected = false
        }

        selectType = CameraType.MOISTURE_T.typeCode

        binding.videoMoistureButton.performClick()
        binding.videoTZoneText.text = "T zone:"
        binding.videoUZoneText.text = "U zone:"

        binding.videoZoneContainer.visibility = View.VISIBLE
    }

    private fun settingCameraMode(cameraType: Int) {
        loadingDialog.show()

        selectType = cameraType
        mCndManager.requestCameraMode(viewModel.cameraModeChange(cameraType))

        viewModel.setButtonSelected(cameraType)
    }

    private fun reconnectDialogShow() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle(R.string.reconnect_title)
        builder.setMessage(R.string.reconnect_message)

        builder.setPositiveButton(R.string.reconnect_button, DialogInterface.OnClickListener { _, _ ->
            setCndManager()
        })

        builder.setNeutralButton(R.string.reconnect_not, DialogInterface.OnClickListener { _, _ ->
            finish()
        })

        runOnUiThread {
            builder.create()
            builder.show()
        }
    }

    override fun captureTypeSelectListener(type: String) {
        when (type) {
            "capture" -> {
                startActivity(Intent(this@VideoViewActivity, CaptureActivity::class.java))
            }

            "captureManual" -> {
                val dialog = SkinGroupDialog(this, viewModel.setSkinDialogData(), this)
                dialog.show()
            }

            "close" -> finish()
        }
    }

    override fun onResume() {
        if (viewModel.getCaptureIsFirst()) {
            showCaptureDialog()
        } else {
            val dialog = MeasurementDialog(this, this)
            dialog.show()
        }

        viewModel.setCaptureIsFirst(false)
        super.onResume()
    }

    override fun getSelectSkin(position: Int) {
        viewModel.setSkinGroup(position)
        val dialog = MeasurementDialog(this, this)
        dialog.show()
    }

    override fun close() {
        finish()
    }

    override fun measurementListener() {
        setCndManager()
    }

    override fun onDestroy() {
        binding.videoCndPlayer.close()

        super.onDestroy()
    }

    /**
     * CNDManagerListener
     * */
    override fun on1MinLeftBeforeSleep() {
        Log.d("TAG", "cndListener: 1minSleep")
    }

    override fun onBatteryValue(batteryValue: Int) {
        viewModel.setBatteryValue(batteryValue)
    }

    override fun onButtonPressed() {
        Log.d("TAG", "onButtonPressed: ")
        when (selectType) {
            CameraType.MOISTURE_T.typeCode,
            CameraType.MOISTURE_U.typeCode -> mCndManager.requestMoistureValue(selectType, true)

            else -> binding.videoCndPlayer.requestCapture()
        }
    }

    override fun onCameraModeChangeComplete() {
        loadingDialog.hide()
    }

    override fun onConnected() {
        Log.d("TAG", "cndListener: connected")
    }

    override fun onConnectionFailed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.connect_failed_title)
        builder.setMessage(R.string.connect_failed_message)

        builder.setNeutralButton(R.string.connect_failed_button, DialogInterface.OnClickListener { dialog, _ ->
            finish()
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
    override fun onResultResponse(requestCode: Int, result: String) {
        Log.d("TAG", "onResultResponse: $requestCode / $result")
        viewModel.setMoistureValue(selectType, result)
    }

    /**
     * CNDPlayerListener
     * */
    override fun onPlayFailed() {}
    override fun onPlayed() {}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPlayerCaptured(bitmap: Bitmap?) {
        if (binding.videoCaptureImageView.visibility == View.GONE) {
            if (bitmap != null) {
                viewModel.setCaptureImage(bitmap)
            }
        }
    }

    override fun onStopped() {}
}
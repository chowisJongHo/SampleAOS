package com.chowis.android_sample.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.chowis.android_sample.viewModel.MainViewModel
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.ActivityMainBinding
import com.chowis.android_sample.fragment.FirmwareFragment
import com.chowis.android_sample.fragment.FunctionFragment
import com.chowis.android_sample.fragment.MainFragment
import com.chowis.android_sample.utils.WifiUtils
import com.chowis.jniimagepro.JNIImageProCWCNDPSkinLocal
import java.io.File

class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 1001

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var getResult: ActivityResultLauncher<Intent>

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val uri = Uri.parse("package:" + "com.chowis.android_sample")
                startActivity(Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri))
            } else {
                permissionCheck()
            }
        } else {
            permissionCheck()
        }

        binding.mainLanguageButton.setOnClickListener {
            val languageItems: Array<String> = resources.getStringArray(R.array.language)
            val builder = AlertDialog.Builder(this)
            builder.setItems(languageItems) { _, which ->
                Log.d("TAG", "onCreate: $which")
            }

            val dialog = builder.create()
            dialog.show()
        }

        supportFragmentManager.beginTransaction().add(R.id.main_frame, MainFragment()).commit()

        viewModel.analysisButtonClick.observe(this, Observer {
            val intent = Intent(Settings.Panel.ACTION_WIFI)
            getResult.launch(intent)
        })

        viewModel.functionButtonClick.observe(this, Observer {
//            addFragment(FunctionFragment())
            val intent = Intent(Settings.Panel.ACTION_WIFI)
            getResult.launch(intent)
        })

        viewModel.firmwareButtonClick.observe(this, Observer {
            addFragment(FirmwareFragment())
        })

//        viewModel.firmwareVersion.observe(this, Observer {
//            addFragment(FirmwareFragment())
//        })

        viewModel.selectSkin.observe(this, Observer {
            val intent = Intent(this@MainActivity, SampleDBActivity::class.java)
            intent.putExtra("selectSkin", "SG${it + 1}")

            startActivity(intent)
        })

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.getWifiName()
        }

        viewModel.getWifiNameCheck.observe(this, Observer {
            if (it) {
                if (viewModel.functionButtonClick.value!!) {
                    addFragment(FunctionFragment())
                } else {
                    startActivity(Intent(this@MainActivity, VideoViewActivity::class.java))
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.cid_check_string),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_frame, fragment)
            .addToBackStack(null).commit()
    }

    private fun permissionCheck() {
        var permission = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                permission = true
            }
        }
//        else {
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                permission = true
//            }
//        }

        if (!permission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    var notGrantedExists = false
                    for (grantResult in grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            notGrantedExists = true
                        }
                    }
                    if (notGrantedExists) {
                        finish()
                    }
                } else {
                    finish()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onRestart() {
        super.onRestart()
        permissionCheck()
    }
}
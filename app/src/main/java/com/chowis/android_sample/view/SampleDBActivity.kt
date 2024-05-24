package com.chowis.android_sample.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chowis.android_sample.R
import com.chowis.android_sample.databinding.ActivitySampleDbactivityBinding
import com.chowis.android_sample.fragment.SampleMainFragment
import com.chowis.android_sample.viewModel.SampleDBViewModel

class SampleDBActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleDbactivityBinding
    private val viewModel: SampleDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_dbactivity)
//        binding.dbViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.setActivityTitle(intent.getStringExtra("selectSkin").toString())

//        supportFragmentManager.beginTransaction().add(R.id.sample_frame, SampleMainFragment()).commit()

        viewModel.titleText.observe(this, Observer {
            binding.dbTitleText.text = it
        })

        binding.dbSpotButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbPoresButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbWrinkleButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbImpuritiesButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbKeratinButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbSensitivityButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbSebumButton.setOnClickListener {
            nextActivity(viewModel.nowSkinGroup, (it as Button).text.toString())
        }

        binding.dbBackButton.setOnClickListener {
            finish()
        }
    }

    private fun nextActivity(skinGroup: String, measurement: String) {
        val intent = Intent(this@SampleDBActivity, SampleViewActivity::class.java)
        intent.putExtra("skinGroup", skinGroup)
        intent.putExtra("measurement", measurement)
        startActivity(intent)
    }
}
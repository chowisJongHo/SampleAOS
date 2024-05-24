package com.chowis.android_sample.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chowis.android_sample.R
import com.chowis.android_sample.adapter.SampleViewRecyclerAdapter
import com.chowis.android_sample.custom.SampleExpandDialog
import com.chowis.android_sample.databinding.ActivitySampleViewBinding
import com.chowis.android_sample.model.SampleDB
import com.chowis.android_sample.viewModel.SampleViewModel

class SampleViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleViewBinding
    private val viewModel: SampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_view)
        binding.sampleViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.setActivityTitle("${intent.getStringExtra("skinGroup")} ${intent.getStringExtra("measurement")}")
        viewModel.getSampleList(intent.getStringExtra("skinGroup").toString(), intent.getStringExtra("measurement").toString())

        viewModel.skinGroup.observe(this@SampleViewActivity, Observer {
            binding.sampleViewTitleText.text = it
        })

        viewModel.sampleList.observe(this@SampleViewActivity, Observer {
            val adapter = SampleViewRecyclerAdapter(this, it, object : SampleViewRecyclerAdapter.SampleSelectListener {
                override fun sampleSelectListener(position: Int) {
                    val dialog = SampleExpandDialog(this@SampleViewActivity, position)
                    dialog.show()
                }
            })

            binding.sampleViewRecycler.layoutManager = LinearLayoutManager(this@SampleViewActivity)
            binding.sampleViewRecycler.adapter = adapter

            Log.d("TAG", "onCreate: $it")
        })

        binding.sampleBackButton.setOnClickListener {
            finish()
        }
    }
}
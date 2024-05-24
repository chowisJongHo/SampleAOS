package com.chowis.android_sample.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.chowis.android_sample.R
import com.chowis.android_sample.adapter.ResultRecyclerAdapter
import com.chowis.android_sample.custom.ImageExpansionDialog
import com.chowis.android_sample.databinding.ActivityResultBinding
import com.chowis.android_sample.listener.ResultImageListener
import com.chowis.android_sample.model.MyResult
import com.chowis.android_sample.model.ResultData

class ResultActivity : AppCompatActivity(), ResultImageListener {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        Log.d("TAG", "ResultActivity: ${intent.getSerializableExtra("myResult")}")
        val x: ArrayList<MyResult> = intent.getSerializableExtra("myResult") as ArrayList<MyResult>

        binding.resultRecycler.layoutManager = LinearLayoutManager(this@ResultActivity)
        binding.resultRecycler.adapter = ResultRecyclerAdapter(this@ResultActivity, x, this)

        binding.resultBackButton.setOnClickListener {
            finish()
        }

        binding.resultHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    override fun resultImageListener(filePath: String) {
        val dialog = ImageExpansionDialog(this@ResultActivity, filePath)
        dialog.show()
    }
}
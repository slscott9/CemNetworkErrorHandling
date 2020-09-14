package com.example.networkerrorhandling.ui.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.networkerrorhandling.R
import com.example.networkerrorhandling.databinding.ActivityCemeteryDetailBinding
import com.example.networkerrorhandling.ui.ui.viewmodels.CemeteryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CemeteryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCemeteryDetailBinding
    private val viewModel: CemeteryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cemetery_detail)
        binding.lifecycleOwner = this

//        viewModel.chosenCemetery.observe(this, Observer {
//            it.let {
//
//            }
//        })
    }
}
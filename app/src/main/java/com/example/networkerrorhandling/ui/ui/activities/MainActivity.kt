package com.example.networkerrorhandling.ui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.networkerrorhandling.R
import com.example.networkerrorhandling.databinding.ActivityMainBinding
import com.example.networkerrorhandling.ui.ui.adapters.CemeteryListAdapter
import com.example.networkerrorhandling.ui.ui.adapters.CemeteryListener
import com.example.networkerrorhandling.ui.ui.viewmodels.CemeteryDetailViewModel
import com.example.networkerrorhandling.ui.ui.viewmodels.CemeteryDetailViewModel.Companion.CEM_ID
import com.example.networkerrorhandling.ui.ui.viewmodels.CemeteryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cemeteryListAdapter: CemeteryListAdapter
    private val viewModel: CemeteryListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this


        cemeteryListAdapter = CemeteryListAdapter(CemeteryListener {
            startActivity(
                Intent(this, CemeteryDetailViewModel::class.java).apply {
                    putExtra(CEM_ID, it)
                }
            )
        })
        viewModel.cemeteryList.observe(this, Observer {
            it?.let{
                cemeteryListAdapter.submitList(it)
            }
        })

        binding.cemeteryListRecyclerView.adapter = cemeteryListAdapter

    }


}
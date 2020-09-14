package com.example.networkerrorhandling.ui.ui.viewmodels

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkerrorhandling.data.CemeteryRepository
import kotlinx.coroutines.*
import timber.log.Timber

class CemeteryListViewModel @ViewModelInject constructor(
    private val repository: CemeteryRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){



    init {
        refreshCemeteryList()
        Timber.i("from view model refreshing cemetery list")
    }

     val cemeteryList = repository.getAllCemeteries()


    private fun refreshCemeteryList(){
        viewModelScope.launch {
            repository.refreshCemeteryList()
        }
    }




}
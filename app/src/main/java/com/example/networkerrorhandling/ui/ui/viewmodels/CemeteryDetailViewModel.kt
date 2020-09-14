package com.example.networkerrorhandling.ui.ui.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.networkerrorhandling.data.CemeteryRepository


//Hilt uses @Provides to inject the repository dependency for this class
//ViewModel ktx extentions allow extend ViewModel() class. We dont have to use oncleared this is handled for us.
//The view model scope runs on main for us

class CemeteryDetailViewModel @ViewModelInject constructor(
    private val repository: CemeteryRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _chosenCemetery = repository.getCemteryWithRowId(savedStateHandle.get<Int>(CEM_ID)!!)
    val chosenCemetery = _chosenCemetery



    companion object {
        const val CEM_ID = "cem_id"
    }

}
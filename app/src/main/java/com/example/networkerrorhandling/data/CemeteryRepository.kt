package com.example.networkerrorhandling.data

import androidx.lifecycle.LiveData
import com.example.networkerrorhandling.data.entities.Cemetery
import com.example.networkerrorhandling.data.local.CemeteryDao
import com.example.networkerrorhandling.uitls.asDatabaseModel
import com.example.networkerrorhandling.data.remote.CemeteryRemoteDataSource
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class CemeteryRepository @Inject constructor(
    private val remoteDataSource: CemeteryRemoteDataSource,
    private val cemeteryDao: CemeteryDao
) {

    suspend fun refreshCemeteryList() {
        try {
            val cemeteryResponse = remoteDataSource.getCemeteryListFromNetwork()
            Timber.i(cemeteryResponse.message)
            cemeteryDao.insertAllCemsFromNetwork(*cemeteryResponse.cemeteryNetworkCemeteryContainer!!.asDatabaseModel())
            Timber.i("refresh cemetery method")
        }catch (e: Exception){
            e.printStackTrace() //should catche the IOException
        }

    }

    fun getAllCemeteries() : LiveData<List<Cemetery>>{
        return cemeteryDao.getAllCemeteries()
    }

    fun getCemteryWithRowId(cemRowId: Int): LiveData<Cemetery>{
        return cemeteryDao.getCemeteryWithId(cemRowId)
    }


}
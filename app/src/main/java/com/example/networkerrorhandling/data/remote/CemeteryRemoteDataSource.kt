package com.example.networkerrorhandling.data.remote

import com.example.networkerrorhandling.network.CemeteryService
import com.example.networkerrorhandling.network.SafeApiRequest
import javax.inject.Inject

class CemeteryRemoteDataSource @Inject constructor(
    private val cemeteryService: CemeteryService
): SafeApiRequest() {

    suspend fun getCemeteryListFromNetwork() = apiRequest { cemeteryService.getCemeteriesFromNetwork() }
}
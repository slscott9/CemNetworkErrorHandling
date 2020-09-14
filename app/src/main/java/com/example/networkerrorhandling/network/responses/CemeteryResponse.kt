package com.example.networkerrorhandling.network.responses

import com.example.networkerrorhandling.uitls.NetworkCemeteryContainer


data class CemeteryResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val cemeteryNetworkCemeteryContainer: NetworkCemeteryContainer?
) {
}
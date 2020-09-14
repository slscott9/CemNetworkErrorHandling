package com.example.networkerrorhandling.network

import com.example.networkerrorhandling.data.entities.Cemetery
import com.example.networkerrorhandling.network.responses.CemeteryResponse
import retrofit2.Response
import retrofit2.http.*

interface CemeteryService {
    @GET("/cgi-bin/getCems.pl")
    suspend fun getCemeteriesFromNetwork(): Response<CemeteryResponse>


    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("/cgi-bin/addCems.pl")
    suspend fun sendNewCemeteryToNetwork(@Field("json") json: String): Response<Cemetery>
}


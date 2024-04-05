package com.example.myapplication.api.services

import com.example.myapplication.api.model.LocationResponse
import retrofit2.Call
import retrofit2.http.GET

interface LocationService {

    @GET("location")
    fun getAll(): Call<LocationResponse>
}
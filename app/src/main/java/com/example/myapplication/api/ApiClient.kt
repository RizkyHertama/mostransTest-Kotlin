package com.example.myapplication.api

import com.example.myapplication.api.services.CharacterService
import com.example.myapplication.api.services.LocationService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val  BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val characterService: CharacterService by lazy {
        retrofit.create((CharacterService::class.java))
    }

    val locationService: LocationService by lazy {
        retrofit.create((LocationService::class.java))
    }
}

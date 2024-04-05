package com.example.myapplication.api.services

import com.example.myapplication.api.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
    fun getAll(): Call<CharacterResponse>

    @GET("character/{id}")
    fun getById(@Path("id") id: Int): Call<CharacterResponse>

//    @GET("character")
//    fun getById(@Query("id") id: Int): Call<CharacterResponse>
}
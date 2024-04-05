package com.example.myapplication.api.model

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val image: String,
    val status: String,
    val origin: OriginDetail,
    val location: LocationDetail
)

data class OriginDetail(
    val name: String,
    val url: String
)

data class LocationDetail(
    val name: String,
    val url: String
)

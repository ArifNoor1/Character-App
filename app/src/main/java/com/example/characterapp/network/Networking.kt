package com.example.characterapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    val BASE_URL = "https://rickandmortyapi.com/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)
}
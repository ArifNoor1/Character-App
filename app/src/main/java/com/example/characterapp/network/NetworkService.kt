package com.example.characterapp.network

import com.example.characterapp.model.Character
import com.example.characterapp.model.Result
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("character")
    suspend fun getCharacter() : Response<Character>
}
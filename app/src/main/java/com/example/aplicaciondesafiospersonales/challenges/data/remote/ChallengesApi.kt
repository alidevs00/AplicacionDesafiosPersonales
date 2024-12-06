package com.example.aplicaciondesafiospersonales.challenges.data.remote

import com.example.aplicaciondesafiospersonales.challenges.data.entity.ApiResultEntity
import retrofit2.http.GET
import retrofit2.http.Header

interface ChallengesApi {

    @GET("classes/Desafio")
    suspend fun challengesApi(): ApiResultEntity

}
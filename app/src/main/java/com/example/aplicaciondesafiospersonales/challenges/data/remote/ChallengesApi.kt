package com.example.aplicaciondesafiospersonales.challenges.data.remote

import com.example.aplicaciondesafiospersonales.challenges.data.entity.ApiResultEntity
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChallengesApi {

    @GET("classes/Desafio")
    suspend fun getChallengesApi(): ApiResultEntity

    @POST("classes/Desafio")
    suspend fun addChallengeApi(
        @Body challengeModel: Challenge
    )

}
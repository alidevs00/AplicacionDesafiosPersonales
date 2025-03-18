package com.example.aplicaciondesafiospersonales.challenges.data.remote

import com.example.aplicaciondesafiospersonales.challenges.data.entity.ApiResultEntity
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ChallengeUpdate
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ChallengesApi {

    @GET("classes/Desafio")
    suspend fun getChallengesApi(): ApiResultEntity

    @POST("classes/Desafio")
    suspend fun addChallengeApi(
        @Body challengeModel: Challenge
    )

    @PUT("classes/Desafio/{objectId}")
    suspend fun updateChallengeFieldsApi(
        @Path("objectId") id: String,
        @Body fieldsToUpdate: ChallengeUpdate
    )

    @DELETE("classes/Desafio/{objectId}")
    suspend fun deleteChallenge(
        @Path("objectId") id: String,
    )

}
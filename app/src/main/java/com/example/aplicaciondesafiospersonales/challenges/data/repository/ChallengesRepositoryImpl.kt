package com.example.aplicaciondesafiospersonales.challenges.data.repository

import com.example.aplicaciondesafiospersonales.challenges.data.mapper.toModel
import com.example.aplicaciondesafiospersonales.challenges.data.remote.ChallengesApi
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import javax.inject.Inject

class ChallengesRepositoryImpl @Inject constructor(
    private val challengesApi: ChallengesApi,
) : ChallengesRepository {

    override suspend fun getChallenges(): ApiResult =
        challengesApi.getChallengesApi().toModel()

}
package com.example.aplicaciondesafiospersonales.challenges.data.repository

import arrow.core.Either
import com.example.aplicaciondesafiospersonales.challenges.data.mapper.mapToSpanish
import com.example.aplicaciondesafiospersonales.challenges.data.mapper.toModel
import com.example.aplicaciondesafiospersonales.challenges.data.mapper.toNetworkError
import com.example.aplicaciondesafiospersonales.challenges.data.remote.ChallengesApi
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ChallengeUpdate
import com.example.aplicaciondesafiospersonales.challenges.domain.model.NetworkError
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import javax.inject.Inject

class ChallengesRepositoryImpl @Inject constructor(
    private val challengesApi: ChallengesApi,
) : ChallengesRepository {

    override suspend fun getChallenges(): Either<NetworkError, ApiResult> =
        Either.catch {
            challengesApi.getChallengesApi().toModel()
        }.mapLeft { it.toNetworkError() }

    override suspend fun addChallenge(challenge: Challenge): Either<NetworkError, Unit> =
        Either.catch {
            challengesApi.addChallengeApi(challenge.mapToSpanish())
        }.mapLeft { it.toNetworkError() }

    override suspend fun updateChallenge(
        id: String,
        fieldsToUpdate: ChallengeUpdate
    ): Either<NetworkError, Unit> =
        Either.catch {
            challengesApi.updateChallengeFieldsApi(id, fieldsToUpdate)
        }.mapLeft { it.toNetworkError() }


}
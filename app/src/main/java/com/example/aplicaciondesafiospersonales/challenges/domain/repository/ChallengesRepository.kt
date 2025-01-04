package com.example.aplicaciondesafiospersonales.challenges.domain.repository

import arrow.core.Either
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ChallengeUpdate
import com.example.aplicaciondesafiospersonales.challenges.domain.model.NetworkError

interface ChallengesRepository {
    suspend fun getChallenges(): Either<NetworkError, ApiResult>
    suspend fun addChallenge(challenge: Challenge) : Either<NetworkError, Unit>
    suspend fun updateChallenge(id: String, fieldsToUpdate: ChallengeUpdate): Either<NetworkError, Unit>
}
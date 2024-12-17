package com.example.aplicaciondesafiospersonales.challenges.domain.repository

import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge

interface ChallengesRepository {
    suspend fun getChallenges(): ApiResult
    suspend fun addChallenge(challenge: Challenge)
}
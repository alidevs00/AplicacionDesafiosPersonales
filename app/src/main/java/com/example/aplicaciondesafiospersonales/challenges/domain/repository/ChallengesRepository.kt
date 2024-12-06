package com.example.aplicaciondesafiospersonales.challenges.domain.repository

import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult

interface ChallengesRepository {
    suspend fun getChallenges(): ApiResult
}
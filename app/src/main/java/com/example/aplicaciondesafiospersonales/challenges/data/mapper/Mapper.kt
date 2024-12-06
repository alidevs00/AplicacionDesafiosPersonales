package com.example.aplicaciondesafiospersonales.challenges.data.mapper

import com.example.aplicaciondesafiospersonales.challenges.data.entity.ApiResultEntity
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiError
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.NetworkError
import com.parse.ParseObject
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this
    )
}

fun ApiResultEntity.toModel() =
    ApiResult(
        this.challenges.map {
            Challenge(
                title = it.title,
                category = it.category,
                amountFulfilled = it.amountFulfilled,
                amountToBeFulfilled = it.amountToBeFulfilled,
                startDate = it.startDate,
                finishDate = it.finishDate
            )
        }
    )

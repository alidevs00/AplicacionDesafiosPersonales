package com.example.aplicaciondesafiospersonales.challenges.data.mapper

import com.example.aplicaciondesafiospersonales.challenges.data.entity.ApiResultEntity
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiError
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.NetworkError
import com.example.aplicaciondesafiospersonales.util.Constant
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
                id = it.objectId,
                title = capitalizeFirstLetter(it.title),
                category = it.category,
                amountFulfilled = it.amountFulfilled,
                amountToBeFulfilled = it.amountToBeFulfilled,
                startDate = it.startDate,
                finishDate = it.finishDate,
            )
        }
    )

fun capitalizeFirstLetter(input: String): String {
    return input.replaceFirstChar { it.uppercase() }
}

private val languageMap = mapOf(
    "Reading" to "Lectura",
    "Exercise" to "Ejercicio",
    "Study" to "Estudio",
    "Mental" to "Mental",
    "Personal" to "Personal",
    "Social" to "Social",
    "Work" to "Trabajo",
    "Cooking" to "Cocina"
)

fun Challenge.mapToSpanish(): Challenge =
    Challenge(
        title = title,
        category = languageMap[category] ?: category,
        amountToBeFulfilled = amountToBeFulfilled,
        amountFulfilled = Constant.INT_ZERO,
        startDate = startDate,
        finishDate = finishDate
    )

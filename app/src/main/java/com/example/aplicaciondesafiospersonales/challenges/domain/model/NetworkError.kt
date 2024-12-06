package com.example.aplicaciondesafiospersonales.challenges.domain.model

data class NetworkError (
    val error: ApiError,
    val t: Throwable? = null
)

enum class ApiError(val message: String) {
    NetworkError("Network Error"),
    UnknownError("NUnknown Error")
}
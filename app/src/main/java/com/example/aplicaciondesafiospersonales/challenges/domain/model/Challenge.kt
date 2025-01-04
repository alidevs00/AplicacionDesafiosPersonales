package com.example.aplicaciondesafiospersonales.challenges.domain.model

data class ApiResult(
    val challenges: List<Challenge>
)

data class Challenge(
    val id: String = "",
    val title: String,
    val category: String,
    val amountToBeFulfilled: Int,
    var amountFulfilled: Int,
    val startDate: String,
    val finishDate: String,
    var progress: Float = 0f
)
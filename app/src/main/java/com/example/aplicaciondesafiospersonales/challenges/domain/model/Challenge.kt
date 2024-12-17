package com.example.aplicaciondesafiospersonales.challenges.domain.model

data class ApiResult(
    val challenges: List<Challenge>
)

data class Challenge (
    val title : String,
    val category : String,
    val amountToBeFulfilled : String,
    val amountFulfilled : String,
    val startDate : String,
    val finishDate : String,
    val progress : Float = 0f
)
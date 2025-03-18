package com.example.aplicaciondesafiospersonales.challenges.domain.model

import com.example.aplicaciondesafiospersonales.util.Constant

data class ApiResult(
    val challenges: List<Challenge>
)

data class Challenge(
    val id: String = Constant.EMPTY_STRING,
    val title: String,
    val category: String,
    val amountToBeFulfilled: Int,
    var amountFulfilled: Int,
    val startDate: String,
    val finishDate: String,
    var progress: Float = Constant.FLOAT_ZERO,
    var challengeProgress: Float = Constant.FLOAT_ZERO,
    var daysUntilFinishDate: Int = Constant.INT_ZERO
)
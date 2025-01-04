package com.example.aplicaciondesafiospersonales.challenges.data.entity

import com.google.gson.annotations.SerializedName

data class ApiResultEntity (
    @SerializedName("results") val challenges : List<ChallengeEntity>
)

data class ChallengeEntity (
    @SerializedName("objectId") val objectId : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("updatedAt") val updatedAt : String,
    @SerializedName("title") val title : String,
    @SerializedName("category") val category : String,
    @SerializedName("amountToBeFulfilled") val amountToBeFulfilled : Int,
    @SerializedName("amountFulfilled") val amountFulfilled : Int,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("finishDate") val finishDate : String
)
package com.example.aplicaciondesafiospersonales.challenges.data.repository

import com.example.aplicaciondesafiospersonales.challenges.data.mapper.toModel
import com.example.aplicaciondesafiospersonales.challenges.data.remote.ChallengesApi
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiResult
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import javax.inject.Inject

class ChallengesRepositoryImpl @Inject constructor(
    private val challengesApi: ChallengesApi,
) : ChallengesRepository {

    override suspend fun getChallenges(): ApiResult =
        challengesApi.challengesApi().toModel()
        /*
        val list: MutableList<Challenge> = mutableListOf()
        val query = ParseQuery.getQuery<ParseObject>("Desafio")
        query.findInBackground { results: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (result in results) {
                    list.add(
                        Challenge(
                            title = result.objectId,
                            category = "",
                            amountFulfilled = 9,
                            amountToBeFulfilled = 0,
                            startDate = "",
                            finishDate = ""
                        )
                    )
                }
            }
        }
        return ApiResult(list)*/

}
package jp.speakbuddy.edisonandroidexercise.network.api

import jp.speakbuddy.edisonandroidexercise.network.entities.FactResponse
import retrofit2.http.GET

interface FactService {
    @GET("fact")
    suspend fun getFact(): FactResponse
}

package jp.speakbuddy.edisonandroidexercise.network

import jp.speakbuddy.edisonandroidexercise.network.api.FactService
import jp.speakbuddy.edisonandroidexercise.network.entities.FactResponse
import javax.inject.Inject
import javax.inject.Singleton

interface FactRemoteDataSource {
    suspend fun getFact(): FactResponse
}

@Singleton
internal class FactNetworkDataSource @Inject constructor(
    private val service: FactService
) : FactRemoteDataSource {
    override suspend fun getFact(): FactResponse = service.getFact()
}

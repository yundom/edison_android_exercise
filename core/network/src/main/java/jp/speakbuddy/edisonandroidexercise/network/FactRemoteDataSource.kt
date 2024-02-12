package jp.speakbuddy.edisonandroidexercise.network

import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.network.api.FactService
import jp.speakbuddy.edisonandroidexercise.network.mapper.toFact
import javax.inject.Inject
import javax.inject.Singleton

interface FactRemoteDataSource {
    suspend fun getFact(): Fact
}

@Singleton
internal class FactNetworkDataSource @Inject constructor(
    private val service: FactService,
) : FactRemoteDataSource {
    override suspend fun getFact(): Fact = service.getFact().toFact()
}

package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.network.FactRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteFactRepository @Inject constructor(
    private val dataSource: FactRemoteDataSource,
) : FactRepository {
    override fun getFact(): Flow<Fact> = flow {
        emit(dataSource.getFact())
    }.catch { emit(Fact()) }
}

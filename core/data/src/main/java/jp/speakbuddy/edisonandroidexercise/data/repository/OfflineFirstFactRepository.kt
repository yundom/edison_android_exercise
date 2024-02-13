package jp.speakbuddy.edisonandroidexercise.data.repository

import android.util.Log
import jp.speakbuddy.edisonandroidexercise.datastore.FactLocalDataSource
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.network.FactRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineFirstFactRepository @Inject constructor(
    private val remoteDataSource: FactRemoteDataSource,
    private val localDataSource: FactLocalDataSource,
) : FactRepository {
    override fun getFact(): Flow<Fact> = flow {
        val remoteData = remoteDataSource.getFact()
        if (remoteData.isValid()) {
            localDataSource.setFact(remoteData)
            emit(remoteData)
        }
    }.catch { error ->
        val localData = localDataSource.getFact()

        if (localData.isValid()) {
            emit(localData)
        } else {
            val msg = "Can't load facts."
            emit(Fact(msg, msg.length))
        }

        Log.e("FactApp", error.message ?: "Unknown error occurred")
    }

    private fun Fact.isValid(): Boolean = this.length > 0
}

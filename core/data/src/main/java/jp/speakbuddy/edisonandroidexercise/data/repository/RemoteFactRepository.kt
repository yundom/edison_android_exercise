package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.domain.model.FactEntry
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import kotlinx.coroutines.flow.Flow

class RemoteFactRepository : FactRepository {
    override fun getFact(): Flow<FactEntry> {
        TODO("Not yet implemented")
    }
}

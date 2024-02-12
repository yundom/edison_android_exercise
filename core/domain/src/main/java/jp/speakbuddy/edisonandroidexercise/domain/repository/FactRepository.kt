package jp.speakbuddy.edisonandroidexercise.domain.repository

import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    fun getFact(): Flow<Fact>
}

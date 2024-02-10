package jp.speakbuddy.edisonandroidexercise.domain.repository

import jp.speakbuddy.edisonandroidexercise.domain.model.FactEntry
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    fun getFact(): Flow<FactEntry>
}

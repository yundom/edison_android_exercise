package jp.speakbuddy.edisonandroidexercise.datastore

import androidx.datastore.core.DataStore
import jp.speakbuddy.edisonandroidexercise.datastore.FactEntryOuterClass.FactEntry
import jp.speakbuddy.edisonandroidexercise.datastore.mapper.toFact
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

interface FactLocalDataSource {
    suspend fun getFact(): Fact
    suspend fun setFact(fact: Fact)
}

@Singleton
internal class FactDataStoreLocalDataSource @Inject constructor(
    private val factDataStore: DataStore<FactEntry>,
) : FactLocalDataSource {
    override suspend fun getFact(): Fact = factDataStore.data.first().toFact()
    override suspend fun setFact(fact: Fact) {
        factDataStore.updateData {
            it.toBuilder()
                .setFact(fact.fact)
                .setLength(fact.length)
                .build()
        }
    }
}

package jp.speakbuddy.edisonandroidexercise.datastore

import androidx.datastore.core.DataStore
import jp.speakbuddy.edisonandroidexercise.datastore.FactEntryOuterClass.FactEntry
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

interface FactLocalDataSource {
    suspend fun getFact(): FactEntry
    suspend fun setFact(factEntry: FactEntry)
}

@Singleton
internal class FactDataStoreLocalDataSource @Inject constructor(
    private val factDataStore: DataStore<FactEntry>,
) : FactLocalDataSource {
    override suspend fun getFact(): FactEntry = factDataStore.data.first()
    override suspend fun setFact(factEntry: FactEntry) {
        factDataStore.updateData {
            it.toBuilder()
                .setFact(factEntry.fact)
                .setLength(factEntry.length)
                .build()
        }
    }
}

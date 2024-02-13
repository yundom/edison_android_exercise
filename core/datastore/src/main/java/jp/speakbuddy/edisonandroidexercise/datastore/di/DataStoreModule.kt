package jp.speakbuddy.edisonandroidexercise.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.datastore.FactDataStoreLocalDataSource
import jp.speakbuddy.edisonandroidexercise.datastore.FactEntryOuterClass.FactEntry
import jp.speakbuddy.edisonandroidexercise.datastore.FactEntrySerializer
import jp.speakbuddy.edisonandroidexercise.datastore.FactLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    internal fun provideFactEntryDataStore(
        @ApplicationContext context: Context,
        serializer: FactEntrySerializer,
    ): DataStore<FactEntry> = DataStoreFactory.create(
        serializer = serializer,
        scope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    ) {
        context.dataStoreFile("fact_entry.pb")
    }

    @Provides
    @Singleton
    internal fun provideFactLocalDataSource(
        dataStore: DataStore<FactEntry>,
    ): FactLocalDataSource {
        return FactDataStoreLocalDataSource(dataStore)
    }
}

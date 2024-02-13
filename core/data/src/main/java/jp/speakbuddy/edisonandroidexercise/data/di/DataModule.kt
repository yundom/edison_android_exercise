package jp.speakbuddy.edisonandroidexercise.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.data.repository.OfflineFirstFactRepository
import jp.speakbuddy.edisonandroidexercise.datastore.FactLocalDataSource
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.network.FactRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFactRepository(
        remoteDataSource: FactRemoteDataSource,
        localDataSource: FactLocalDataSource,
    ): FactRepository = OfflineFirstFactRepository(remoteDataSource, localDataSource)
}

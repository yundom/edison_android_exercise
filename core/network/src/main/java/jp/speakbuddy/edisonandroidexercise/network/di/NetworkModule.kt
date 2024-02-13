package jp.speakbuddy.edisonandroidexercise.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.FactRemoteDataSource
import jp.speakbuddy.edisonandroidexercise.network.api.FactService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideFactService(
        json: Json,
    ): FactService = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(FactService::class.java)

    @Provides
    @Singleton
    fun provideFactDataSource(
        service: FactService,
    ): FactRemoteDataSource = FactNetworkDataSource(service)
}

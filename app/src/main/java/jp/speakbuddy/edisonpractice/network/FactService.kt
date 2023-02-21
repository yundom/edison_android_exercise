package jp.speakbuddy.edisonpractice.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface FactService {

    @GET("/fact")
    suspend fun getFact(): FactResponse
}

data class FactResponse(
    val fact: String,
    val length: Int
)

object RetrofitProvider {
    fun provide(): FactService =
        Retrofit.Builder()
            .baseUrl("https://example.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FactService::class.java)
}

package jp.speakbuddy.edisonandroidexercise.network.fake

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.network.FactRemoteDataSource
import jp.speakbuddy.edisonandroidexercise.network.entities.FactResponse
import jp.speakbuddy.edisonandroidexercise.network.mapper.toFact
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okio.use
import java.io.InputStream

@OptIn(ExperimentalSerializationApi::class)
class FakeFactRemoteDataSource(
    private val json: Json,
    private val asset: FakeAssetManager,
) : FactRemoteDataSource {
    override suspend fun getFact(): Fact = readFromAsset().toFact()

    private fun readFromAsset(): FactResponse = asset.open("fact.json").use(json::decodeFromStream)
}

fun interface FakeAssetManager {
    fun open(fileName: String): InputStream
}

internal object JvmUnitTestFakeAssetManager : FakeAssetManager {
    override fun open(fileName: String): InputStream {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return context.assets.open(fileName)
    }
}

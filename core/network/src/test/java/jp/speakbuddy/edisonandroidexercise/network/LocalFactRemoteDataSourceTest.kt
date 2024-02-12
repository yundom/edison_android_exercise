package jp.speakbuddy.edisonandroidexercise.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.network.di.NetworkModule
import jp.speakbuddy.edisonandroidexercise.network.fake.FakeFactRemoteDataSource
import jp.speakbuddy.edisonandroidexercise.network.fake.JvmUnitTestFakeAssetManager
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@HiltAndroidTest
@UninstallModules(NetworkModule::class)
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
class LocalFactRemoteDataSourceTest {

    @Module
    @InstallIn(SingletonComponent::class)
    object FakeNetworkModule {

        @Provides
        @Singleton
        fun provideFactDataSource(): FactRemoteDataSource {
            return FakeFactRemoteDataSource(
                Json { ignoreUnknownKeys = true },
                JvmUnitTestFakeAssetManager
            )
        }
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dataSource: FactRemoteDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `Test deserialization of Fact`() = runTest {
        val result = dataSource.getFact()

        assertEquals("Cats see six times better in the dark and at night than humans.", result.fact)
        assertEquals(63, result.length)
    }
}

package jp.speakbuddy.edisonandroidexercise.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertNotNull

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
class NetworkFactRemoteDataSourceTest {

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
        var result = dataSource.getFact()

        assertNotNull(result.fact)
        assertNotNull(result.length)
    }
}

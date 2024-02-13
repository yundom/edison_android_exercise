package jp.speakbuddy.edisonandroidexercise.datastore

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertEquals

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
class FactLocalDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dataSource: FactLocalDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `Test write and read from datastore`() = runTest {
        val fact = Fact("Fact", 4)

        dataSource.setFact(fact)
        val result = dataSource.getFact()

        assertEquals(fact, result)
    }
}

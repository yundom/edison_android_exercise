package jp.speakbuddy.edisonandroidexercise

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.data.di.DataModule
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.fact.FactScreenTestTags
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(DataModule::class)
@RunWith(AndroidJUnit4::class)
class FactScreenTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Module
    @InstallIn(SingletonComponent::class)
    object FakeDataModule {
        @Provides
        @Singleton
        fun provideFactRepository(): FactRepository = object : FactRepository {
            override fun getFact(): Flow<Fact> {
                val msg = "I love cats."
                return flowOf(Fact(msg, msg.length))
            }
        }
    }

    @Test
    fun testFactScreen() {
        with(composeTestRule) {
            waitUntil(10_000) {
                onNodeWithTag(FactScreenTestTags.SUCCESS_STATE).isDisplayed()
            }
            onNodeWithTag(FactScreenTestTags.CONTENT).assert(hasText("I love cats."))
            onNodeWithTag(FactScreenTestTags.CONTENT_LENGTH).assert(hasText("Length: 12"))
            onNodeWithTag(FactScreenTestTags.MULTIPLE_CATS).isDisplayed()
        }
    }
}

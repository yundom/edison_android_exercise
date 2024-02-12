package jp.speakbuddy.edisonandroidexercise.datastore

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
class FactLocalDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var dataSource: FactLocalDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `Test write and read from datastore`() {


    }
}

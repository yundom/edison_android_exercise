package jp.speakbuddy.edisonandroidexercise.domain

import io.mockk.coEvery
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GetFactUseCaseTest {

    private lateinit var factRepository: FactRepository

    private lateinit var getFactUseCase: GetFactUseCase

    @Before
    fun setUp() {
        factRepository = mockk()

        getFactUseCase = GetFactUseCase(factRepository)
    }

    @Test
    fun `Test invoke returns manyCats is true when fact contains 'cats'`() =
        runTest {
            val msg = "I love cats."
            val fact = Fact(msg, msg.length)
            coEvery { factRepository.getFact() } returns flowOf(fact)

            val result = getFactUseCase().first()

            assertEquals(fact, result.fact)
            assertTrue(result.manyCats)
        }

    @Test
    fun `Test invoke returns manyCats is false when fact does not contain 'cats'`() =
        runTest {
            val msg = "I love dogs."
            val fact = Fact(msg, msg.length)
            coEvery { factRepository.getFact() } returns flowOf(fact)

            val result = getFactUseCase().first()

            assertEquals(fact, result.fact)
            assertFalse(result.manyCats)
        }
}

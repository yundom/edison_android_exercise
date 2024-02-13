package jp.speakbuddy.edisonandroidexercise.ui

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.domain.GetFactUseCase
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.domain.model.FactWithCats
import jp.speakbuddy.edisonandroidexercise.testing.MainDispatcherRule
import jp.speakbuddy.edisonandroidexercise.ui.common.UiState
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class FactViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetFactUseCase

    private lateinit var viewModel: FactViewModel

    @Before
    fun setUp() {
        useCase = mockk()
    }

    @Test
    fun `Test fetchFact emits UiState Success when useCase returns fact`() = runTest {
        val factWithCats = FactWithCats(Fact("I love cats"), true)
        coEvery { useCase() } returns flowOf(factWithCats)

        viewModel = FactViewModel(useCase)
        viewModel.fetchFact()

        val state = viewModel.factUiState.value
        assertTrue(state is UiState.Success)
        assertEquals(factWithCats, state.data)
        coVerify(exactly = 2) { useCase.invoke() }
    }

    @Test
    fun `Test init emits UiState Success when useCase returns fact`() = runTest {
        val factWithCats = FactWithCats(Fact("I love cats"), true)
        coEvery { useCase() } returns flowOf(factWithCats)

        viewModel = FactViewModel(useCase)

        val state = viewModel.factUiState.value
        assertTrue(state is UiState.Success)
        assertEquals(factWithCats, state.data)
        coVerify(exactly = 1) { useCase.invoke() }
    }
}

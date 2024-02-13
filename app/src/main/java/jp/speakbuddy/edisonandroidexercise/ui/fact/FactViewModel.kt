package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.domain.GetFactUseCase
import jp.speakbuddy.edisonandroidexercise.domain.model.FactWithCats
import jp.speakbuddy.edisonandroidexercise.ui.common.UiState
import jp.speakbuddy.edisonandroidexercise.ui.common.asUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val useCase: GetFactUseCase,
) : ViewModel() {
    private val _factUiState = MutableStateFlow<UiState<FactWithCats>>(UiState.Loading)
    val factUiState: StateFlow<UiState<FactWithCats>> = _factUiState.asStateFlow()

    init {
        fetchFact()
    }

    fun fetchFact() {
        viewModelScope.launch {
            useCase().asUiState().collect {
                _factUiState.value = it
            }
        }
    }
}

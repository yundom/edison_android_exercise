package jp.speakbuddy.edisonandroidexercise.fact.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val exception: Throwable) : UiState<Nothing>()
}

fun <T> Flow<T>.asUiState(): Flow<UiState<T>> = map<T, UiState<T>> {
    UiState.Success(
        it
    )
}
    .onStart { UiState.Loading }
    .catch { UiState.Error(it) }

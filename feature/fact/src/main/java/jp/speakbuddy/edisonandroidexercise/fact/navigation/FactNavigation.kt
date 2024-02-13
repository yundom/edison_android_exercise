package jp.speakbuddy.edisonandroidexercise.fact.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.speakbuddy.edisonandroidexercise.fact.FactScreen
import jp.speakbuddy.edisonandroidexercise.fact.FactViewModel
import jp.speakbuddy.edisonandroidexercise.fact.R

const val FACT_ROUTE = "fact_route"

fun NavGraphBuilder.factScreen(onRouteChange: (String) -> Unit = {}) {
    composable(route = FACT_ROUTE) {
        FactRoute()
        onRouteChange(stringResource(id = R.string.screen_name))
    }
}

@Composable
fun FactRoute(
    viewModel: FactViewModel = hiltViewModel(),
) {
    val uiState by viewModel.factUiState.collectAsStateWithLifecycle()
    FactScreen(
        uiState = uiState,
        onClick = { viewModel.fetchFact() }
    )
}

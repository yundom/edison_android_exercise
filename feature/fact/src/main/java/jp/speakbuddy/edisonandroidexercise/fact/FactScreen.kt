package jp.speakbuddy.edisonandroidexercise.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.domain.model.FactWithCats
import jp.speakbuddy.edisonandroidexercise.fact.common.UiState

@Composable
fun FactScreen(
    stateProvider: () -> UiState<FactWithCats>,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        when (val uiState = stateProvider()) {
            UiState.Loading -> {
                Loading(stringResource(id = R.string.loading))
            }

            is UiState.Error -> {
                ErrorDetail(
                    uiState.exception.message ?: stringResource(id = R.string.error_unknown)
                )
            }

            is UiState.Success -> {
                FactDetail(
                    fact = uiState.data.fact.fact,
                    length = uiState.data.fact.length,
                    multipleCats = uiState.data.manyCats
                )
            }
        }

        Button(onClick = onClick) {
            Text(text = stringResource(id = R.string.button_update))
        }
    }
}

@Composable
fun Loading(hint: String) {
    Text(text = hint, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun ErrorDetail(message: String) {
    Text(text = message, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun FactDetail(
    modifier: Modifier = Modifier,
    fact: String,
    length: Int,
    multipleCats: Boolean = false,
) {
    Column(
        modifier = modifier.testTag(FactScreenTestTags.SUCCESS_STATE),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.titleLarge
        )

        if (multipleCats) {
            Text(
                modifier = Modifier.testTag(FactScreenTestTags.MULTIPLE_CATS),
                text = stringResource(id = R.string.cats),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Text(
            modifier = Modifier.testTag(FactScreenTestTags.CONTENT),
            text = fact,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier
                .testTag(FactScreenTestTags.CONTENT_LENGTH)
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            text = stringResource(id = R.string.length, length),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

object FactScreenTestTags {
    const val SUCCESS_STATE = "fact_screen_success_state"
    const val MULTIPLE_CATS = "fact_screen_multiple_cats"
    const val CONTENT = "fact_screen_content"
    const val CONTENT_LENGTH = "fact_screen_content_length"
}

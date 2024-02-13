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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.domain.model.FactWithCats
import jp.speakbuddy.edisonandroidexercise.fact.common.UiState

@Composable
fun FactScreen(
    uiState: UiState<FactWithCats>,
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
        when (uiState) {
            UiState.Loading -> {
                Loading()
            }

            is UiState.Error -> {
                ErrorDetail(uiState.exception.message ?: "Unknown error")
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
            Text(text = "Update fact")
        }
    }
}

@Composable
fun Loading() {
    Text(text = "Loading", style = MaterialTheme.typography.titleLarge)
}

@Composable
fun ErrorDetail(message: String) {
    Text(text = message, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun FactDetail(fact: String, length: Int, multipleCats: Boolean = false) {
    Text(
        text = "Fact",
        style = MaterialTheme.typography.titleLarge
    )

    if (multipleCats) {
        Text(
            text = "Multiple Cats!!",
            style = MaterialTheme.typography.titleMedium
        )
    }

    Text(
        text = fact,
        style = MaterialTheme.typography.bodyLarge
    )

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        text = "Length: $length",
        style = MaterialTheme.typography.titleMedium
    )
}

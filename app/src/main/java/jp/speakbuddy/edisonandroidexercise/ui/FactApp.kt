package jp.speakbuddy.edisonandroidexercise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.ui.navigation.FactNavHost
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonAndroidExerciseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactApp() {
    EdisonAndroidExerciseTheme {
        val defaultName = stringResource(id = R.string.app_name)
        val currentRoute = remember { mutableStateOf(defaultName) }
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(text = currentRoute.value)
                    })
            }
        ) { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                FactNavHost(onRouteChange = {
                    currentRoute.value = it
                })
            }
        }
    }
}

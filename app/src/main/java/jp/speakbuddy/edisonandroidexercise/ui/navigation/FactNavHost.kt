package jp.speakbuddy.edisonandroidexercise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import jp.speakbuddy.edisonandroidexercise.fact.navigation.FACT_ROUTE
import jp.speakbuddy.edisonandroidexercise.fact.navigation.factScreen

@Composable
fun FactNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = FACT_ROUTE) {
        factScreen()
    }
}

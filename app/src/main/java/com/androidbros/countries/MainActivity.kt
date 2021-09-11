package com.androidbros.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.androidbros.countries.ui.theme.CountriesComposeAppTheme
import com.androidbros.countries.view.CountryDetailScreen
import com.androidbros.countries.view.CountryListScreen
import com.androidbros.countries.view.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesComposeAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("country_list_screen") {
            CountryListScreen(navController = navController)
        }
        composable(
            "country_detail_screen/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            CountryDetailScreen(
                navController = navController,
                name = backStackEntry.arguments?.getString("name")
            )
        }
    }
}
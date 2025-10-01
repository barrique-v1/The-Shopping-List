package com.example.theshoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.theshoppinglist.presentation.features.profile.ProfileScreen
import com.example.theshoppinglist.presentation.features.recipes.RecipeScreen
import com.example.theshoppinglist.presentation.features.shoppinglists.ShoppingListsScreen
import com.example.theshoppinglist.presentation.navigation.BottomNavigationBar
import com.example.theshoppinglist.presentation.navigation.Routes
import com.example.theshoppinglist.presentation.theme.TheShoppingListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheShoppingListTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.LISTEN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.LISTEN) {
                ShoppingListsScreen()
            }
            composable(Routes.REZEPTE) {
                RecipeScreen()
            }
            composable(Routes.PROFIL) {
                ProfileScreen()
            }
        }
    }
}
package com.example.theshoppinglist

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
import com.example.theshoppinglist.navigation.BottomNavigationBar
import com.example.theshoppinglist.navigation.Routes
import com.example.theshoppinglist.screens.ListenScreen
import com.example.theshoppinglist.screens.ProfilScreen
import com.example.theshoppinglist.screens.RezepteScreen
import com.example.theshoppinglist.ui.theme.TheShoppingListTheme

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
                ListenScreen()
            }
            composable(Routes.REZEPTE) {
                RezepteScreen()
            }
            composable(Routes.PROFIL) {
                ProfilScreen()
            }
        }
    }
}
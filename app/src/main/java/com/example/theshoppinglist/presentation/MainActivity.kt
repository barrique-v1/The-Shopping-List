package com.example.theshoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.theshoppinglist.presentation.features.profile.ProfileScreen
import com.example.theshoppinglist.presentation.features.recipes.RecipeScreen
import com.example.theshoppinglist.presentation.features.shoppinglists.listdetail.ListDetailScreen
import com.example.theshoppinglist.presentation.features.shoppinglists.ShoppingListsScreen
import com.example.theshoppinglist.presentation.navigation.BottomNavigationBar
import com.example.theshoppinglist.presentation.navigation.FabConfig
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
    var fabConfig by remember { mutableStateOf<FabConfig?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        floatingActionButton = {
            fabConfig?.let { config ->
                FloatingActionButton(onClick = config.onClick) {
                    Icon(
                        imageVector = config.icon,
                        contentDescription = config.contentDescription
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.LISTEN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.LISTEN) {
                ShoppingListsScreen(
                    onNavigateToDetail = { listId ->
                        navController.navigate("list_detail/$listId")
                    },
                    onFabConfigChange = { config ->
                        fabConfig = config
                    }
                )
            }
            composable(
                route = "list_detail/{listId}",
                arguments = listOf(
                    navArgument("listId") { type = NavType.LongType }
                )
            ) {
                ListDetailScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onFabConfigChange = { config ->
                        fabConfig = config
                    }
                )
            }
            composable(Routes.REZEPTE) {
                RecipeScreen(
                    onFabConfigChange = { config ->
                        fabConfig = config
                    }
                )
            }
            composable(Routes.PROFIL) {
                ProfileScreen(
                    onFabConfigChange = { config ->
                        fabConfig = config
                    }
                )
            }
        }
    }
}
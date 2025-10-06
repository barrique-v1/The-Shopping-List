package com.example.theshoppinglist.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.PlaylistAddCheck
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.PlaylistAddCheck
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

// Navigation routes
object Routes {
    const val LISTEN = "listen"
    const val REZEPTE = "rezepte"
    const val PROFIL = "profil"
}

// Bottom navigation item data class
data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
)

// List of navigation items
val bottomNavItems = listOf(
    BottomNavItem(
        route = Routes.LISTEN,
        title = "Listen",
        icon = Icons.AutoMirrored.Outlined.PlaylistAddCheck,
        selectedIcon = Icons.AutoMirrored.Filled.PlaylistAddCheck
    ),
    BottomNavItem(
        route = Routes.REZEPTE,
        title = "Rezepte",
        icon = Icons.AutoMirrored.Outlined.MenuBook,
        selectedIcon = Icons.AutoMirrored.Filled.MenuBook
    ),
    BottomNavItem(
        route = Routes.PROFIL,
        title = "Profil",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person
    )
)

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Pop up to the start destination to avoid building a large stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when re-selecting
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.selectedIcon else item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}
package com.example.theshoppinglist.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Configuration for a floating action button.
 * Screens can provide this configuration to show a FAB in the main scaffold.
 *
 * @param icon The icon to display in the FAB
 * @param contentDescription Accessibility description for the FAB
 * @param onClick Callback when the FAB is clicked
 */
data class FabConfig(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit
)

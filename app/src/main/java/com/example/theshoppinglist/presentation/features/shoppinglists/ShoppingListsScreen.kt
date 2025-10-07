package com.example.theshoppinglist.presentation.features.shoppinglists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.theshoppinglist.presentation.common.UiEvent
import com.example.theshoppinglist.presentation.common.UiState
import com.example.theshoppinglist.presentation.features.shoppinglists.components.CreateListDialog
import com.example.theshoppinglist.presentation.features.shoppinglists.components.ShoppingListCard
import com.example.theshoppinglist.presentation.navigation.FabConfig

/**
 * Screen displaying all shopping lists.
 * Shows loading state, error state, or list of shopping lists with FAB to create new ones.
 */
@Composable
fun ShoppingListsScreen(
    viewModel: ShoppingListsViewModel = hiltViewModel(),
    onNavigateToDetail: (Long) -> Unit = {},
    onFabConfigChange: (FabConfig?) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCreateDialog by remember { mutableStateOf(false) }

    // Configure FAB for this screen
    LaunchedEffect(key1 = Unit) {
        onFabConfigChange(
            FabConfig(
                icon = Icons.Default.Add,
                contentDescription = "Neue Liste erstellen",
                onClick = { showCreateDialog = true }
            )
        )
    }

    // Handle one-time events
    LaunchedEffect(key1 = viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is UiEvent.Navigate -> {
                    // Extract listId from route "list_detail/{listId}"
                    val listId = event.route.substringAfterLast("/").toLongOrNull()
                    listId?.let { onNavigateToDetail(it) }
                }
                else -> { /* Handle other events if needed */ }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Snackbar host positioned at top
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // Main content
        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = uiState) {
                is UiState.Idle -> {
                    // Initial state - could show placeholder
                }
                is UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is UiState.Success -> {
                    if (state.data.lists.isEmpty()) {
                        // Empty state
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Keine Einkaufslisten vorhanden",
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Tippen Sie auf + um eine neue Liste zu erstellen",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                items = state.data.lists,
                                key = { it.id }
                            ) { shoppingList ->
                                ShoppingListCard(
                                    shoppingList = shoppingList,
                                    onClick = { viewModel.onListClicked(shoppingList.id) }
                                )
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Fehler beim Laden",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }

    // Show create dialog
    if (showCreateDialog) {
        CreateListDialog(
            onDismiss = { showCreateDialog = false },
            onConfirm = { name ->
                viewModel.createShoppingList(name)
            }
        )
    }
}

package com.example.theshoppinglist.presentation.features.shoppinglists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.usecase.shoppinglist.CreateShoppingListUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.DeleteShoppingListUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.GetShoppingListsUseCase
import com.example.theshoppinglist.presentation.common.UiEvent
import com.example.theshoppinglist.presentation.common.UiState
import com.example.theshoppinglist.presentation.features.shoppinglists.listdetail.ListsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the shopping lists screen.
 * Manages the state and handles user interactions for the lists overview.
 */
@HiltViewModel
class ShoppingListsViewModel @Inject constructor(
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val createShoppingListUseCase: CreateShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<ListsUiState>>(UiState.Idle)
    val uiState: StateFlow<UiState<ListsUiState>> = _uiState.asStateFlow()

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadShoppingLists()
    }

    /**
     * Loads all shopping lists and observes changes.
     * Automatically updates UI when lists change in the database.
     */
    fun loadShoppingLists() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            getShoppingListsUseCase().collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> UiState.Success(ListsUiState(lists = result.data))
                    is Result.Error -> UiState.Error(
                        message = result.exception.message ?: "Failed to load shopping lists"
                    )
                    is Result.Loading -> UiState.Loading
                }
            }
        }
    }

    /**
     * Creates a new shopping list.
     * @param name The name of the new shopping list
     */
    fun createShoppingList(name: String) {
        viewModelScope.launch {
            when (val result = createShoppingListUseCase(name)) {
                is Result.Success -> {
                    _events.send(UiEvent.ShowSnackbar("Shopping list created"))
                }
                is Result.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(
                            result.exception.message ?: "Failed to create shopping list"
                        )
                    )
                }
                is Result.Loading -> { /* Not applicable for suspend functions */ }
            }
        }
    }

    /**
     * Deletes a shopping list.
     * @param id The ID of the shopping list to delete
     */
    fun deleteShoppingList(id: Long) {
        viewModelScope.launch {
            when (val result = deleteShoppingListUseCase(id)) {
                is Result.Success -> {
                    _events.send(UiEvent.ShowSnackbar("Shopping list deleted"))
                }
                is Result.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(
                            result.exception.message ?: "Failed to delete shopping list"
                        )
                    )
                }
                is Result.Loading -> { /* Not applicable for suspend functions */ }
            }
        }
    }

    /**
     * Handles navigation to the list detail screen.
     * @param listId The ID of the shopping list to view
     */
    fun onListClicked(listId: Long) {
        viewModelScope.launch {
            _events.send(UiEvent.Navigate("list_detail/$listId"))
        }
    }
}

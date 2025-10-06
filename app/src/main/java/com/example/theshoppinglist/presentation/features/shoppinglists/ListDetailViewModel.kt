package com.example.theshoppinglist.presentation.features.shoppinglists

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.Unit
import com.example.theshoppinglist.domain.usecase.shoppinglist.AddListItemUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.DeleteListItemUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.GetListItemsUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.GetShoppingListByIdUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.ToggleListItemCheckedUseCase
import com.example.theshoppinglist.domain.usecase.shoppinglist.UpdateListItemUseCase
import com.example.theshoppinglist.presentation.common.UiEvent
import com.example.theshoppinglist.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the list detail screen.
 * Manages the state and handles user interactions for a single shopping list.
 *
 * @param savedStateHandle Provides access to navigation arguments (listId)
 */
@HiltViewModel
class ListDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getShoppingListByIdUseCase: GetShoppingListByIdUseCase,
    private val getListItemsUseCase: GetListItemsUseCase,
    private val addListItemUseCase: AddListItemUseCase,
    private val updateListItemUseCase: UpdateListItemUseCase,
    private val deleteListItemUseCase: DeleteListItemUseCase,
    private val toggleListItemCheckedUseCase: ToggleListItemCheckedUseCase
) : ViewModel() {

    private val listId: Long = checkNotNull(savedStateHandle.get<Long>("listId")) {
        "listId parameter is required"
    }

    private val _uiState = MutableStateFlow<UiState<ListDetailUiState>>(UiState.Idle)
    val uiState: StateFlow<UiState<ListDetailUiState>> = _uiState.asStateFlow()

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadListDetails()
    }

    /**
     * Loads the shopping list and its items.
     * Combines both flows to create a complete UI state.
     */
    private fun loadListDetails() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            combine(
                getShoppingListByIdUseCase(listId),
                getListItemsUseCase(listId)
            ) { listResult, itemsResult ->
                when {
                    listResult is Result.Success && itemsResult is Result.Success -> {
                        UiState.Success(
                            ListDetailUiState(
                                shoppingList = listResult.data,
                                items = itemsResult.data
                            )
                        )
                    }
                    listResult is Result.Error -> {
                        UiState.Error(
                            message = listResult.exception.message ?: "Failed to load shopping list"
                        )
                    }
                    itemsResult is Result.Error -> {
                        UiState.Error(
                            message = itemsResult.exception.message ?: "Failed to load items"
                        )
                    }
                    else -> UiState.Loading
                }
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    /**
     * Adds a new item to the shopping list.
     * @param name The name of the item
     * @param quantity The quantity
     * @param unit The unit of measurement
     * @param category The category
     */
    fun addItem(
        name: String,
        quantity: Double,
        unit: Unit,
        category: Category
    ) {
        viewModelScope.launch {
            when (val result = addListItemUseCase(listId, name, quantity, unit, category)) {
                is Result.Success -> {
                    _events.send(UiEvent.ShowSnackbar("Item added"))
                }
                is Result.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(
                            result.exception.message ?: "Failed to add item"
                        )
                    )
                }
                is Result.Loading -> { /* Not applicable */ }
            }
        }
    }

    /**
     * Updates an existing list item.
     * @param id The ID of the item to update
     * @param name The updated name
     * @param quantity The updated quantity
     * @param unit The updated unit
     * @param category The updated category
     * @param isChecked The checked state
     */
    fun updateItem(
        id: Long,
        name: String,
        quantity: Double,
        unit: Unit,
        category: Category,
        isChecked: Boolean = false
    ) {
        viewModelScope.launch {
            when (val result = updateListItemUseCase(
                id = id,
                shoppingListId = listId,
                name = name,
                quantity = quantity,
                unit = unit,
                category = category,
                isChecked = isChecked
            )) {
                is Result.Success -> {
                    _events.send(UiEvent.ShowSnackbar("Item updated"))
                }
                is Result.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(
                            result.exception.message ?: "Failed to update item"
                        )
                    )
                }
                is Result.Loading -> { /* Not applicable */ }
            }
        }
    }

    /**
     * Deletes a list item.
     * @param id The ID of the item to delete
     */
    fun deleteItem(id: Long) {
        viewModelScope.launch {
            when (val result = deleteListItemUseCase(id)) {
                is Result.Success -> {
                    _events.send(UiEvent.ShowSnackbar("Item deleted"))
                }
                is Result.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(
                            result.exception.message ?: "Failed to delete item"
                        )
                    )
                }
                is Result.Loading -> { /* Not applicable */ }
            }
        }
    }

    /**
     * Toggles the checked state of an item.
     * This is the most frequent user interaction.
     * @param id The ID of the item
     * @param isChecked The new checked state
     */
    fun toggleItemChecked(id: Long, isChecked: Boolean) {
        viewModelScope.launch {
            when (val result = toggleListItemCheckedUseCase(id, isChecked)) {
                is Result.Success -> {
                    // No snackbar for frequent actions like checkbox toggle
                }
                is Result.Error -> {
                    _events.send(
                        UiEvent.ShowSnackbar(
                            result.exception.message ?: "Failed to update item"
                        )
                    )
                }
                is Result.Loading -> { /* Not applicable */ }
            }
        }
    }
}

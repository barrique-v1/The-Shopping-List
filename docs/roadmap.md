# Development Roadmap

## Phase 1: Foundation & Data Layer ✅

### 1.1 Database Setup
- [x] Add Room, KSP dependencies to gradle
- [x] Create domain models (ShoppingList, ListItem, Recipe, Ingredient)
- [x] Create enums (Category, Unit) with display names
- [x] Create Room entities with proper relationships
- [x] Create TypeConverters for LocalDateTime and enums
- [x] Create mapper extension functions (Entity ↔ Domain)
- [x] Create DAOs (ShoppingListDao, RecipeDao, ListItemDao, IngredientDao)
- [x] Set up AppDatabase with migrations strategy
- [x] Configure schema export location
- [x] Create DatabaseModule for Hilt DI

### 1.2 Repository Layer
- [x] Create Result wrapper class for error handling (Success/Error/Loading states)
- [x] Create ShoppingListRepository interface in domain layer
- [x] Create RecipeRepository interface in domain layer
- [x] Implement ShoppingListRepositoryImpl with error handling
- [x] Implement RecipeRepositoryImpl with error handling
- [x] Extend ShoppingListRepository with ListItem operations (aggregate pattern)
- [x] Extend RecipeRepository with Ingredient operations (aggregate pattern)
- [x] Add RepositoryModule for Hilt DI with @Binds

### 1.3 Common Utilities
- [x] Create base UI state classes (UiState with Idle/Loading/Success/Error)
- [x] Create sealed classes for UI events (UiEvent with navigation/snackbar/toast)
- [x] Add extension functions for UiState (isLoading, isSuccess, dataOrNull, etc.)
- Note: Extensions and utils packages will be added incrementally as needed

## Phase 2: Shopping Lists Feature 📝

### 2.1 Use Cases ✅
- [x] GetShoppingListsUseCase - fetch all shopping lists (Flow)
- [x] CreateShoppingListUseCase - create new list with validation
- [x] DeleteShoppingListUseCase - delete list by ID
- [x] GetShoppingListByIdUseCase - fetch single list for detail screen
- [x] GetListItemsUseCase - fetch items for a shopping list (Flow)
- [x] AddListItemUseCase - add item with validation (name, quantity)
- [x] UpdateListItemUseCase - update existing item with validation
- [x] DeleteListItemUseCase - delete item by ID
- [x] ToggleListItemCheckedUseCase - toggle checked state

### 2.2 ViewModels & State ✅
- [x] Define ListsUiState data class - holds list of shopping lists
- [x] Create ShoppingListsViewModel with StateFlow and events Channel
  - loadShoppingLists() - observes all lists reactively
  - createShoppingList(name) - creates new list with validation
  - deleteShoppingList(id) - deletes list
  - onListClicked(listId) - navigation to detail screen
- [x] Define ListDetailUiState data class - holds shopping list and items
- [x] Create ListDetailViewModel with combined Flow state
  - loadListDetails() - combines list + items flows
  - addItem(...) - adds item with validation
  - updateItem(...) - updates item with validation
  - deleteItem(id) - deletes item
  - toggleItemChecked(id, isChecked) - toggle without snackbar
- [x] Implement state management with StateFlow for UI state and Channel for events

### 2.3 UI Implementation
- [ ] ListsScreen - display all shopping lists as cards
- [ ] Add FAB for creating new lists
- [ ] ListDetailScreen - display list items
- [ ] Create ListItemCard component
- [ ] Add item creation dialog/bottom sheet
- [ ] Implement checkbox toggle functionality
- [ ] Add swipe-to-delete for items
- [ ] Navigation between lists and detail screens

## Phase 3: Recipes Feature 🍳

### 3.1 Use Cases
- [ ] GetRecipesUseCase
- [ ] CreateRecipeUseCase
- [ ] DeleteRecipeUseCase
- [ ] GetRecipeDetailUseCase
- [ ] AddRecipeToShoppingListUseCase

### 3.2 ViewModels & State
- [ ] Create RecipesViewModel
- [ ] Define RecipesUiState data class
- [ ] Create RecipeDetailViewModel
- [ ] Define RecipeDetailUiState data class

### 3.3 UI Implementation
- [ ] RecipesScreen - display all recipes as cards
- [ ] Add FAB for creating new recipes
- [ ] RecipeDetailScreen - display recipe ingredients
- [ ] Create RecipeCard component
- [ ] Add "Add to Shopping List" functionality
- [ ] Recipe creation/editing form

## Phase 4: UI Components & Polish ✨

### 4.1 Shared Components
- [ ] Move BottomBar to ui/components/
- [ ] Create CategoryChip component
- [ ] Create EmptyState component
- [ ] Create LoadingIndicator component
- [ ] Create ErrorView component
- [ ] Create ConfirmationDialog component

### 4.2 Top App Bar
- [ ] Implement TopAppBar with navigation drawer
- [ ] Add list title display
- [ ] Add share button (placeholder for now)

### 4.3 Categories
- [ ] Define category enum/sealed class
- [ ] Add category selector to item creation
- [ ] Implement category filtering/grouping in lists
- [ ] Add category icons/colors

## Phase 5: Advanced Features 🚀

### 5.1 Profile & Settings
- [ ] ProfileScreen basic implementation
- [ ] App settings (theme, language)
- [ ] Data management (export/import)

### 5.2 Search & Filter
- [ ] Add search functionality to lists
- [ ] Add search functionality to recipes
- [ ] Implement filtering by category
- [ ] Sort options (alphabetical, date, category)

### 5.3 Additional Features (Future)
- [ ] Photo capture for items (camera integration)
- [ ] Text-to-speech for adding items
- [ ] Sharing lists via link (requires backend)
- [ ] Offers/Deals section (if needed)

## Phase 6: Testing & Quality 🧪

### 6.1 Unit Tests
- [ ] Test use cases
- [ ] Test repositories
- [ ] Test ViewModels
- [ ] Test domain models

### 6.2 Integration Tests
- [ ] Test database operations
- [ ] Test repository implementations
- [ ] Test end-to-end flows

### 6.3 UI Tests
- [ ] Test navigation flows
- [ ] Test user interactions
- [ ] Test state updates

### 6.4 Code Quality
- [ ] Add detekt for static analysis
- [ ] Configure ktlint for code formatting
- [ ] Set up CI/CD pipeline
- [ ] Code review and refactoring

## Phase 7: Release Preparation 📦

- [ ] App icon and branding
- [ ] Splash screen
- [ ] Onboarding flow
- [ ] Privacy policy
- [ ] Play Store assets
- [ ] Beta testing
- [ ] Performance optimization
- [ ] Accessibility improvements
- [ ] Localization (German/English)

---

## Current Status
**Phase:** 2.2 - ViewModels & State ✅ **COMPLETED**

**Phase 2.2 Complete! ViewModels & State Management Finished! 🎉**

**What's Done:**
- ✅ **Phase 1**: Foundation & Data Layer (Database, Repositories, Common Utilities)
- ✅ **Phase 2.1**: Shopping Lists Use Cases (9 use cases)
- ✅ **Phase 2.2**: ViewModels & State - **2 ViewModels + 2 State classes implemented**

  **ShoppingListsViewModel:**
  - File: `presentation/features/shoppinglists/ShoppingListsViewModel.kt:25`
  - State: StateFlow<UiState<ListsUiState>> - holds lists
  - Events: SharedFlow<UiEvent> - one-time navigation/snackbars
  - Operations: loadShoppingLists(), createShoppingList(), deleteShoppingList(), onListClicked()
  - Auto-loads lists on init, observes Flow for reactive updates

  **ListDetailViewModel:**
  - File: `presentation/features/shoppinglists/ListDetailViewModel.kt:37`
  - State: Combines 2 Flows (list + items) into single UiState<ListDetailUiState>
  - SavedStateHandle for listId navigation argument
  - Operations: addItem(), updateItem(), deleteItem(), toggleItemChecked()
  - Smart UX: No snackbar for frequent checkbox toggles

**Architecture Patterns:**
- **StateFlow for State**: Persistent UI state that survives config changes
- **Channel for Events**: One-time events (navigation, snackbars) consumed only once
- **Flow Combination**: combine() operator merges list + items into unified state
- **Result → UiState Mapping**: Transforms domain Result<T> to presentation UiState<T>
- **Hilt Integration**: @HiltViewModel with constructor-injected use cases

**Key Implementation Details:**
- ListsUiState: Simple data class with `lists: List<ShoppingList>`
- ListDetailUiState: Combines `shoppingList: ShoppingList?` + `items: List<ListItem>`
- ViewModels handle all user actions and emit appropriate states/events
- Error messages from Result.Error are displayed via UiEvent.ShowSnackbar
- All operations use viewModelScope for automatic cancellation

**Next Phase:** 2.3 - UI Implementation 🚀

**Next Immediate Steps:**
1. Update ShoppingListsScreen to observe ViewModel state
2. Create ListDetailScreen composable
3. Add FAB for creating new lists
4. Implement list item cards with checkboxes
5. Add dialogs/bottom sheets for item creation/editing
6. Implement swipe-to-delete functionality
7. Wire up navigation between screens

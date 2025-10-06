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

### 2.3 UI Implementation ✅
- [x] ShoppingListsScreen - displays shopping lists as cards with LazyColumn
- [x] Add FAB for creating new lists with CreateListDialog
- [x] ShoppingListCard component with click handling
- [x] CreateListDialog with validation
- [x] ListDetailScreen - displays list items with TopAppBar
- [x] ListItemCard component with category, name, quantity, unit, checkbox
- [x] AddItemBottomSheet with dropdowns for unit & category
- [x] Checkbox toggle functionality (no snackbar for frequent action)
- [x] Swipe-to-delete with delete icon background
- [x] Navigation wired: listen → list_detail/{listId} with back button
- [x] Added hilt-navigation-compose dependency for hiltViewModel()

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
**Phase:** 2.3 - UI Implementation ✅ **COMPLETED**

**Phase 2 Complete! Shopping Lists Feature Fully Functional! 🎉**

**What's Done:**
- ✅ **Phase 1**: Foundation & Data Layer (Database, Repositories, Common Utilities)
- ✅ **Phase 2.1**: Shopping Lists Use Cases (9 use cases)
- ✅ **Phase 2.2**: ViewModels & State (2 ViewModels + 2 State classes)
- ✅ **Phase 2.3**: UI Implementation - **Complete shopping list feature!**

  **UI Components Created (7 files):**
  1. `ShoppingListCard.kt` - Card showing list name & creation date
  2. `ListItemCard.kt` - Card showing category, name, quantity, unit, checkbox
  3. `CreateListDialog.kt` - AlertDialog for creating lists with validation
  4. `AddItemBottomSheet.kt` - ModalBottomSheet with dropdowns for all item fields
  5. `ShoppingListsScreen.kt` - Full screen with state handling, FAB, empty states
  6. `ListDetailScreen.kt` - Detail screen with swipe-to-delete, TopAppBar, FAB
  7. `MainActivity.kt` - Navigation setup with list_detail/{listId} route

  **Key Features Implemented:**
  - ✅ Reactive UI with StateFlow - lists/items update automatically
  - ✅ Loading, success, error, and empty states for all screens
  - ✅ FAB + dialogs/bottom sheets for creating lists & items
  - ✅ Swipe-to-delete with visual feedback (delete icon)
  - ✅ Checkbox toggle for marking items (no snackbar spam)
  - ✅ Navigation with type-safe arguments (NavType.LongType)
  - ✅ Event handling via Channel (snackbars, navigation)
  - ✅ German UI text throughout

  **Dependency Added:**
  - `androidx.hilt:hilt-navigation-compose:1.2.0` for hiltViewModel()

**Architecture Highlights:**
- **Type Alias Used**: `Unit as MeasurementUnit` to avoid kotlin.Unit conflict
- **Enum Display**: Used `getDisplayName()` functions for Category & Unit
- **Swipe Gestures**: SwipeToDismissBox with EndToStart direction only
- **Bottom Sheet**: Material3 ModalBottomSheet with form validation
- **Navigation**: Proper argument extraction via SavedStateHandle

**Build Status:** ✅ Builds successfully (some deprecation warnings - non-blocking)

**Next Phase:** 3.1 - Recipes Use Cases 🚀

**Next Immediate Steps:**
1. Implement use cases for recipes (GetRecipesUseCase, CreateRecipeUseCase, etc.)
2. Add use case for adding recipe ingredients to shopping list
3. Create RecipesViewModel and RecipeDetailViewModel
4. Build recipes UI screens similar to shopping lists

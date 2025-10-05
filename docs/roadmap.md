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

### 2.1 Use Cases
- [ ] GetShoppingListsUseCase
- [ ] CreateShoppingListUseCase
- [ ] DeleteShoppingListUseCase
- [ ] GetListItemsUseCase
- [ ] AddListItemUseCase
- [ ] UpdateListItemUseCase
- [ ] DeleteListItemUseCase
- [ ] ToggleListItemCheckedUseCase

### 2.2 ViewModels & State
- [ ] Create ShoppingListsViewModel
- [ ] Define ListsUiState data class
- [ ] Create ListDetailViewModel
- [ ] Define ListDetailUiState data class
- [ ] Implement state management with StateFlow

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
**Phase:** 1.3 - Common Utilities ✅ **COMPLETED**

**Phase 1 Complete! Foundation & Data Layer Finished! 🎉**

**What's Done:**
- ✅ **Phase 1.1**: Complete database setup with Room, entities, DAOs, and mappers
- ✅ **Phase 1.2**: Repository layer with aggregate pattern
  - Result<T> wrapper class (Success/Error/Loading) for domain operations
  - ShoppingListRepository (6 shopping list + 8 list item operations)
  - RecipeRepository (6 recipe + 7 ingredient operations)
  - Repository implementations with Flow-based reactive streams
  - Comprehensive error handling with try-catch and Flow.catch
  - Hilt RepositoryModule with @Binds annotations
- ✅ **Phase 1.3**: Common presentation utilities
  - UiState<T> sealed class (Idle/Loading/Success/Error) for ViewModels
  - UiEvent sealed class (ShowSnackbar/ShowToast/Navigate) for one-time events
  - Helper extension functions for UiState
- ✅ Project builds successfully with all tests passing

**Architecture Decisions:**
- **Aggregate Pattern**: Parent entities manage children through the same repository
- **Separation of Concerns**: Result<T> for domain, UiState<T> for presentation
- **One-time Events**: UiEvent for navigation/snackbars (Channel/SharedFlow), UiState for persistent state (StateFlow)
- **Flow-based reactive streams** for automatic UI updates

**Next Phase:** 2.1 - Shopping Lists Use Cases 🚀

**Next Immediate Steps:**
1. Implement use cases for shopping list operations (GetShoppingListsUseCase, CreateShoppingListUseCase, etc.)
2. Each use case handles one specific business operation
3. Use cases will consume repositories and return Flow<Result<T>> or Result<T>

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
- [x] Create repository interfaces in domain layer
- [x] Implement ShoppingListRepository
- [x] Implement RecipeRepository
- [x] Add RepositoryModule for Hilt DI
- [x] Create Result wrapper class for error handling

### 1.3 Common Utilities
- [ ] Set up common/extensions package
- [ ] Set up common/utils package
- [ ] Create base UI state classes
- [ ] Create sealed classes for UI events

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
**Phase:** 1.2 - Repository Layer ✅ **COMPLETED**

**What's Done:**
- ✅ Phase 1.1: Complete database setup with Room, entities, DAOs, and mappers
- ✅ Phase 1.2: Repository layer with interfaces, implementations, and error handling
- ✅ Result wrapper class for explicit error handling
- ✅ Hilt DI modules for database and repositories
- ✅ Project builds successfully

**Next Phase:** 1.3 - Common Utilities (Optional) or 2.1 - Shopping Lists Use Cases

**Next Immediate Steps:**
1. Decide: Skip Phase 1.3 (utilities) and go directly to Phase 2.1 (use cases), or
2. Set up common utilities (base UI state classes, sealed classes for events)
3. Begin implementing use cases for shopping list operations

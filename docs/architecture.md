The-Shopping-List/
└── app/
    ├── build.gradle.kts
    ├── proguard-rules.pro
    └── src/
        ├── main/
        │   ├── AndroidManifest.xml
        │   ├── java/
        │   │   └── com/example/einkaufsliste/
        │   │       ├── App.kt
        │   │       ├── MainActivity.kt
        │   │       ├── navigation/
        │   │       │   ├── NavGraph.kt
        │   │       │   └── Destinations.kt
        │   │       ├── core/
        │   │       │   ├── designsystem/theme/
        │   │       │   │   ├── Color.kt
        │   │       │   │   ├── Theme.kt
        │   │       │   │   └── Type.kt
        │   │       │   ├── data/db/
        │   │       │   │   ├── AppDatabase.kt
        │   │       │   │   ├── dao/
        │   │       │   │   │   ├── ShoppingListDao.kt
        │   │       │   │   │   └── RecipeDao.kt
        │   │       │   │   └── entity/
        │   │       │   │       ├── ShoppingListEntity.kt
        │   │       │   │       ├── ListItemEntity.kt
        │   │       │   │       ├── RecipeEntity.kt
        │   │       │   │       └── RecipeIngredientEntity.kt
        │   │       │   ├── data/repository/
        │   │       │   │   ├── ShoppingListRepository.kt
        │   │       │   │   └── RecipeRepository.kt
        │   │       │   ├── domain/model/
        │   │       │   │   ├── ShoppingList.kt
        │   │       │   │   ├── ListItem.kt
        │   │       │   │   ├── Recipe.kt
        │   │       │   │   └── Ingredient.kt
        │   │       │   ├── domain/usecase/
        │   │       │   │   ├── GetShoppingListsUseCase.kt
        │   │       │   │   ├── AddListItemUseCase.kt
        │   │       │   │   ├── GetRecipesUseCase.kt
        │   │       │   │   └── AddRecipeToListUseCase.kt
        │   │       │   ├── di/
        │   │       │   │   ├── DatabaseModule.kt
        │   │       │   │   └── RepositoryModule.kt
        │   │       │   ├── common/
        │   │       │   │   ├── extensions/
        │   │       │   │   └── utils/
        │   │       │   └── util/
        │   │       │       └── Result.kt
        │   │       ├── feature/
        │   │       │   ├── lists/
        │   │       │   │   ├── ListsScreen.kt
        │   │       │   │   ├── ListDetailScreen.kt
        │   │       │   │   └── ListsViewModel.kt
        │   │       │   ├── recipes/
        │   │       │   │   ├── RecipesScreen.kt
        │   │       │   │   └── RecipesViewModel.kt
        │   │       │   ├── offers/
        │   │       │   │   └── OffersScreen.kt   // optional
        │   │       │   └── profile/
        │   │       │       └── ProfileScreen.kt
        │   │       └── ui/
        │   │           └── components/
        │   │               └── BottomBar.kt
        │   └── res/
        │       ├── values/strings.xml
        │       └── drawable/
        └── test/ …

## Architecture Principles

### MVVM Pattern
The app follows the **Model-View-ViewModel (MVVM)** architecture pattern:

- **View (Composables)**: UI layer built with Jetpack Compose
- **ViewModel**: Holds UI state and handles user interactions, uses Kotlin Flow/StateFlow
- **Model**: Domain models and business logic in use cases

### Data Flow
1. User interaction triggers ViewModel function
2. ViewModel calls appropriate Use Case
3. Use Case executes business logic via Repository
4. Repository accesses data from Room DB
5. Data flows back through layers as Flow/StateFlow
6. UI recomposes when state changes

### State Management
- Use `StateFlow` for UI state in ViewModels
- Define UI state as data classes (e.g., `ListsUiState`, `RecipeDetailUiState`)
- Include loading, success, and error states
- Use sealed classes for one-time events (navigation, snackbars)

### Error Handling
- Use `Result<T>` wrapper for operations that can fail
- Handle errors at ViewModel level
- Display user-friendly error messages in UI
- Log errors for debugging

### Async Operations
- Use Kotlin Coroutines with `viewModelScope`
- Use Flow for reactive data streams from Room
- Repository functions return Flow or suspend functions

### Dependency Injection
- Use Dagger Hilt throughout the app
- Provide dependencies at appropriate scopes (Singleton, ViewModelScoped)
- Use constructor injection in ViewModels and Use Cases

## Key Recommendations

1. **Use Case Layer**: Always access repositories through use cases, not directly from ViewModels
2. **Single Responsibility**: Each use case should handle one specific business operation
3. **Offline-First**: Design with local database as source of truth
4. **Testability**: Keep business logic in use cases and repositories for easy unit testing
5. **Coroutines**: Use structured concurrency with proper scope management
6. **State Hoisting**: Keep composables stateless, hoist state to ViewModels
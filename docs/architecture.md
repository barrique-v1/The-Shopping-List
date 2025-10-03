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
        │   │       │   ├── di/
        │   │       │   │   ├── DatabaseModule.kt
        │   │       │   │   └── RepositoryModule.kt
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
        │   │       └── widgets/
        │   │           └── BottomBar.kt
        │   └── res/
        │       ├── values/strings.xml
        │       └── drawable/
        └── test/ …
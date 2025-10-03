package com.example.theshoppinglist.domain.model

enum class Category {
    OBST,           // Fruits
    GEMUESE,        // Vegetables
    FLEISCH,        // Meat
    FISCH,          // Fish
    MILCHPRODUKTE,  // Dairy
    BACKWAREN,      // Bakery
    GETRAENKE,      // Beverages
    SUESSIGKEITEN,  // Sweets
    KONSERVEN,      // Canned goods
    TIEFKUEHL,      // Frozen
    GEWUERZE,       // Spices
    SONSTIGES;      // Other

    fun getDisplayName(): String {
        return when (this) {
            OBST -> "Obst"
            GEMUESE -> "Gemüse"
            FLEISCH -> "Fleisch"
            FISCH -> "Fisch"
            MILCHPRODUKTE -> "Milchprodukte"
            BACKWAREN -> "Backwaren"
            GETRAENKE -> "Getränke"
            SUESSIGKEITEN -> "Süßigkeiten"
            KONSERVEN -> "Konserven"
            TIEFKUEHL -> "Tiefkühl"
            GEWUERZE -> "Gewürze"
            SONSTIGES -> "Sonstiges"
        }
    }
}

package com.example.theshoppinglist.domain.model

enum class Unit {
    GRAMM,          // g
    KILOGRAMM,      // kg
    MILLILITER,     // ml
    LITER,          // l
    STUECK,         // pieces
    PACKUNG,        // package
    DOSE,           // can
    BUND,           // bunch
    PRISE,          // pinch
    ESSLOEFFEL,     // tablespoon
    TEELOEFFEL;     // teaspoon

    fun getDisplayName(): String {
        return when (this) {
            GRAMM -> "g"
            KILOGRAMM -> "kg"
            MILLILITER -> "ml"
            LITER -> "l"
            STUECK -> "Stück"
            PACKUNG -> "Packung"
            DOSE -> "Dose"
            BUND -> "Bund"
            PRISE -> "Prise"
            ESSLOEFFEL -> "EL"
            TEELOEFFEL -> "TL"
        }
    }
}

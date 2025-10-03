Die App soll eine Einkaufslisten-App sein, die es ermöglicht, verschiedene Einkaufslisten zu erstellen, zu verwalten und zu teilen. Die App soll benutzerfreundlich und intuitiv gestaltet sein.
Man soll zudem Rezepte speichern können und die Zutaten dieser Rezepte auf den Einkaufslisten hinzufügen können.

- *die App soll mit Android Native (Jetpack Compose) entwickelt werden*

### Top App Bar

*Elemente von links nach rechts*

- Icon Button: “Navigation drawer öffnen”
- Title der Liste
-Icon Button: “Liste teilen” → teilen über Link (Backend notwendig)

# Navigation - Die Bottom Navigation Bar

### Listen

Beschreibung: Im Tab “Listen” sollen alle Hauptlisten als “Cards” untereinander aufgelistet sein
    - eine Liste kann man mithilfe eines Buttons “Erstellen” unten rechts erstellen

Wenn man eine Liste selektiert, kommt man auf einen neuen Screen mit dem Inhalt der Liste:
    - die Elemente der Liste stellen die Produkte dar

**List Item Aufbau von links nach rechts**
    - Kategorie Item | Bezeichnung | Menge | Einheit | Checkbox

Beispiele:

- Kategorie: Obst, Gemüse, Fleisch, usw.
- genaue Bezeichung des Artikels
- Menge: g, l, ml
- Checkbox (über die Checkbox kann man das Listenitem abhaken

### Rezepte

Beschreibung: Die Rezepte sind ähnlich aufgebaut wie die Einkaufslisten. Der Unterschied ist, dass man die Rezepte mit ihren Zutaten als ganzes einer Einkaufsliste hinzufügen kann.

### Profil

Beschreibung folgt später

### Button Navigation Bar

- Tabs: Listen | Rezepte | Angebote | Profil

- Unterlisten von Listen
    - in diesen Unterlisten sollen z.B. Rezepte gespeichert werden können
    - man soll Rezepte und deren Zutaten speichern können
    - man kann diese Rezepte dann einer Hauptliste hinzufügen, wo man dann nach Rezepten
    - man soll Fotos machen können von Zutaten und diese dann der Liste hinzufügen können
    - zudem soll man mit Text-to-Voice ebenfalls Dinge der Liste hinzufügen können

# Architektur


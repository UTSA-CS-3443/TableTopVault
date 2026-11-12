# TableTop Vault - Group 6

JavaFX board game inventory, patron directory, and rental management application built using the Model-View-Controller pattern.

## Features
- Public Game Catalog and Staff Login: View shelf availability upon startup and log in as staff (1234 / 1234).
- The Shelf (Game CRUD): List, search, add, update, and delete board games with immediate persistence to data/games.txt.
- Customer Management (Patron CRUD): Register, search, update, and remove customer profiles persisted in data/customers.txt.
- Checkout and Check-In Counter: Record board game checkouts and returns, automatically updating shelf availability and data/rentals.txt.

## Running the Application
    chmod +x mvnw
    ./mvnw clean javafx:run

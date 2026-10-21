package edu.utsa.cs3443.tabletopvault.group6;

import edu.utsa.cs3443.tabletopvault.group6.controller.CustomerController;
import edu.utsa.cs3443.tabletopvault.group6.controller.GameController;
import edu.utsa.cs3443.tabletopvault.group6.controller.mainScreenController;
import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Rental;

import java.util.Scanner;

/**
 * Java Notes:
 * - Uses java.util.Scanner to handle interactive command-line input/output.
 * - Drives CRUD operations through mainScreenController, GameController, CustomerController,
 *   and GameRentalManager, keeping presentation separate from data persistence.
 */
public class Main {

    /**
     * Main entry point for the TableTop Vault console prototype.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        GameRentalManager manager = new GameRentalManager();
        manager.loadAllData(); // Loads games, customers, rentals, and staff

        mainScreenController mainController = new mainScreenController(manager);
        GameController gameController = mainController.getGameController();
        CustomerController customerController = mainController.getCustomerController();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n**************************** [TABLETOP VAULT] ****************************");
            System.out.println("1. The Shelf (game collection)");
            System.out.println("2. Checkout Counter");
            System.out.println("3. Add/Update Customer");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleShelfMenu(scanner, gameController);
                    break;
                case "2":
                    handleCheckoutMenu(scanner, mainController);
                    break;
                case "3":
                    handleCustomerMenu(scanner, customerController);
                    break;
                case "4":
                    System.out.println("Saving all data and exiting TableTop Vault. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1-4.");
            }
        }
        scanner.close();
    }

    /**
     * Displays and processes the interactive submenu for The Shelf (board game inventory).
     *
     * @param scanner        Scanner for console input
     * @param gameController controller managing game operations
     */
    private static void handleShelfMenu(Scanner scanner, GameController gameController) {
        boolean inShelf = true;
        while (inShelf) {
            System.out.println("\n==================== [THE SHELF] ====================");
            System.out.println("1. View All Games");
            System.out.println("2. Add a New Game");
            System.out.println("3. Update Game Availability / Notes");
            System.out.println("4. Remove a Game");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Current Game Inventory ---");
                    if (gameController.getAllGames().isEmpty()) {
                        System.out.println("No games found in inventory.");
                    } else {
                        for (Game g : gameController.getAllGames()) {
                            System.out.println(g);
                        }
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter Game Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter Minimum Players: ");
                        int min = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Maximum Players: ");
                        int max = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Category (e.g., Strategy, Party, Co-op): ");
                        String category = scanner.nextLine().trim();
                        System.out.print("Enter Game Notes (or 'None'): ");
                        String notes = scanner.nextLine().trim();

                        if (gameController.createGame(title, min, max, category, true, notes)) {
                            System.out.println("Game added and saved to data/games.txt!");
                        } else {
                            System.out.println("Error: Could not add game (check player counts or duplicate title).");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number entered for player count.");
                    }
                    break;
                case "3":
                    System.out.print("Enter the Game Title to update: ");
                    String updateTitle = scanner.nextLine().trim();
                    System.out.print("Is the game available? (true/false): ");
                    boolean available = Boolean.parseBoolean(scanner.nextLine().trim());
                    System.out.print("Enter updated notes (leave blank to keep current): ");
                    String newNotes = scanner.nextLine().trim();

                    if (gameController.updateGameAvailabilityAndNotes(updateTitle, available, newNotes)) {
                        System.out.println("Game updated and saved to data/games.txt!");
                    } else {
                        System.out.println("Game not found.");
                    }
                    break;
                case "4":
                    System.out.print("Enter the Game Title to remove: ");
                    String removeTitle = scanner.nextLine().trim();
                    if (gameController.deleteGame(removeTitle)) {
                        System.out.println("Game removed and data/games.txt updated!");
                    } else {
                        System.out.println("Game not found.");
                    }
                    break;
                case "5":
                    inShelf = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1-5.");
            }
        }
    }

    /**
     * Displays and processes the interactive submenu for the Checkout Counter (rentals & returns).
     *
     * @param scanner        Scanner for console input
     * @param mainController controller managing rental checkouts and returns
     */
    private static void handleCheckoutMenu(Scanner scanner, mainScreenController mainController) {
        boolean inCheckout = true;
        while (inCheckout) {
            System.out.println("\n==================== [CHECKOUT COUNTER] ====================");
            System.out.println("1. View All Rentals");
            System.out.println("2. Checkout a Game");
            System.out.println("3. Return a Game");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Rental History & Active Checkouts ---");
                    if (mainController.getAllRentals().isEmpty()) {
                        System.out.println("No rentals recorded.");
                    } else {
                        for (Rental r : mainController.getAllRentals()) {
                            System.out.println(r);
                        }
                    }
                    break;
                case "2":
                    System.out.print("Enter Game Title to checkout: ");
                    String checkoutGame = scanner.nextLine().trim();
                    System.out.print("Enter Customer Full Name: ");
                    String checkoutCust = scanner.nextLine().trim();

                    if (mainController.handleCheckout(checkoutGame, checkoutCust)) {
                        System.out.println("Checkout successful! Saved to data/rentals.txt and data/games.txt.");
                    } else {
                        System.out.println("Checkout failed. Verify the game exists, is available, and the customer is registered.");
                    }
                    break;
                case "3":
                    System.out.print("Enter Game Title to return: ");
                    String returnGame = scanner.nextLine().trim();
                    System.out.print("Enter Customer Full Name: ");
                    String returnCust = scanner.nextLine().trim();

                    if (mainController.handleReturn(returnGame, returnCust)) {
                        System.out.println("Return processed! Saved to data/rentals.txt and data/games.txt.");
                    } else {
                        System.out.println("Return failed. Active rental matching that game and customer was not found.");
                    }
                    break;
                case "4":
                    inCheckout = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1-4.");
            }
        }
    }

    /**
     * Displays and processes the interactive submenu for Customer Management.
     *
     * @param scanner            Scanner for console input
     * @param customerController controller managing customer CRUD operations
     */
    private static void handleCustomerMenu(Scanner scanner, CustomerController customerController) {
        boolean inCustomers = true;
        while (inCustomers) {
            System.out.println("\n==================== [CUSTOMER MANAGEMENT] ====================");
            System.out.println("1. View All Customers");
            System.out.println("2. Register New Customer");
            System.out.println("3. Update Existing Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Registered Customers ---");
                    if (customerController.getAllCustomers().isEmpty()) {
                        System.out.println("No customers registered.");
                    } else {
                        for (Customer c : customerController.getAllCustomers()) {
                            System.out.println(c);
                        }
                    }
                    break;
                case "2":
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine().trim();
                    System.out.print("Enter Preferred Contact (Email/Phone/Text): ");
                    String pref = scanner.nextLine().trim();
                    System.out.print("Enter Customer Notes (or 'None'): ");
                    String notes = scanner.nextLine().trim();

                    if (customerController.registerCustomer(name, email, phone, pref, notes)) {
                        System.out.println("Customer registered and saved to data/customers.txt!");
                    } else {
                        System.out.println("Error: Could not register customer (check required fields or duplicate name).");
                    }
                    break;
                case "3":
                    System.out.print("Enter Full Name of customer to update: ");
                    String targetName = scanner.nextLine().trim();
                    System.out.print("Enter new Email (leave blank to keep current): ");
                    String newEmail = scanner.nextLine().trim();
                    System.out.print("Enter new Phone (leave blank to keep current): ");
                    String newPhone = scanner.nextLine().trim();
                    System.out.print("Enter new Preferred Contact (leave blank to keep current): ");
                    String newPref = scanner.nextLine().trim();
                    System.out.print("Enter new Notes (leave blank to keep current): ");
                    String newNotes = scanner.nextLine().trim();

                    if (customerController.updateCustomerProfile(targetName, newEmail, newPhone, newPref, newNotes)) {
                        System.out.println("Customer updated and saved to data/customers.txt!");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case "4":
                    System.out.print("Enter Full Name of customer to remove: ");
                    String removeName = scanner.nextLine().trim();
                    if (customerController.removeCustomer(removeName)) {
                        System.out.println("Customer removed and data/customers.txt updated!");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case "5":
                    inCustomers = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1-5.");
            }
        }
    }
}
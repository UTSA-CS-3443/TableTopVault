package edu.utsa.cs3443.tabletopvault.group6;

import edu.utsa.cs3443.tabletopvault.group6.model.*;
import java.util.Scanner;

/**
 * Main application entry point and console presentation layer for TableTop Vault.
 *
 * Java Notes:
 * - Uses java.util.Scanner to handle interactive command-line loops.
 * - Drives CRUD operations through GameRentalManager, keeping presentation separate from data handling.
 */
public class Main {

    public static void main(String[] args) {
        GameRentalManager manager = new GameRentalManager();
        manager.loadAllData(); // Loads games, customers, rentals, and staff from text files

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n******************************* [TABLETOP VAULT] *******************************");
            System.out.println("1. The Shelf (game collection)");
            System.out.println("2. Checkout Counter");
            System.out.println("3. Add/Update Customer");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    runShelfMenu(manager, scanner);
                    break;
                case "2":
                    runCheckoutMenu(manager, scanner);
                    break;
                case "3":
                    runCustomerMenu(manager, scanner);
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting TableTop Vault. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }
        scanner.close();
    }

    private static void runShelfMenu(GameRentalManager manager, Scanner scanner) {
        boolean shelfRunning = true;
        while (shelfRunning) {
            System.out.println("\n******************************* [THE SHELF] *******************************");
            System.out.println("1. View Games");
            System.out.println("2. Add new game to inventory");
            System.out.println("3. Remove game from inventory");
            System.out.println("4. Update status of game");
            System.out.println("5. Exit back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nGame Title | Min Players | Max Players | Category | Available | Game Notes");
                    for (Game g : manager.getGames()) {
                        System.out.println(g.getGameTitle() + " | " + g.getMinPlayers() + " | " +
                                g.getMaxPlayers() + " | " + g.getCategory() + " | " +
                                g.isAvailable() + " | " + g.getGameNotes());
                    }
                    break;
                case "2":
                    System.out.print("Enter Game Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Min Players: ");
                    int min = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Max Players: ");
                    int max = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Category: ");
                    String cat = scanner.nextLine();
                    System.out.print("Enter Game Notes (or 'No comment'): ");
                    String notes = scanner.nextLine();

                    manager.addGame(new Game(title, min, max, cat, true, notes));
                    System.out.println("Game Successfully Added!");
                    break;
                case "3":
                    System.out.print("Enter Game Title to remove: ");
                    String removeTitle = scanner.nextLine();
                    if (manager.removeGame(removeTitle)) {
                        System.out.println("Game removed from inventory.");
                    } else {
                        System.out.println("Game not found.");
                    }
                    break;
                case "4":
                    System.out.print("Enter Game Title to update: ");
                    String updateTitle = scanner.nextLine();
                    System.out.print("Is the game available? (true/false): ");
                    boolean avail = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Enter updated Game Notes (leave blank to keep current): ");
                    String newNotes = scanner.nextLine();
                    if (manager.updateGameStatus(updateTitle, avail, newNotes)) {
                        System.out.println("Game status updated.");
                    } else {
                        System.out.println("Game not found.");
                    }
                    break;
                case "5":
                    shelfRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void runCheckoutMenu(GameRentalManager manager, Scanner scanner) {
        boolean checkoutRunning = true;
        while (checkoutRunning) {
            System.out.println("\n******************************* [Checkout Desk] *******************************");
            System.out.println("1. View rentals");
            System.out.println("2. Checkout new game");
            System.out.println("3. Return game");
            System.out.println("4. Exit back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nGame Title | Customer Name | Checkout Date | Return Date | Returned");
                    for (Rental r : manager.getRentals()) {
                        System.out.println(r.toCsvString().replace(",", " | "));
                    }
                    break;
                case "2":
                    System.out.print("Enter Game Title to checkout: ");
                    String gameTitle = scanner.nextLine();
                    System.out.print("Enter Customer Full Name: ");
                    String custName = scanner.nextLine();
                    if (manager.checkoutGame(gameTitle, custName)) {
                        System.out.println("Game checked out successfully!");
                    } else {
                        System.out.println("Checkout failed. Check game availability or names.");
                    }
                    break;
                case "3":
                    System.out.print("Enter Game Title to return: ");
                    String returnGameTitle = scanner.nextLine();
                    System.out.print("Enter Customer Full Name: ");
                    String returnCustName = scanner.nextLine();
                    if (manager.returnGame(returnGameTitle, returnCustName)) {
                        System.out.println("Game returned successfully!");
                    } else {
                        System.out.println("Active rental not found.");
                    }
                    break;
                case "4":
                    checkoutRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void runCustomerMenu(GameRentalManager manager, Scanner scanner) {
        boolean custRunning = true;
        while (custRunning) {
            System.out.println("\n******************************* [Add / Update Customer] *******************************");
            System.out.println("1. View current customer listings");
            System.out.println("2. Add new customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Exit back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nFull Name | Email | Phone Number | Preferred Contact | Customer Notes");
                    for (Customer c : manager.getCustomers()) {
                        System.out.println(c.getFullName() + " | " + c.getEmail() + " | " +
                                c.getPhoneNumber() + " | " + c.getPreferredContact() + " | " +
                                c.getCustomerNotes());
                    }
                    break;
                case "2":
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email Address: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Preferred Contact (Email/Text): ");
                    String pref = scanner.nextLine();
                    System.out.print("Enter Customer Notes: ");
                    String notes = scanner.nextLine();

                    manager.addCustomer(new Customer(name, email, phone, pref, notes));
                    System.out.println("Customer added successfully!");
                    break;
                case "3":
                    System.out.print("Enter Full Name of customer to update: ");
                    String targetName = scanner.nextLine();
                    System.out.print("New Email (leave blank to keep current): ");
                    String newEmail = scanner.nextLine();
                    System.out.print("New Phone (leave blank to keep current): ");
                    String newPhone = scanner.nextLine();
                    System.out.print("New Preferred Contact (leave blank to keep current): ");
                    String newPref = scanner.nextLine();
                    System.out.print("New Notes (leave blank to keep current): ");
                    String newNotes = scanner.nextLine();

                    if (manager.updateCustomer(targetName, newEmail, newPhone, newPref, newNotes)) {
                        System.out.println("Customer updated!");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case "4":
                    System.out.print("Enter Full Name of customer to remove: ");
                    String delName = scanner.nextLine();
                    if (manager.removeCustomer(delName)) {
                        System.out.println("Customer removed.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case "5":
                    custRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
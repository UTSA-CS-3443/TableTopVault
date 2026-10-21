package edu.utsa.cs3443.tabletopvault.group6.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Core service and persistence manager for TableTop Vault.
 * Maintains in-memory collections of games, customers, rentals, and staff,
 * and synchronizes state mutations immediately to flat files in the data/ directory.
 */
public class GameRentalManager {

    private final ArrayList<Game> games;
    private final ArrayList<Customer> customers;
    private final ArrayList<Rental> rentals;
    private final ArrayList<Staff> staffList;

    /**
     * Initializes empty collections for games, customers, rentals, and staff.
     */
    public GameRentalManager() {
        this.games = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.staffList = new ArrayList<>();
    }

    /**
     * Loads all application data files from the local data/ directory.
     */
    public void loadAllData() {
        loadGames();
        loadCustomers();
        loadRentals();
        loadStaff();
    }

    /**
     * Reads game records from data/games.txt into the in-memory games list.
     */
    public void loadGames() {
        games.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/games.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String title = parts[0].trim();
                    int min = Integer.parseInt(parts[1].trim());
                    int max = Integer.parseInt(parts[2].trim());
                    String category = parts[3].trim();
                    boolean available = Boolean.parseBoolean(parts[4].trim());
                    String notes = parts[5].trim();
                    games.add(new Game(title, min, max, category, available, notes));
                }
            }
        } catch (IOException e) {
            System.out.println("Note: Could not load data/games.txt or file is empty.");
        }
    }

    /**
     * Reads customer records from data/customers.txt into the in-memory customers list.
     */
    public void loadCustomers() {
        customers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String name = parts[0].trim();
                    String email = parts[1].trim();
                    String phone = parts[2].trim();
                    String pref = parts[3].trim();
                    String notes = parts[4].trim();
                    customers.add(new Customer(name, email, phone, pref, notes));
                }
            }
        } catch (IOException e) {
            System.out.println("Note: Could not load data/customers.txt or file is empty.");
        }
    }

    /**
     * Reads rental records from data/rentals.txt and links them to existing Game and Customer objects.
     */
    public void loadRentals() {
        rentals.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/rentals.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String gameTitle = parts[0].trim();
                    String custName = parts[1].trim();
                    LocalDate checkoutDate = LocalDate.parse(parts[2].trim());
                    LocalDate returnDate = parts[3].trim().equalsIgnoreCase("Not returned")
                            ? null
                            : LocalDate.parse(parts[3].trim());
                    boolean returned = Boolean.parseBoolean(parts[4].trim());

                    Game matchingGame = findGameByTitle(gameTitle);
                    Customer matchingCust = findCustomerByName(custName);

                    if (matchingGame != null && matchingCust != null) {
                        rentals.add(new Rental(matchingGame, matchingCust, checkoutDate, returnDate, returned));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Note: Could not load data/rentals.txt or file is empty.");
        }
    }

    /**
     * Reads staff records from data/staff.txt into the in-memory staffList.
     */
    public void loadStaff() {
        staffList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/staff.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String name = parts[0].trim();
                    String email = parts[1].trim();
                    String phone = parts[2].trim();
                    String username = parts[3].trim();
                    String passHash = parts[4].trim();
                    staffList.add(new Staff(name, email, phone, username, passHash));
                }
            }
        } catch (IOException e) {
            System.out.println("Note: Could not load data/staff.txt or file is empty.");
        }
    }

    /**
     * Finds a game in the collection by its title (case-insensitive).
     *
     * @param title title of the game to search for
     * @return matching Game object, or null if not found
     */
    public Game findGameByTitle(String title) {
        for (Game g : games) {
            if (g.getGameTitle().equalsIgnoreCase(title.trim())) {
                return g;
            }
        }
        return null;
    }

    /**
     * Finds a customer in the collection by full name (case-insensitive).
     *
     * @param name full name of the customer to search for
     * @return matching Customer object, or null if not found
     */
    public Customer findCustomerByName(String name) {
        for (Customer c : customers) {
            if (c.getFullName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    // ==================== GAME CRUD & PERSISTENCE ====================

    /**
     * Adds a new game to the inventory and immediately persists to data/games.txt.
     *
     * @param newGame the Game object to add
     */
    public void addGame(Game newGame) {
        games.add(newGame);
        saveGames();
    }

    /**
     * Removes a game by title and updates data/games.txt.
     *
     * @param title title of the game to remove
     * @return true if removed, false if not found
     */
    public boolean removeGame(String title) {
        boolean removed = games.removeIf(g -> g.getGameTitle().equalsIgnoreCase(title.trim()));
        if (removed) {
            saveGames();
        }
        return removed;
    }

    /**
     * Updates a game's availability and optional notes, then saves to data/games.txt.
     *
     * @param title     title of the game to update
     * @param available updated availability status
     * @param notes     updated notes (ignored if null or blank)
     * @return true if found and updated, false otherwise
     */
    public boolean updateGameStatus(String title, boolean available, String notes) {
        for (Game g : games) {
            if (g.getGameTitle().equalsIgnoreCase(title.trim())) {
                g.setAvailable(available);
                if (notes != null && !notes.isBlank()) {
                    g.setGameNotes(notes);
                }
                saveGames();
                return true;
            }
        }
        return false;
    }

    /**
     * Writes all current games in memory to data/games.txt.
     */
    public void saveGames() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/games.txt"))) {
            for (Game g : games) {
                writer.println(g.getGameTitle() + "," + g.getMinPlayers() + ","
                        + g.getMaxPlayers() + "," + g.getCategory() + ","
                        + g.isAvailable() + "," + g.getGameNotes());
            }
        } catch (IOException e) {
            System.out.println("Error saving games: " + e.getMessage());
        }
    }

    // ==================== CUSTOMER CRUD & PERSISTENCE ====================

    /**
     * Adds a new customer and immediately persists to data/customers.txt.
     *
     * @param newCustomer the Customer object to add
     */
    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        saveCustomers();
    }

    /**
     * Removes a customer by full name and updates data/customers.txt.
     *
     * @param fullName full name of the customer to remove
     * @return true if removed, false if not found
     */
    public boolean removeCustomer(String fullName) {
        boolean removed = customers.removeIf(c -> c.getFullName().equalsIgnoreCase(fullName.trim()));
        if (removed) {
            saveCustomers();
        }
        return removed;
    }

    /**
     * Updates an existing customer's contact fields and saves to data/customers.txt.
     *
     * @param fullName    full name of the customer to update
     * @param email       new email (kept unchanged if blank)
     * @param phone       new phone number (kept unchanged if blank)
     * @param prefContact new preferred contact (kept unchanged if blank)
     * @param notes       new customer notes (kept unchanged if blank)
     * @return true if customer was found and updated, false otherwise
     */
    public boolean updateCustomer(String fullName, String email, String phone, String prefContact, String notes) {
        for (Customer c : customers) {
            if (c.getFullName().equalsIgnoreCase(fullName.trim())) {
                if (email != null && !email.isBlank()) c.setEmail(email);
                if (phone != null && !phone.isBlank()) c.setPhoneNumber(phone);
                if (prefContact != null && !prefContact.isBlank()) c.setPreferredContact(prefContact);
                if (notes != null && !notes.isBlank()) c.setCustomerNotes(notes);
                saveCustomers();
                return true;
            }
        }
        return false;
    }

    /**
     * Writes all current customers in memory to data/customers.txt.
     */
    public void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/customers.txt"))) {
            for (Customer c : customers) {
                writer.println(c.getFullName() + "," + c.getEmail() + ","
                        + c.getPhoneNumber() + "," + c.getPreferredContact() + ","
                        + c.getCustomerNotes());
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    // ==================== RENTAL TRANSACTIONS & PERSISTENCE ====================

    /**
     * Processes a game checkout for a customer if the game exists and is currently available.
     *
     * @param gameTitle    title of the game to check out
     * @param customerName full name of the borrowing customer
     * @return true if checkout succeeded, false otherwise
     */
    public boolean checkoutGame(String gameTitle, String customerName) {
        Game targetGame = findGameByTitle(gameTitle);
        Customer targetCustomer = findCustomerByName(customerName);

        if (targetGame == null || targetCustomer == null || !targetGame.isAvailable()) {
            return false;
        }

        targetGame.setAvailable(false);
        Rental rental = new Rental(targetGame, targetCustomer);
        rentals.add(rental);
        saveGames();
        saveRentals();
        return true;
    }

    /**
     * Processes a game return for an active rental and updates both rentals.txt and games.txt.
     *
     * @param gameTitle    title of the game being returned
     * @param customerName full name of the customer returning the game
     * @return true if an active rental was found and returned, false otherwise
     */
    public boolean returnGame(String gameTitle, String customerName) {
        for (Rental r : rentals) {
            if (!r.isReturned()
                    && r.getGame().getGameTitle().equalsIgnoreCase(gameTitle.trim())
                    && r.getCustomer().getFullName().equalsIgnoreCase(customerName.trim())) {
                r.returnGame();
                saveGames();
                saveRentals();
                return true;
            }
        }
        return false;
    }

    /**
     * Writes all rental records in memory to data/rentals.txt.
     */
    public void saveRentals() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/rentals.txt"))) {
            for (Rental r : rentals) {
                writer.println(r.toCsvString());
            }
        } catch (IOException e) {
            System.out.println("Error saving rentals: " + e.getMessage());
        }
    }
}
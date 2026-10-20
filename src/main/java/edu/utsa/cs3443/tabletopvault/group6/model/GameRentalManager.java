package edu.utsa.cs3443.tabletopvault.group6.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Java Notes:
 * - Acts as the core service layer enforcing separation of concerns between presentation and data.
 * - Implements immediate disk synchronization via java.io.PrintWriter and java.io.FileWriter upon state mutation.
 */
public class GameRentalManager {

    private ArrayList<Game> games;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;
    private ArrayList<Staff> staffList;

    public GameRentalManager() {
        this.games = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.staffList = new ArrayList<>();
    }

    public void loadAllData() {
        loadGames();
        loadCustomers();
        loadRentals();
        loadStaff();
    }

    public void loadGames() {
        games.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/games.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
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
            System.out.println("Note: Could not load games.txt or file is empty.");
        }
    }

    public void loadCustomers() {
        customers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
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
            System.out.println("Note: Could not load customers.txt or file is empty.");
        }
    }

    public void loadRentals() {
        rentals.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/rentals.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String gameTitle = parts[0].trim();
                    String custName = parts[1].trim();
                    LocalDate checkoutDate = LocalDate.parse(parts[2].trim());
                    LocalDate returnDate = parts[3].trim().equals("Not returned") ? null : LocalDate.parse(parts[3].trim());
                    boolean returned = Boolean.parseBoolean(parts[4].trim());

                    Game matchingGame = findGameByTitle(gameTitle);
                    Customer matchingCust = findCustomerByName(custName);

                    if (matchingGame != null && matchingCust != null) {
                        rentals.add(new Rental(matchingGame, matchingCust, checkoutDate, returnDate, returned));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Note: Could not load rentals.txt or file is empty.");
        }
    }

    public void loadStaff() {
        staffList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/staff.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
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
            System.out.println("Note: Could not load staff.txt or file is empty.");
        }
    }

    public Game findGameByTitle(String title) {
        for (Game g : games) {
            if (g.getGameTitle().equalsIgnoreCase(title.trim())) {
                return g;
            }
        }
        return null;
    }

    public Customer findCustomerByName(String name) {
        for (Customer c : customers) {
            if (c.getFullName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Game> getGames() { return games; }
    public ArrayList<Customer> getCustomers() { return customers; }
    public ArrayList<Rental> getRentals() { return rentals; }
    public ArrayList<Staff> getStaffList() { return staffList; }

    // ==================== GAME CRUD & PERSISTENCE ====================
    public void addGame(Game newGame) {
        games.add(newGame);
        saveGames();
    }

    public boolean removeGame(String title) {
        boolean removed = games.removeIf(g -> g.getGameTitle().equalsIgnoreCase(title.trim()));
        if (removed) saveGames();
        return removed;
    }

    public boolean updateGameStatus(String title, boolean available, String notes) {
        for (Game g : games) {
            if (g.getGameTitle().equalsIgnoreCase(title.trim())) {
                g.setAvailable(available);
                if (notes != null && !notes.isBlank()) g.setGameNotes(notes);
                saveGames();
                return true;
            }
        }
        return false;
    }

    public void saveGames() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/games.txt"))) {
            for (Game g : games) {
                writer.println(g.getGameTitle() + "," + g.getMinPlayers() + "," +
                        g.getMaxPlayers() + "," + g.getCategory() + "," +
                        g.isAvailable() + "," + g.getGameNotes());
            }
        } catch (IOException e) {
            System.out.println("Error saving games: " + e.getMessage());
        }
    }

    // ==================== CUSTOMER CRUD & PERSISTENCE ====================
    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        saveCustomers();
    }

    public boolean removeCustomer(String fullName) {
        boolean removed = customers.removeIf(c -> c.getFullName().equalsIgnoreCase(fullName.trim()));
        if (removed) saveCustomers();
        return removed;
    }

    public boolean updateCustomer(String fullName, String email, String phone, String prefContact, String notes) {
        for (Customer c : customers) {
            if (c.getFullName().equalsIgnoreCase(fullName.trim())) {
                if (!email.isBlank()) c.setEmail(email);
                if (!phone.isBlank()) c.setPhoneNumber(phone);
                if (!prefContact.isBlank()) c.setPreferredContact(prefContact);
                if (!notes.isBlank()) c.setCustomerNotes(notes);
                saveCustomers();
                return true;
            }
        }
        return false;
    }

    public void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/customers.txt"))) {
            for (Customer c : customers) {
                writer.println(c.getFullName() + "," + c.getEmail() + "," +
                        c.getPhoneNumber() + "," + c.getPreferredContact() + "," +
                        c.getCustomerNotes());
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    // ==================== RENTAL TRANSACTIONS & PERSISTENCE ====================
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

    public boolean returnGame(String gameTitle, String customerName) {
        for (Rental r : rentals) {
            if (!r.isReturned() &&
                    r.getGame().getGameTitle().equalsIgnoreCase(gameTitle.trim()) &&
                    r.getCustomer().getFullName().equalsIgnoreCase(customerName.trim())) {
                r.returnGame();
                saveGames();
                saveRentals();
                return true;
            }
        }
        return false;
    }

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
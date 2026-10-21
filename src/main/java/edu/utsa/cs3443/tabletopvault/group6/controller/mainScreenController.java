package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Rental;
import edu.utsa.cs3443.tabletopvault.group6.model.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Primary application controller coordinating checkout/return transactions,
 * staff directory access, and sub-controllers (GameController and CustomerController).
 */
public class mainScreenController {

    private final GameRentalManager manager;
    private final GameController gameController;
    private final CustomerController customerController;

    /**
     * Default constructor initializing a new GameRentalManager and loading all data files.
     */
    public mainScreenController() {
        this.manager = new GameRentalManager();
        this.manager.loadAllData();
        this.gameController = new GameController(this.manager);
        this.customerController = new CustomerController(this.manager);
    }

    /**
     * Constructs a mainScreenController with an existing GameRentalManager instance.
     *
     * @param manager pre-initialized GameRentalManager
     */
    public mainScreenController(GameRentalManager manager) {
        this.manager = manager;
        this.gameController = new GameController(manager);
        this.customerController = new CustomerController(manager);
    }

    /**
     * Reloads all flat files from the data/ directory.
     */
    public void reloadData() {
        manager.loadAllData();
    }

    /**
     * Coordinates checking out a board game to a registered patron.
     *
     * @param gameTitle    title of the game to check out
     * @param customerName full name of the customer
     * @return true if checkout succeeded, false otherwise
     */
    public boolean handleCheckout(String gameTitle, String customerName) {
        if (gameTitle == null || gameTitle.isBlank() || customerName == null || customerName.isBlank()) {
            return false;
        }
        return manager.checkoutGame(gameTitle, customerName);
    }

    /**
     * Coordinates returning a rented board game to the shelf.
     *
     * @param gameTitle    title of the game being returned
     * @param customerName full name of the customer returning the game
     * @return true if return succeeded, false otherwise
     */
    public boolean handleReturn(String gameTitle, String customerName) {
        if (gameTitle == null || gameTitle.isBlank() || customerName == null || customerName.isBlank()) {
            return false;
        }
        return manager.returnGame(gameTitle, customerName);
    }

    /**
     * Retrieves all active and historical rental records.
     *
     * @return ArrayList of Rental objects
     */
    public ArrayList<Rental> getAllRentals() {
        return manager.getRentals();
    }

    /**
     * Retrieves only currently active (unreturned) rentals.
     *
     * @return List of active Rental objects
     */
    public List<Rental> getActiveRentals() {
        return manager.getRentals().stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all staff members loaded from data/staff.txt.
     *
     * @return ArrayList of Staff objects
     */
    public ArrayList<Staff> getAllStaff() {
        return manager.getStaffList();
    }

    public GameController getGameController() {
        return gameController;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public GameRentalManager getManager() {
        return manager;
    }
}
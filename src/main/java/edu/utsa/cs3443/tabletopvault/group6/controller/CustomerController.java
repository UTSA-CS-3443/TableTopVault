package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;

import java.util.ArrayList;

/**
 * Controller class managing patron registration, profile updates, and lookups.
 * Enforces input validation before delegating persistence to GameRentalManager.
 */
public class CustomerController {

    private final GameRentalManager manager;

    /**
     * Constructs a CustomerController bound to the shared GameRentalManager.
     *
     * @param manager shared GameRentalManager instance
     */
    public CustomerController(GameRentalManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves all registered customers.
     *
     * @return ArrayList of Customer objects
     */
    public ArrayList<Customer> getAllCustomers() {
        return manager.getCustomers();
    }

    /**
     * Finds a specific customer by full name.
     *
     * @param fullName customer name to look up
     * @return matching Customer or null
     */
    public Customer findCustomer(String fullName) {
        return manager.findCustomerByName(fullName);
    }

    /**
     * Validates and registers a new customer profile.
     *
     * @param fullName         customer's full name
     * @param email            customer's email address
     * @param phone            customer's phone number
     * @param preferredContact preferred contact method
     * @param notes            initial staff notes
     * @return true if registered, false if required fields are missing or customer already exists
     */
    public boolean registerCustomer(String fullName, String email, String phone, String preferredContact, String notes) {
        if (fullName == null || fullName.isBlank() || email == null || email.isBlank()) {
            return false;
        }
        if (manager.findCustomerByName(fullName) != null) {
            return false;
        }
        String cleanPhone = (phone == null || phone.isBlank()) ? "N/A" : phone.trim();
        String cleanPref = (preferredContact == null || preferredContact.isBlank()) ? "Email" : preferredContact.trim();
        String cleanNotes = (notes == null || notes.isBlank()) ? "None" : notes.trim();

        Customer customer = new Customer(fullName.trim(), email.trim(), cleanPhone, cleanPref, cleanNotes);
        manager.addCustomer(customer);
        return true;
    }

    /**
     * Updates an existing customer's profile information.
     *
     * @param fullName         full name of the customer to update
     * @param email            updated email (or blank to keep current)
     * @param phone            updated phone (or blank to keep current)
     * @param preferredContact updated preferred contact (or blank to keep current)
     * @param notes            updated notes (or blank to keep current)
     * @return true if found and updated, false otherwise
     */
    public boolean updateCustomerProfile(String fullName, String email, String phone, String preferredContact, String notes) {
        return manager.updateCustomer(fullName, email, phone, preferredContact, notes);
    }

    /**
     * Removes a customer profile by full name.
     *
     * @param fullName full name of the customer to remove
     * @return true if removed, false if not found
     */
    public boolean removeCustomer(String fullName) {
        return manager.removeCustomer(fullName);
    }
}
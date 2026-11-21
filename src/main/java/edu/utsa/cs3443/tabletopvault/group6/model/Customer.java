package edu.utsa.cs3443.tabletopvault.group6.model;

/**
 * Customer class representing library patrons who borrow board games.
 * Extends the abstract Person base class and tracks preferred contact methods and staff notes.
 */
public class Customer extends Person {


    private String preferredContact;
    private String customerNotes;

    /**
     *
     * @param fullName full name of customer
     * @param email email of customer
     * @param phoneNumber phone number of customer
     * @param preferredContact customers preferred contact
     * @param customerNotes notes about customer
     */

    public Customer(String fullName, String email, String phoneNumber, String preferredContact, String customerNotes) {
        super(fullName, email, phoneNumber);
        this.preferredContact = preferredContact;
        this.customerNotes = customerNotes;
    }

    /**
     * preferred contact method for customer
     * @return preferred contact
     */

    public String getPreferredContact() {
        return preferredContact;
    }

    /**
     * sets preferred contact info
     * @param preferredContact
     */

    public void setPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    /**
     *
     * @return customer notes
     */

    public String getCustomerNotes() {
        return customerNotes;
    }

    /**
     *
     * @param customerNotes
     */
    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    /**
     *  Returns the customer's information in a formatted string.
     *  Aligns the full name, email, phone number, preferred contact and customer notes
     * @return a formatted string containing the game's details
     */

    @Override
    public String toString() {
        return String.format("%-18s | %-25s | %-15s | %-18s | %s", getFullName(), getEmail(), getPhoneNumber(), preferredContact, customerNotes);
    }

}
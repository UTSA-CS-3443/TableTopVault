package edu.utsa.cs3443.tabletopvault.group6.model;

/**
 * Model class representing a registered library patron who can rent board games.
 * Extends Person to inherit shared contact attributes while adding contact preferences and staff notes.
 */
public class Customer extends Person {

    private String preferredContact;
    private String customerNotes;

    /**
     * Constructs a Customer instance.
     *
     * @param fullName         the customer's full name
     * @param email            the customer's email address
     * @param phoneNumber      the customer's phone number
     * @param preferredContact preferred communication method (e.g., Email, Phone, Text)
     * @param customerNotes    staff notes regarding the customer's account or preferences
     */
    public Customer(String fullName, String email, String phoneNumber, String preferredContact, String customerNotes) {
        super(fullName, email, phoneNumber);
        this.preferredContact = preferredContact;
        this.customerNotes = customerNotes;
    }

    /**
     * Gets the customer's preferred contact method.
     *
     * @return preferred contact method
     */
    public String getPreferredContact() {
        return preferredContact;
    }

    /**
     * Updates the customer's preferred contact method.
     *
     * @param preferredContact new preferred contact method
     */
    public void setPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    /**
     * Gets staff notes associated with the customer.
     *
     * @return customer notes string
     */
    public String getCustomerNotes() {
        return customerNotes;
    }

    /**
     * Updates staff notes associated with the customer.
     *
     * @param customerNotes new customer notes string
     */
    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    /**
     * Formats the customer record for console display.
     *
     * @return formatted customer string
     */
    @Override
    public String toString() {
        return getFullName() + " | " + getEmail() + " | " + getPhoneNumber()
                + " | Pref: " + preferredContact + " | Notes: " + customerNotes;
    }
}
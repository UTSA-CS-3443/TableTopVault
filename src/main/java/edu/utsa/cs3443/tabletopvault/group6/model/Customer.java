package edu.utsa.cs3443.tabletopvault.group6.model;

/**
 * Customer class representing library patrons who borrow board games.
 * Extends the abstract Person base class and tracks preferred contact methods and staff notes.
 */
public class Customer extends Person {

    private String preferredContact;
    private String customerNotes;

    public Customer(String fullName, String email, String phoneNumber, String preferredContact, String customerNotes) {
        super(fullName, email, phoneNumber);
        this.preferredContact = preferredContact;
        this.customerNotes = customerNotes;
    }

    public String getPreferredContact() {
        return preferredContact;
    }

    public void setPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }





    @Override
    public String toString() {
        return String.format("%-22s | %-30s | %-14s | %-17s | %s", getFullName(), getEmail(), getPhoneNumber(), preferredContact, customerNotes);
    }
}



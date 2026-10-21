package edu.utsa.cs3443.tabletopvault.group6.model;

/**
 * Abstract base class representing a person associated with TableTop Vault.
 * Demonstrates OOP inheritance and encapsulation by storing shared contact details
 * for subclasses such as Customer and Staff.
 */
public abstract class Person {

    private String fullName;
    private String email;
    private String phoneNumber;

    /**
     * Constructs a Person with core contact details.
     *
     * @param fullName    the person's full name
     * @param email       the person's email address
     * @param phoneNumber the person's telephone number
     */
    public Person(String fullName, String email, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the person's full name.
     *
     * @return full name string
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Updates the person's full name.
     *
     * @param fullName new full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the person's email address.
     *
     * @return email address string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the person's email address.
     *
     * @param email new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the person's phone number.
     *
     * @return phone number string
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the person's phone number.
     *
     * @param phoneNumber new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a formatted string of the person's core contact details.
     *
     * @return formatted contact string
     */
    @Override
    public String toString() {
        return fullName + " | " + email + " | " + phoneNumber;
    }
}


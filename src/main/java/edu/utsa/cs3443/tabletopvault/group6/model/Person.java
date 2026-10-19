package edu.utsa.cs3443.tabletopvault.group6.model;

public abstract class Person {

    //Fields
    /*if we want to add username change functionality, not needed
    private final int id;
     */
    private String fullName;
    private String email;
    private String phoneNumber;
    private String preferredContact;
    private String preferredContactEmail;


    //Constructor
    protected Person (String fullName, String email, String phoneNumber, String preferredContact, String preferredContactEmail ) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferredContact = preferredContact;
        this.preferredContactEmail = preferredContactEmail;
    }
    //Getters
    public String getFullName() {return fullName;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getPreferredContact() {return preferredContact;}
    public String getPreferredContactEmail() {return preferredContactEmail;}

    //Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPreferredContact(String preferredContact) {
            this.preferredContact = preferredContact;
    }
    public void setPreferredContactEmail(String preferredContactEmail) {
        this.preferredContactEmail = preferredContactEmail;
    }
}


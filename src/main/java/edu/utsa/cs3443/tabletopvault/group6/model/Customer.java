package edu.utsa.cs3443.tabletopvault.group6.model;

public class Customer {
//fields
    private String fullName;
    private String email;
    private String phoneNumber;
    private String preferredContact;
    private String customerNotes;


    //constructor
    public Customer(String fullName, String email, String phoneNumber, String preferredContact, String customerNotes) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferredContact = preferredContact;
        this.customerNotes = customerNotes;
    }

    //getters
    public String getFullName() {return fullName;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getPreferredContact() {return preferredContact;}
    public String getCustomerNotes() {return customerNotes;}


    //setters
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
    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }





    //@Override










}

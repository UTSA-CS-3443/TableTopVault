package edu.utsa.cs3443.tabletopvault.group6.model;

public class Customer extends Person {

    //fields
    private String customerNotes;

    //constructor
    public Customer(String fullName, String email, String phoneNumber,
                    String preferredContact, String preferredContactEmail,
                    String customerNotes) {
        //From Person class
        super(fullName, email, phoneNumber,
                preferredContact, preferredContactEmail);

        this.customerNotes = customerNotes;
    }
//getters
    public String getCustomerNotes() {
        return customerNotes;
    }
//setters
    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }
}



    //@Override












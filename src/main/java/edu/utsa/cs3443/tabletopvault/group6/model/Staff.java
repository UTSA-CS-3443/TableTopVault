package edu.utsa.cs3443.tabletopvault.group6.model;

public class Staff extends Person {


    private String passwordHash;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String notes;
    public Staff(String fullName, String email, String phoneNumber, String notes, String passwordHash) {

        //From Person class
        super(fullName, email, phoneNumber);
        this.notes = notes;
        this.passwordHash = passwordHash;
    }


}

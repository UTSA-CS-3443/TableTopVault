package edu.utsa.cs3443.tabletopvault.group6.model;

public class Customer extends Person {

    //customer specific fields
    private String preferredContact;
    private String notes;


    //constructor
    public Customer(String fullName, String email, String phoneNumber,
                    String preferredContact, String notes) {
        //From Person class
        super(fullName, email, phoneNumber);
        this.preferredContact = preferredContact;
        this.notes = notes;

    }
//getters
    public String getPreferredContact() { return preferredContact; }
    public String getNotes() {return notes;}
//setters
    public void setPreferredContact(String preferredContact) { this.preferredContact = preferredContact; }
    public void setNotes(String notes) {
        this.notes = notes;
    }


@Override
public String toString() {
    return getFullName() + " | "
            + getEmail() + " | "
            + getPhoneNumber() + " | "
            + getPreferredContact() + " | "
            + getNotes();

}
}




    //@Override












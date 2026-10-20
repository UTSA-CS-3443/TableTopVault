
package edu.utsa.cs3443.tabletopvault.group6.model;

import java.time.LocalDate;

public class Rental {


        // Fields
        private Game gameTitle;
        private Customer customer;
        private LocalDate checkoutDate;
        private LocalDate returnDate;
        private boolean returned;

        // Constructor
        public Rental(Game gameTitle, Customer customer) {
            this.gameTitle = gameTitle;
            this.customer = customer;
            this.checkoutDate = LocalDate.now();
            this.returnDate = null;
            this.returned = false;
        }



    // Record when the game is returned
    public void returnGameTitle() {
        if (!returned) {
            this.returnDate = LocalDate.now();
            this.returned = true;
        }
    }



    // Getters
    public String getGameTitle() {
        return gameTitle.getGameTitle();
    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public boolean isReturned() {
        return returned;
    }





    public void setRentalDates(LocalDate checkoutDate, LocalDate returnDate, boolean returned) {
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }
    // Display rentals.txt information

    @Override
    public String toString() {

        String returnStatus;

        if (getReturnDate() == null) {
            returnStatus = "Not returned";
        } else {
            returnStatus = getReturnDate().toString();
        }

        return getGameTitle() + " | " +
                getCustomer().getFullName() + " | " +
                getCheckoutDate() + " | " +
                returnStatus + " | " +
                isReturned();
    }



}

//boolean
    //add a new game

    //delete a game
    //add a customer
    //update customer info
    //delete a customer
    //record a checkout
    //show details of an in dividual rentals.txt
    //handle checkout screen interaction



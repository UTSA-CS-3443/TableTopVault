package edu.utsa.cs3443.tabletopvault.group6.model;

import java.time.LocalDate;

/**
 * Rental class modeling the relationship between a Game and a Customer.
 *
 * Java Notes:
 * - Utilizes java.time.LocalDate for robust, modern date handling during checkouts and returns.
 * - Maintains object associations (aggregation) by holding direct references to Game and Customer instances.
 */
public class Rental {

    private Game game;
    private Customer customer;
    private LocalDate checkoutDate;
    private LocalDate returnDate;
    private boolean returned;

    public Rental(Game game, Customer customer) {
        this.game = game;
        this.customer = customer;
        this.checkoutDate = LocalDate.now();
        this.returnDate = null;
        this.returned = false;
    }

    public Rental(Game game, Customer customer, LocalDate checkoutDate, LocalDate returnDate, boolean returned) {
        this.game = game;
        this.customer = customer;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public void returnGame() {
        this.returnDate = LocalDate.now();
        this.returned = true;
        if (this.game != null) {
            this.game.setAvailable(true);
        }
    }

    public Game getGame() { return game; }
    public Customer getCustomer() { return customer; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }

    public String toCsvString() {
        String returnStr = (returnDate == null) ? "Not returned" : returnDate.toString();
        return game.getGameTitle() + "," + customer.getFullName() + "," +
                checkoutDate + "," + returnStr + "," + returned;
    }

    @Override
    public String toString() {
        String returnStr = (returnDate == null) ? "Not returned" : returnDate.toString();
        return "Game: " + game.getGameTitle() +
                "\nCustomer: " + customer.getFullName() +
                "\nCheckout Date: " + checkoutDate +
                "\nReturn Date: " + returnStr +
                "\nReturned: " + returned;
    }
}
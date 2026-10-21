package edu.utsa.cs3443.tabletopvault.group6.model;

import java.time.LocalDate;

/**
 * Model class representing a board game rental transaction.
 * Demonstrates object composition by linking a Game object and a Customer object
 * with checkout and return timestamps.
 */
public class Rental {

    private Game game;
    private Customer customer;
    private LocalDate checkoutDate;
    private LocalDate returnDate;
    private boolean returned;

    /**
     * Constructs a new active Rental starting today.
     *
     * @param game     the Game being checked out
     * @param customer the Customer borrowing the game
     */
    public Rental(Game game, Customer customer) {
        this.game = game;
        this.customer = customer;
        this.checkoutDate = LocalDate.now();
        this.returnDate = null;
        this.returned = false;
    }

    /**
     * Constructs a Rental record from persisted file data.
     *
     * @param game         the associated Game object
     * @param customer     the associated Customer object
     * @param checkoutDate date the rental was initiated
     * @param returnDate   date the rental was returned (or null if still active)
     * @param returned     true if the game has been returned, false otherwise
     */
    public Rental(Game game, Customer customer, LocalDate checkoutDate, LocalDate returnDate, boolean returned) {
        this.game = game;
        this.customer = customer;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    /**
     * Marks the rental as returned today and restores the game's shelf availability.
     */
    public void returnGame() {
        this.returned = true;
        this.returnDate = LocalDate.now();
        if (this.game != null) {
            this.game.setAvailable(true);
        }
    }

    /**
     * Serializes the rental record into a comma-separated line for data/rentals.txt.
     *
     * @return CSV representation of the rental
     */
    public String toCsvString() {
        String retDateStr = (returnDate == null) ? "Not returned" : returnDate.toString();
        return game.getGameTitle() + "," + customer.getFullName() + ","
                + checkoutDate + "," + retDateStr + "," + returned;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    /**
     * Formats the rental transaction for console display.
     *
     * @return formatted rental string
     */
    @Override
    public String toString() {
        String retStatus = (returnDate == null) ? "Not returned" : returnDate.toString();
        return "Game: " + game.getGameTitle() + " | Patron: " + customer.getFullName()
                + " | Checked Out: " + checkoutDate + " | Returned: " + retStatus
                + " | Completed: " + returned;
    }
}
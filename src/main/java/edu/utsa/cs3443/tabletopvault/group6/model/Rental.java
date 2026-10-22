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

    /**
     *
     * @param game Game title
     * @param customer customer name
     */

    public Rental(Game game, Customer customer) {
        this.game = game;
        this.customer = customer;
        this.checkoutDate = LocalDate.now();
        this.returnDate = null;
        this.returned = false;
    }

    /**
     *
     * @param game game title
     * @param customer full name
     * @param checkoutDate date checked out
     * @param returnDate date returned
     * @param returned confimation returned
     */

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

    /**
     *game rented
     * @return game
     */

    public Game getGame() { return game; }

    /*customer who returned game
     * @return customer name
     */
    public Customer getCustomer() { return customer; }

    /**
     * chckout date
     * @return checkout date
     */
    public LocalDate getCheckoutDate() { return checkoutDate; }

    /**
     * checks if returned
     * @return return date
     */
    public LocalDate getReturnDate() { return returnDate; }

    /**
     * confirms if returned
     * @return confirmation
     */
    public boolean isReturned() { return returned; }

    /**
     *
     * @return rental info to string
     */

    public String toCsvString() {
        String returnStr = (returnDate == null) ? "Not returned" : returnDate.toString();
        return game.getGameTitle() + "," + customer.getFullName() + "," +
                checkoutDate + "," + returnStr + "," + returned;
    }

    /**
     * a formatted string containing the rental's information in a string
     * Game title, full name, checkout date, return
     * @return
     */

    @Override
    public String toString() {
        String returnStr = (returnDate == null) ? "Not returned" : returnDate.toString();

        return String.format("%-18s | %-22s | %-13s | %-13s | %s", game.getGameTitle(), customer.getFullName(), checkoutDate, returnStr, returned);
    }
}
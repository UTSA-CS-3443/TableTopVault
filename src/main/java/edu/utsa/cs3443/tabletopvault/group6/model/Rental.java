package edu.utsa.cs3443.tabletopvault.group6.model;
import java.time.LocalDate;

public class Rental {

    private final String gamesDataFilename = "data/games";
    private Customer currentCustomer;
    private Game currentGame;


    //fields
    private Game game;
    private Customer customer;
    private LocalDate checkoutDate;
    private LocalDate returnDate;
    private boolean returned;




    //constructor
    public Rental(Game game, Customer customer) {
        this.game = game;
        this.customer = customer;
        this.checkoutDate = LocalDate.now();
        this.returned = false;
    }

    //constructor
    public void returnGame() {
        this.returnDate = LocalDate.now();
        this.returned = true;
    }

    //getters
    public Game getGame() {return game;}
    public Customer getCustomer() {return customer;}
    public boolean isReturned() {return returned;}

    //boolean
    //add a new game

    //delete a game
    //add a customer
    //update customer info
    //delete a customer
    //record a checkout
    //show details of an in dividual rental
    //handle checkout screen interaction
}


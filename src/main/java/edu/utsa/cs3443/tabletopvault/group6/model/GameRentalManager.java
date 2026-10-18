package edu.utsa.cs3443.tabletopvault.group6.model;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;

public class GameRentalManager {

    //array to hold customer
    private ArrayList<Customer> customers;

    //array to hold games
    private ArrayList<Game> games;
    //String customersDataFilename
    private final String customersDataFilename;
    // String gamesDataFilename
    private final String gamesDataFilename;




    private static GameRentalManager instance;





    //array
    GameRentalManager() {
        customers = new ArrayList<>();
        customersDataFilename = "data/customers.txt";
        games = new ArrayList<>();
        gamesDataFilename = "data/games.txt";
    }

    //instance
    public static GameRentalManager getInstance() {
        if (instance == null) {
            instance = new GameRentalManager();
            //instance.loadCustomersDataFromFile();   //need the method
           // instance.loadGamesDataFromFile();    //need the method
        }
        return instance;
    }

    /*//////////////////////////customer methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    loadCustomersDataFromFile()

    ArrayList<Customer> getCustomersList(return customers;) {}

     public Client findCustomer(String phoneNumber) {}
     public boolean isCustomerExists(String phoneNumber) {return findCustomer(phoneNumber) != null; }
     public boolean updateCustomer(Customer customer){}

     public boolean addNewCustomer(Customer customer){}
     public boolean deleteCustomer(Customer customer){}
     private void addCustomerLineToFile(String line){}
     private void saveCustomerDataToFile(){}




    ///////////////////////////////Game methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    loadGameDataFromFile()
     ArrayList<Customer> getCustomersList(return customers;) {}

     public Game findGame(String title) {}
     public boolean isGameExists(String title) {return findGame(title) != null; }
     public boolean updateGame(Game game){}

     public boolean addNewGame(Game game){}
     public boolean deleteGamer(Game game){}
     private void addGameLineToFile(String line){}
     private void saveGameDataToFile(){}

     */

    //game method









}

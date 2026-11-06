package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.util.List;

public class ListCustomersController {

    @FXML
    private Label totalCustomersLabel;

    @FXML
    private ListView<String> customersListView;

    private ObservableList<String> customers;
    private GameRentalManager gameRentalManager;



    @FXML
    void onShowCustomersListButtonClicked(ActionEvent event) {
        customers = FXCollections.observableArrayList();


        if(gameRentalManager.getCustomersList().size() == 0) {
            totalCustomersLabel.setText("No Customers found!");
            return;
        }
        totalCustomersLabel.setText("Number of Customers: " + gameRentalManager.getCustomersList().size());
        customers = FXCollections.observableArrayList();

        for(Customer customer : gameRentalManager.getCustomersList())
            customers.add(customer.getFullName());
        customersListView.setItems(customers);
    }






    @FXML
    private TableView<Customer> customersTableView;
     /*

    ////////CHANGE THIS FOR CUSTOMERS  /////////////////////////////////////
    /// /////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TableColumn<Game,String> titleColumn;

    @FXML
    private TableColumn<Game, Integer> minPlayersColumn;

    @FXML
    private TableColumn<Game, Integer> maxPlayersColumn;

    @FXML
    private TableColumn<Game, String> categoryColumn;

    @FXML
    private TableColumn<Game, Boolean> availableColumn;

    @FXML
    private TableColumn<Game, String> notesColumn;

    @FXML
    public void initialize() {
        titleColumn.setText("Game Title");
        minPlayersColumn.setText("Min Players");
        maxPlayersColumn.setText("Max Players");
        categoryColumn.setText("Category");
        availableColumn.setText("Available");
        notesColumn.setText("Notes");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

       minPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("minPlayers"));

        maxPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("maxPlayers"));

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        notesColumn.setCellValueFactory(new PropertyValueFactory<>("gameNotes"));
    }

     */




    public void setCustomers(List<Customer> customers) {
        customersTableView.setItems(FXCollections.observableArrayList(customers));
    }

}





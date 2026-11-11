package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


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
       // customers = FXCollections.observableArrayList();
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




    @FXML
    private TableColumn<Customer,String> fullNameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    @FXML
    private TableColumn<Customer, String> preferredContactColumn;

    @FXML
    private TableColumn<Customer, String> customerNotesColumn;



    @FXML
    public void initialize() {
        fullNameColumn.setText("Full Name");
        emailColumn.setText("Email");
        phoneNumberColumn.setText("Phone Number");
        preferredContactColumn.setText("Pref. Contact");
        customerNotesColumn.setText("Customer Notes");


        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

       emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        preferredContactColumn.setCellValueFactory(new PropertyValueFactory<>("preferredContact"));

        customerNotesColumn.setCellValueFactory(new PropertyValueFactory<>("customerNotes"));


    }









    public void setCustomers(List<Customer> customers) {
        customersTableView.setItems(FXCollections.observableArrayList(customers));
    }

}





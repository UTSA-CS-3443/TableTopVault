package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controller for list-customers-screen.fxml.
 * Binds Customer model attributes to a JavaFX TableView showing all registered patrons.
 */
public class ListCustomersController {

    @FXML
    private Label totalCustomersLabel;

    @FXML
    private ListView<String> customersListView;

    private ObservableList<String> customers;
    private GameRentalManager gameRentalManager;

    @FXML
    private TableView<Customer> customersTableView;

    @FXML
    private TableColumn<Customer, String> fullNameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;

    @FXML
    private TableColumn<Customer, String> preferredContactColumn;

    @FXML
    private TableColumn<Customer, String> customerNotesColumn;

    /**
     * Populates the optional customer ListView summary if invoked.
     *
     * @param event button action event
     */
    @FXML
    void onShowCustomersListButtonClicked(ActionEvent event) {
        if (gameRentalManager.getCustomersList().isEmpty()) {
            totalCustomersLabel.setText("No Customers found!");
            return;
        }
        totalCustomersLabel.setText("Number of Customers: " + gameRentalManager.getCustomersList().size());
        customers = FXCollections.observableArrayList();
        for (Customer customer : gameRentalManager.getCustomersList()) {
            customers.add(customer.getFullName());
        }
        customersListView.setItems(customers);
    }

    /**
     * Configures table columns and binds them to Customer getter properties.
     */
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

    /**
     * Populates the TableView with the provided list of Customer objects.
     *
     * @param customers list of registered customers
     */
    public void setCustomers(List<Customer> customers) {
        customersTableView.setItems(FXCollections.observableArrayList(customers));
    }
}

package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

/**
 * Controller for delete-customers-screen.fxml.
 * Displays registered customers in a ListView with inline delete buttons for account removal.
 */
public class DeleteCustomersController {

    private ObservableList<Customer> customers;

    @FXML
    private ListView<Customer> customersListView;

    private GameRentalManager gameRentalManager;

    @FXML
    private Label deleteCustomerLabel;

    /**
     * Initializes the controller with the singleton GameRentalManager instance.
     *
     * @throws FileNotFoundException if data files cannot be accessed
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
    }

    /**
     * Populates the ListView with all customers and attaches a custom ListCell containing a delete button.
     *
     * @param event Show List button click event
     */
    @FXML
    void onShowListButtonClicked(ActionEvent event) {
        customers = FXCollections.observableArrayList(gameRentalManager.getCustomersList());
        customersListView.setItems(customers);

        customersListView.setCellFactory(lv -> new ListCell<Customer>() {
            private final Button myButton = new Button("delete");
            private final Label label = new Label();
            private final HBox hbox = new HBox(10, label, myButton);

            {
                myButton.setOnAction(e -> {
                    Customer customer = getItem();
                    if (customer != null) {
                        boolean removed = gameRentalManager.removeCustomer(customer.getFullName());
                        if (removed) {
                            customers.removeIf(c -> c.getFullName().equalsIgnoreCase(customer.getFullName()));
                            deleteCustomerLabel.setText("Deleted Customer: " + customer.getFullName());
                        } else {
                            deleteCustomerLabel.setText("Customer could not be deleted");
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    label.setText(customer.getFullName());
                    setText(null);
                    setGraphic(hbox);
                }
            }
        });
    }
}

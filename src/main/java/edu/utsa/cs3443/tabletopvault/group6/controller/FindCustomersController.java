package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

/**
 * Controller for find-customers-screen.fxml.
 * Looks up a registered patron by name and displays their contact profile and active rentals.
 */
public class FindCustomersController {

    @FXML
    private TextField customerNameTextField;

    @FXML
    private Label searchResultLabel;

    private GameRentalManager gameRentalManager;

    /**
     * Initializes the controller with the shared GameRentalManager instance.
     *
     * @throws FileNotFoundException if data files cannot be read
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
    }

    /**
     * Searches for a customer by full name and displays their profile and any active game checkouts.
     *
     * @param event Search button click event
     */
    @FXML
    void onFindCustomerButtonClicked(ActionEvent event) {
        String query = customerNameTextField.getText().trim();
        if (query.isEmpty()) {
            searchResultLabel.setText("Please enter a customer name.");
            return;
        }
        Customer c = gameRentalManager.findCustomerByName(query);
        if (c != null) {
            String activeRentals = gameRentalManager.getRentals().stream()
                    .filter(r -> !r.isReturned() && r.getCustomer().getFullName().equalsIgnoreCase(c.getFullName()))
                    .map(r -> r.getGame().getGameTitle() + " (since " + r.getCheckoutDate() + ")")
                    .collect(Collectors.joining(", "));
            if (activeRentals.isEmpty()) {
                activeRentals = "None";
            }
            searchResultLabel.setText(
                    "Name: " + c.getFullName() + "\n" +
                    "Email: " + c.getEmail() + "\n" +
                    "Phone: " + c.getPhoneNumber() + "\n" +
                    "Preferred Contact: " + c.getPreferredContact() + "\n" +
                    "Notes: " + c.getCustomerNotes() + "\n" +
                    "Active Rentals: " + activeRentals
            );
        } else {
            searchResultLabel.setText("No customer found matching \"" + query + "\".");
        }
    }
}

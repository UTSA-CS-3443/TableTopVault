package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

/**
 * Controller for update-customers-screen.fxml.
 * Enables staff to load an existing customer profile, edit contact details, and save changes.
 */
public class UpdateCustomersController {

    @FXML
    private TextField fullNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField preferredContactTextField;

    @FXML
    private TextArea customerNotesTextArea;

    @FXML
    private Label statusLabel;

    private GameRentalManager gameRentalManager;

    /**
     * Initializes the controller with the shared GameRentalManager instance.
     *
     * @throws FileNotFoundException if data files cannot be accessed
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
    }

    /**
     * Searches for a customer by full name and populates the form fields with their current data.
     *
     * @param event Load Profile button click event
     */
    @FXML
    void onLoadCustomerButtonClicked(ActionEvent event) {
        String name = fullNameTextField.getText().trim();
        Customer c = gameRentalManager.findCustomerByName(name);
        if (c == null) {
            statusLabel.setText("Customer not found: " + name);
            return;
        }
        emailTextField.setText(c.getEmail());
        phoneNumberTextField.setText(c.getPhoneNumber());
        preferredContactTextField.setText(c.getPreferredContact());
        customerNotesTextArea.setText(c.getCustomerNotes());
        statusLabel.setText("Loaded: " + c.getFullName());
    }

    /**
     * Updates the customer's contact details and writes the changes to data/customers.txt.
     *
     * @param event Save Changes button click event
     */
    @FXML
    void onUpdateCustomerButtonClicked(ActionEvent event) {
        String name = fullNameTextField.getText().trim();
        boolean updated = gameRentalManager.updateCustomer(
                name,
                emailTextField.getText().trim(),
                phoneNumberTextField.getText().trim(),
                preferredContactTextField.getText().trim(),
                customerNotesTextArea.getText().trim()
        );
        if (updated) {
            statusLabel.setText("Updated Customer: " + name);
        } else {
            statusLabel.setText("Customer not found: " + name);
        }
    }
}

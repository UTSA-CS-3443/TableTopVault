package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

/**
 * Controller for add-new-customers-screen.fxml.
 * Validates patron registration details and saves new Customer records to data/customers.txt.
 */
public class AddNewCustomersController {

    @FXML
    private TextField fullNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private CheckBox emailCheckBox;

    @FXML
    private CheckBox textCheckBox;

    @FXML
    private TextArea customerNotesTextArea;

    @FXML
    private TextArea gamesCheckedoutTextArea;

    @FXML
    private Button onAddCustomerClicked;

    @FXML
    private Label statusLabel;

    private GameRentalManager gameRentalManager;

    /**
     * Initializes the controller and connects to the shared GameRentalManager instance.
     *
     * @throws FileNotFoundException if data files cannot be read
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
        if (gamesCheckedoutTextArea != null) {
            gamesCheckedoutTextArea.setEditable(false);
            gamesCheckedoutTextArea.setText("New customer has no active checkouts.");
        }
    }

    /**
     * Validates form inputs, creates a new Customer, and persists to data/customers.txt.
     *
     * @param event Add Customer button click event
     */
    @FXML
    void handleAddCustomer(ActionEvent event) {
        String fullName = fullNameTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String phone = phoneNumberTextField.getText().trim();
        String notes = customerNotesTextArea.getText().trim();

        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            if (statusLabel != null) statusLabel.setText("Name, Email, and Phone are required.");
            return;
        }
        if (gameRentalManager.findCustomerByName(fullName) != null) {
            if (statusLabel != null) statusLabel.setText("Customer already exists!");
            return;
        }

        String pref = "Email";
        if (textCheckBox != null && textCheckBox.isSelected() && (emailCheckBox == null || !emailCheckBox.isSelected())) {
            pref = "Text";
        }
        if (notes.isEmpty()) {
            notes = "No comment";
        }

        Customer newCustomer = new Customer(fullName, email, phone, pref, notes);
        gameRentalManager.addCustomer(newCustomer);

        if (statusLabel != null) {
            statusLabel.setText("Added Customer: " + fullName);
        }
        fullNameTextField.clear();
        emailTextField.clear();
        phoneNumberTextField.clear();
        customerNotesTextArea.clear();
    }
}

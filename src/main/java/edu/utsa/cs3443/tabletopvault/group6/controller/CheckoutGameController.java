package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for checkout-game-screen.fxml.
 * Allows staff to select an available board game and a registered patron to record a checkout.
 */
public class CheckoutGameController {

    @FXML
    private ComboBox<String> availableGamesComboBox;

    @FXML
    private ComboBox<String> customersComboBox;

    @FXML
    private Label checkoutStatusLabel;

    private GameRentalManager gameRentalManager;

    /**
     * Initializes the checkout dialog and populates the available games and customers dropdowns.
     *
     * @throws FileNotFoundException if data files cannot be accessed
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
        refreshDropdowns();
    }

    /**
     * Refreshes the ComboBox options so only currently available games are selectable.
     */
    private void refreshDropdowns() {
        List<String> availableGames = gameRentalManager.getGames().stream()
                .filter(Game::isAvailable)
                .map(Game::getGameTitle)
                .collect(Collectors.toList());
        List<String> customerNames = gameRentalManager.getCustomers().stream()
                .map(Customer::getFullName)
                .collect(Collectors.toList());

        availableGamesComboBox.setItems(FXCollections.observableArrayList(availableGames));
        customersComboBox.setItems(FXCollections.observableArrayList(customerNames));
    }

    /**
     * Processes the board game checkout and updates both data/games.txt and data/rentals.txt.
     *
     * @param event Complete Checkout button click event
     */
    @FXML
    void onCheckoutButtonClicked(ActionEvent event) {
        String selectedGame = availableGamesComboBox.getValue();
        String selectedCustomer = customersComboBox.getValue();

        if (selectedGame == null || selectedCustomer == null) {
            checkoutStatusLabel.setText("Please select both an available game and a customer.");
            return;
        }

        if (gameRentalManager.checkoutGame(selectedGame, selectedCustomer)) {
            checkoutStatusLabel.setText("Checked out \"" + selectedGame + "\" to " + selectedCustomer + "!");
            refreshDropdowns();
        } else {
            checkoutStatusLabel.setText("Checkout failed. Verify availability.");
        }
    }
}

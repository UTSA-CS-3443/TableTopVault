package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Rental;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for checkin-game-screen.fxml.
 * Displays active rentals for one-click game return processing alongside the full rental history log.
 */
public class CheckinGameController {

    @FXML
    private ComboBox<String> activeRentalsComboBox;

    @FXML
    private ListView<String> rentalHistoryListView;

    @FXML
    private Label checkinStatusLabel;

    private GameRentalManager gameRentalManager;

    /**
     * Initializes the check-in dialog and populates active rentals and rental history.
     *
     * @throws FileNotFoundException if data files cannot be accessed
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
        refreshRentals();
    }

    /**
     * Refreshes the active rental dropdown and full rental history ListView.
     */
    private void refreshRentals() {
        List<String> activeItems = gameRentalManager.getRentals().stream()
                .filter(r -> !r.isReturned())
                .map(r -> r.getGame().getGameTitle() + " — " + r.getCustomer().getFullName())
                .collect(Collectors.toList());
        activeRentalsComboBox.setItems(FXCollections.observableArrayList(activeItems));

        List<String> allHistory = gameRentalManager.getRentals().stream()
                .map(Rental::toString)
                .collect(Collectors.toList());
        rentalHistoryListView.setItems(FXCollections.observableArrayList(allHistory));
    }

    /**
     * Marks the selected active rental as returned, restores shelf availability, and saves to disk.
     *
     * @param event Process Return button click event
     */
    @FXML
    void onReturnGameButtonClicked(ActionEvent event) {
        String selected = activeRentalsComboBox.getValue();
        if (selected == null || !selected.contains(" — ")) {
            checkinStatusLabel.setText("Please select an active rental to return.");
            return;
        }
        String[] parts = selected.split(" — ", 2);
        String gameTitle = parts[0].trim();
        String customerName = parts[1].trim();

        if (gameRentalManager.returnGame(gameTitle, customerName)) {
            checkinStatusLabel.setText("Returned \"" + gameTitle + "\" from " + customerName + "!");
            refreshRentals();
        } else {
            checkinStatusLabel.setText("Could not process return.");
        }
    }
}

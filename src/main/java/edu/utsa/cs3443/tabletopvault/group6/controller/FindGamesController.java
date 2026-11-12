package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

/**
 * Controller for find-games-screen.fxml.
 * Searches the inventory by game title and displays availability, player count, and notes.
 */
public class FindGamesController {

    @FXML
    private Label searchResultLabel;

    @FXML
    private TextField gameNameTextField;

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
     * Executes a case-insensitive search by game title and displays the result.
     *
     * @param event Search button click event
     */
    @FXML
    void onFindButtonClicked(ActionEvent event) {
        String query = gameNameTextField.getText().trim();
        if (query.isEmpty()) {
            searchResultLabel.setText("Please enter a game title.");
            return;
        }
        Game game = gameRentalManager.findGameByTitle(query);
        if (game != null) {
            searchResultLabel.setText(
                    "Title: " + game.getGameTitle() + "\n" +
                    "Players: " + game.getMinPlayers() + " - " + game.getMaxPlayers() + "\n" +
                    "Category: " + game.getCategory() + "\n" +
                    "Available: " + (game.isAvailable() ? "Yes" : "No (Checked Out)") + "\n" +
                    "Notes: " + game.getGameNotes()
            );
        } else {
            searchResultLabel.setText("No game found matching \"" + query + "\".");
        }
    }
}

package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

/**
 * Controller for add-new-games-screen.fxml.
 * Validates staff input and adds a new board game to the inventory and data/games.txt.
 */
public class AddNewGamesController {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField minPlayersTextField;

    @FXML
    private TextField maxPlayersTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private Label statusLabel;

    private GameRentalManager gameRentalManager;

    /**
     * Connects the controller to the singleton GameRentalManager instance.
     *
     * @throws FileNotFoundException if data files cannot be loaded
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
    }

    /**
     * Validates form fields, creates a new Game object, and persists it to data/games.txt.
     *
     * @param event Save Game button click event
     */
    @FXML
    void onAddGameButtonClicked(ActionEvent event) {
        String title = titleTextField.getText().trim();
        String minStr = minPlayersTextField.getText().trim();
        String maxStr = maxPlayersTextField.getText().trim();
        String category = categoryTextField.getText().trim();
        String notes = notesTextArea.getText().trim();

        if (title.isEmpty() || minStr.isEmpty() || maxStr.isEmpty() || category.isEmpty()) {
            statusLabel.setText("Please fill in all required fields.");
            return;
        }
        if (gameRentalManager.findGameByTitle(title) != null) {
            statusLabel.setText("Game title already exists!");
            return;
        }

        try {
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);
            if (min <= 0 || max < min) {
                statusLabel.setText("Invalid player count range.");
                return;
            }
            if (notes.isEmpty()) {
                notes = "No comment";
            }
            gameRentalManager.addGame(new Game(title, min, max, category, true, notes));
            statusLabel.setText("Added: " + title);
            onClearButtonClicked(null);
        } catch (NumberFormatException e) {
            statusLabel.setText("Min and Max players must be numbers.");
        }
    }

    /**
     * Clears all input fields on the Add Game form.
     *
     * @param event Clear button click event
     */
    @FXML
    void onClearButtonClicked(ActionEvent event) {
        titleTextField.clear();
        minPlayersTextField.clear();
        maxPlayersTextField.clear();
        categoryTextField.clear();
        notesTextArea.clear();
    }
}

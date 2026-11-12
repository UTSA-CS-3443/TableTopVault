package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

/**
 * Controller for update-games-screen.fxml.
 * Allows staff to search for an existing game, modify its attributes, and persist changes to disk.
 */
public class UpdateGamesController {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField minPlayersTextField;

    @FXML
    private TextField maxPlayersTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private CheckBox availableCheckBox;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private Label statusLabel;

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
     * Searches for a game by title and populates the form controls with its current attributes.
     *
     * @param event Load Game button click event
     */
    @FXML
    void onLoadGameButtonClicked(ActionEvent event) {
        String title = titleTextField.getText().trim();
        Game game = gameRentalManager.findGameByTitle(title);
        if (game == null) {
            statusLabel.setText("Game not found: " + title);
            return;
        }
        minPlayersTextField.setText(String.valueOf(game.getMinPlayers()));
        maxPlayersTextField.setText(String.valueOf(game.getMaxPlayers()));
        categoryTextField.setText(game.getCategory());
        availableCheckBox.setSelected(game.isAvailable());
        notesTextArea.setText(game.getGameNotes());
        statusLabel.setText("Loaded: " + game.getGameTitle());
    }

    /**
     * Applies form modifications to the loaded Game and writes the updated inventory to data/games.txt.
     *
     * @param event Update Game button click event
     */
    @FXML
    void onUpdateGameButtonClicked(ActionEvent event) {
        String title = titleTextField.getText().trim();
        Game game = gameRentalManager.findGameByTitle(title);
        if (game == null) {
            statusLabel.setText("Game not found: " + title);
            return;
        }
        try {
            if (!minPlayersTextField.getText().isBlank()) {
                game.setMinPlayers(Integer.parseInt(minPlayersTextField.getText().trim()));
            }
            if (!maxPlayersTextField.getText().isBlank()) {
                game.setMaxPlayers(Integer.parseInt(maxPlayersTextField.getText().trim()));
            }
            if (!categoryTextField.getText().isBlank()) {
                game.setCategory(categoryTextField.getText().trim());
            }
            game.setAvailable(availableCheckBox.isSelected());
            if (!notesTextArea.getText().isBlank()) {
                game.setGameNotes(notesTextArea.getText().trim());
            }
            gameRentalManager.saveGames();
            statusLabel.setText("Updated: " + game.getGameTitle());
        } catch (NumberFormatException e) {
            statusLabel.setText("Player counts must be valid integers.");
        }
    }
}

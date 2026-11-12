package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
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
 * Controller for delete-games-screen.fxml.
 * Renders a ListView of games with inline Delete buttons to remove titles from the inventory.
 */
public class DeleteGamesController {

    private ObservableList<Game> games;

    @FXML
    private ListView<Game> gamesListView;

    private GameRentalManager gameRentalManager;

    @FXML
    private Label deleteGameLabel;

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
     * Populates the ListView with all games and attaches a custom cell factory with a Delete button.
     *
     * @param event Show List button click event
     */
    @FXML
    void onShowListButtonClicked(ActionEvent event) {
        games = FXCollections.observableArrayList(gameRentalManager.getGamesList());
        gamesListView.setItems(games);

        gamesListView.setCellFactory(lv -> new ListCell<Game>() {
            private final Button deleteButton = new Button("Delete");
            private final Label label = new Label();
            private final HBox hbox = new HBox(10, label, deleteButton);

            {
                deleteButton.setOnAction(e -> {
                    Game game = getItem();
                    if (game != null) {
                        boolean removed = gameRentalManager.removeGame(game.getGameTitle());
                        if (removed) {
                            games.removeIf(g -> g.getGameTitle().equalsIgnoreCase(game.getGameTitle()));
                            deleteGameLabel.setText("Deleted Game: " + game.getGameTitle());
                        } else {
                            deleteGameLabel.setText("Game could not be deleted.");
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Game game, boolean empty) {
                super.updateItem(game, empty);
                if (empty || game == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    label.setText(game.getGameTitle() + " (" + game.getCategory() + ")");
                    setText(null);
                    setGraphic(hbox);
                }
            }
        });
    }
}

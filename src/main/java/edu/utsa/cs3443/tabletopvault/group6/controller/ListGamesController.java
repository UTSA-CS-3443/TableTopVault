package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

import java.io.FileNotFoundException;

public class ListGamesController {

    @FXML
    private Label totalGamesLabel;

    @FXML
    private ListView<String> gamesListView;

    private ObservableList<String> games;
    private GameRentalManager gameRentalManager;



    @FXML
    void onShowGamesListButtonClicked(ActionEvent event) {

        if(gameRentalManager.getGamesList().size() == 0) {
            totalGamesLabel.setText("No games found!");
            return;
        }
        totalGamesLabel.setText("Number of Games: " + gameRentalManager.getGamesList().size());
        games = FXCollections.observableArrayList();
        for(Game game : gameRentalManager.getGamesList())
            games.add(game.getGameTitle());

        gamesListView.setItems(games);

    }




    ////////REPEAT THIS FOR CUSTOMERS IN ListCustomersController /////////////////////////////////////
    /// /////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<Game> gamesTableView;

    @FXML
    private TableColumn<Game,String> titleColumn;

    @FXML
    private TableColumn<Game, Integer> minPlayersColumn;

    @FXML
    private TableColumn<Game, Integer> maxPlayersColumn;

    @FXML
    private TableColumn<Game, String> categoryColumn;

    @FXML
    private TableColumn<Game, Boolean> availableColumn;

    @FXML
    private TableColumn<Game, String> notesColumn;

    @FXML
    public void initialize() {
        titleColumn.setText("Game Title");
        minPlayersColumn.setText("Min Players");
        maxPlayersColumn.setText("Max Players");
        categoryColumn.setText("Category");
        availableColumn.setText("Available");
        notesColumn.setText("Notes");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

       minPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("minPlayers"));

        maxPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("maxPlayers"));

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        notesColumn.setCellValueFactory(new PropertyValueFactory<>("gameNotes"));
    }


    public void setGames(List<Game> games) {

        gamesTableView.setItems(FXCollections.observableArrayList(games));

    }




}




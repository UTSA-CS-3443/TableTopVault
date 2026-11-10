




package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ListGamesController {

    @FXML
    private TableView<Game> gamesTableView;

    @FXML
    private TableColumn<Game, String> titleColumn;

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

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("gameTitle"));

        minPlayersColumn.setCellValueFactory(
                new PropertyValueFactory<>("minPlayers"));

        maxPlayersColumn.setCellValueFactory(
                new PropertyValueFactory<>("maxPlayers"));

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("category"));

        availableColumn.setCellValueFactory(
                new PropertyValueFactory<>("available"));

        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("gameNotes"));
    }

    public void setGames(List<Game> games) {
        gamesTableView.setItems(
                FXCollections.observableArrayList(games)
        );
    }
}

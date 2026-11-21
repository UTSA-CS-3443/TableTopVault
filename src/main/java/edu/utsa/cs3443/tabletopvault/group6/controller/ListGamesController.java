package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

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
        if (gamesTableView != null) {
            gamesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        }
        if (titleColumn != null) {
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        }
        if (minPlayersColumn != null) {
            minPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("minPlayers"));
        }
        if (maxPlayersColumn != null) {
            maxPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("maxPlayers"));
        }
        if (categoryColumn != null) {
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        }
        if (availableColumn != null) {
            availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        }
        if (notesColumn != null) {
            notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        }
    }

    public void setGames(ArrayList<Game> games) {
        if (gamesTableView != null && games != null) {
            gamesTableView.setItems(FXCollections.observableArrayList(games));
        }
    }
}

package edu.utsa.cs3443.tabletopvault.group6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListGamesController {

    @FXML
    private TableView<?> gamesTableView;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> minPlayersColumn;

    @FXML
    private TableColumn<?, ?> maxPlayersColumn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> availableColumn;

    @FXML
    private TableColumn<?, ?> notesColumn;

    @FXML
    public void initialize() {
        if (gamesTableView != null) {
            gamesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        }
    }
}

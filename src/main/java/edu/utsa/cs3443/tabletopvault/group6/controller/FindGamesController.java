package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class FindGamesController {
    @FXML
    private Label searchResultLabel;

    @FXML
    private TextField gameNameTextField;

    private GameRentalManager gameRentalManager;
    @FXML
    public void initialize() throws FileNotFoundException {gameRentalManager = GameRentalManager.getInstance();}

    /*
    @FXML
    void onFindButtonClicked(ActionEvent event) {
        String game

    }
    */

}

package edu.utsa.cs3443.tabletopvault.group6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ListCustomersController {

    @FXML
    private TableView<?> customersTableView;

    @FXML
    public void initialize() {
        if (customersTableView != null) {
            customersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        }
    }
}

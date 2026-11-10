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

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class DeleteGamesController {

   // CHANGE ALL OF THIS TO FROM CUSTOMER TO GAME////////////////////////////////////////////////////
/*//////////////////////////////////////////////////////////////////////////////////////////////////
    private ObservableList<Customer> customers;


    @FXML
    private ListView<Customer> customersListView;

    private GameRentalManager gameRentalManager;

    @FXML
    private Label deleteCustomerLabel;



    @FXML
    public void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
    }






    @FXML
    void onShowListButtonClicked(ActionEvent event) {
        customers = FXCollections.observableArrayList(gameRentalManager.getCustomersList());

        customersListView.setItems(customers);

        // TODO: explain new idea
        // public final void setCellFactory(Callback<ListView<T>, ListCell<T>> value)
        // So the parameter is a Callback interface.
        //     @FunctionalInterface
        //     public interface Callback<P, R> {
        //         R call(P param);
        //     }
        // In our example:
        // P = ListView<User>
        // R = ListCell<User>
        // Given a ListView<> lv, return a new ListCell<> instance that knows how to render each item.
        customersListView.setCellFactory(lv -> new ListCell<Customer>() {



            private final Button myButton = new Button("delete");

            {
                myButton.setOnAction(e -> {
                    Customer customer = getItem();
                    if(customer != null) {
                        boolean removed = gameRentalManager.removeCustomer(customer.getFullName());

                        if (removed) {
                            customers.removeIf(c -> c.getFullName().equalsIgnoreCase(customer.getFullName()));
                            deleteCustomerLabel.setText("Deleted Customer: " + customer.getFullName());
                        } else {
                            deleteCustomerLabel.setText("Customer could not be deleted");
                        }
                    }

                });
            }

            //private final ImageView imageView = new ImageView();
            private final Label label = new Label();
            private final HBox hbox = new HBox(10,  label, myButton);



            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);

                if (empty || customer == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    label.setText(customer.getFullName());
                    setText(null);
                    setGraphic(hbox);
                }
            }
            });
        }
        */


    }



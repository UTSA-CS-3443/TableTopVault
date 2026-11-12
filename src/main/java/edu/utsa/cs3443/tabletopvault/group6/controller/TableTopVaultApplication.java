package edu.utsa.cs3443.tabletopvault.group6.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX Application entry point for TableTop Vault.
 * Initializes the primary stage and loads the staff login and public catalog screen.
 */
public class TableTopVaultApplication extends Application {

    /**
     * Starts the JavaFX application stage with login-screen.fxml.
     *
     * @param stage primary application window
     * @throws Exception if the FXML resource cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                TableTopVaultApplication.class.getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/login-screen.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        // Pass the primary stage reference to LoginScreenController for scene transitions
        LoginScreenController loginController = fxmlLoader.getController();
        loginController.setStage(stage);

        stage.setTitle("TableTop Game Rental System!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

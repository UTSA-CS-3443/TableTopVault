package edu.utsa.cs3443.tabletopvault.group6.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TableTopVaultApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TableTopVaultApplication.class.getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/login-screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        LoginScreenController loginController = fxmlLoader.getController();
        loginController.setStage(stage);

        stage.setTitle("TableTop Game Rental System!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch(args);}
}

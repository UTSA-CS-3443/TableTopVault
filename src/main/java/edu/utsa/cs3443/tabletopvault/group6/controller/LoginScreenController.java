package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LoginScreenController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginIndicatorLabel;

    private GameRentalManager gameRentalManager;
    private Stage stage;

    public void setStage(Stage stage) {this.stage = stage;}




    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            loginIndicatorLabel.setText("Enter a valid username and password!");
            return;
        }
        Staff staff;
        try {
            staff = Staff.login(username, password);
        } catch (FileNotFoundException e) {

            {
                loginIndicatorLabel.setText("Unable to read staff.txt");
                return;
            }
        }
        if (staff == null) {
            loginIndicatorLabel.setText("Invalid username and/or password!");
            return;
        }
        login(staff);


    }

    private void login(Staff staff) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/main-screen.fxml"));
            gameRentalManager = GameRentalManager.getInstance();
            gameRentalManager.setCurrentStaff(staff);
            Scene scene = new Scene(fxmlLoader.load());

            MainScreenController mainScreenController = fxmlLoader.getController();
            mainScreenController.setMainStage(stage);

            stage.setScene(scene);
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


//////////////////////////////////////////////Loads GamesTable on login screen///////////////////////////////////////
    @FXML
    public void initialize() {
        loadGamesTable();
    }

        @FXML
        private BorderPane loginBorderPane;


   private void loadGamesTable() {


       try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/list-games-screen.fxml"));

           Parent gamesView = loader.load();
           ListGamesController controller = loader.getController();
           GameRentalManager manager = GameRentalManager.getInstance();
           controller.setGames(manager.getGames());
           loginBorderPane.setCenter(gamesView);


       } catch (Exception e) {
           e.printStackTrace();
       }
   }



}













































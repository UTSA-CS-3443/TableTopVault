package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controller for the login and public game catalog screen (login-screen.fxml).
 * Displays the current board game inventory to patrons and authenticates staff members.
 */
public class LoginScreenController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginIndicatorLabel;

    @FXML
    private BorderPane loginBorderPane;

    private GameRentalManager gameRentalManager;
    private Stage stage;

    /**
     * Sets the primary stage used to transition to the main dashboard after login.
     *
     * @param stage primary application stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes the login screen by embedding the read-only game catalog table in the center pane.
     */
    @FXML
    public void initialize() {
        loadGamesTable();
    }

    /**
     * Validates staff credentials against data/staff.txt when the Staff Login button is clicked.
     *
     * @param event button click action event
     */
    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            loginIndicatorLabel.setText("Enter a valid username and password!");
            return;
        }

        Staff staff;
        try {
            staff = Staff.login(username, password);
        } catch (FileNotFoundException e) {
            loginIndicatorLabel.setText("Unable to read staff.txt");
            return;
        }

        if (staff == null) {
            loginIndicatorLabel.setText("Invalid username and/or password!");
            return;
        }

        login(staff);
    }

    /**
     * Transitions the authenticated staff member to the main management dashboard (main-screen.fxml).
     *
     * @param staff authenticated Staff object
     */
    private void login(Staff staff) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/main-screen.fxml")
            );
            gameRentalManager = GameRentalManager.getInstance();
            gameRentalManager.setCurrentStaff(staff);

            Scene scene = new Scene(fxmlLoader.load());
            MainScreenController mainScreenController = fxmlLoader.getController();
            mainScreenController.setMainStage(stage);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads list-games-screen.fxml into the center of the login BorderPane.
     */
    private void loadGamesTable() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/list-games-screen.fxml")
            );
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

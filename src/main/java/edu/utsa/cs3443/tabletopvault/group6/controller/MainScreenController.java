package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Rental;
import edu.utsa.cs3443.tabletopvault.group6.model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Primary application controller coordinating checkout/return transactions,
 * staff directory access, and sub-controllers (GameController and CustomerController).
 */
public class MainScreenController {


    private final GameRentalManager manager;
    private final GameController gameController;
    private final CustomerController customerController;

    /**
     * Default constructor initializing a new GameRentalManager and loading all data files.
     */
    public MainScreenController() {
        this.manager = new GameRentalManager();
        this.manager.loadAllData();
        this.gameController = new GameController(this.manager);
        this.customerController = new CustomerController(this.manager);
    }

    /**
     * Constructs a mainScreenController with an existing GameRentalManager instance.
     *
     * @param manager pre-initialized GameRentalManager
     */
    public MainScreenController(GameRentalManager manager) {
        this.manager = manager;
        this.gameController = new GameController(manager);
        this.customerController = new CustomerController(manager);
    }

    /**
     * Reloads all flat files from the data/ directory.
     */
    public void reloadData() {
        manager.loadAllData();
    }

    /**
     * Coordinates checking out a board game to a registered patron.
     *
     * @param gameTitle    title of the game to check out
     * @param customerName full name of the customer
     * @return true if checkout succeeded, false otherwise
     */
    public boolean handleCheckout(String gameTitle, String customerName) {
        if (gameTitle == null || gameTitle.isBlank() || customerName == null || customerName.isBlank()) {
            return false;
        }
        return manager.checkoutGame(gameTitle, customerName);
    }

    /**
     * Coordinates returning a rented board game to the shelf.
     *
     * @param gameTitle    title of the game being returned
     * @param customerName full name of the customer returning the game
     * @return true if return succeeded, false otherwise
     */
    public boolean handleReturn(String gameTitle, String customerName) {
        if (gameTitle == null || gameTitle.isBlank() || customerName == null || customerName.isBlank()) {
            return false;
        }
        return manager.returnGame(gameTitle, customerName);
    }

    /**
     * Retrieves all active and historical rental records.
     *
     * @return ArrayList of Rental objects
     */
    public ArrayList<Rental> getAllRentals() {
        return manager.getRentals();
    }

    /**
     * Retrieves only currently active (unreturned) rentals.
     *
     * @return List of active Rental objects
     */
    public List<Rental> getActiveRentals() {
        return manager.getRentals().stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all staff members loaded from data/staff.txt.
     *
     * @return ArrayList of Staff objects
     */
    public ArrayList<Staff> getAllStaff() {
        return manager.getStaffList();
    }

    public GameController getGameController() {
        return gameController;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public GameRentalManager getManager() {
        return manager;
    }


    public GameRentalManager getGameRentalManager() {
        return gameRentalManager;
    }







    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////FXML/////////////////////////////////////////////////////////

private void launchScreen(String fxml, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/" + fxml + ".fxml"
            ));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.mainStage);
            stage.setTitle(title);
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void setMainStage(Stage stage) {this.mainStage = stage;}

    private void logout() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/login-screen.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());

            LoginScreenController loginController = fxmlLoader.getController();
            loginController.setStage(mainStage);
            mainStage.setScene(loginScene);
            gameRentalManager.setCurrentStaff(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
private Button handleButtonClicked;

    @FXML
    void setHandleButtonClicked(ActionEvent event) {

    }
    @FXML
    void onLogoutMenuButtonClick(ActionEvent event) {logout();}



    @FXML
    private BorderPane mainBorderPane;

    //@FXML
    // private Label welcomeLabel;
    private Stage mainStage;
    private GameRentalManager gameRentalManager;
    private Staff currentStaff;

    @FXML
    private void initialize() throws FileNotFoundException {

        gameRentalManager = GameRentalManager.getInstance();
        currentStaff = gameRentalManager.getCurrentStaff();
        loadGamesTable();

    }


    /////////////////////////////////////////////Customer Screens ///////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void onAddNewCustomersMenuItemClick(ActionEvent event) {launchScreen("add-new-customers-screen", "Add New Customer");}

    @FXML
    void onDeleteCustomersMenuItemClick(ActionEvent event) {launchScreen("delete-customers-screen", "Delete Customer");}

    @FXML
    void onFindCustomersMenuItemClick(ActionEvent event) {launchScreen("find-customers-screen", "Find Customer");}
    @FXML
    void onListCustomersMenuItemClick(ActionEvent event) {launchScreen("list-customers-screen", "List Customers");}
    @FXML
    void onUpdateCustomersMenuItemClick(ActionEvent event) {launchScreen("update-customers-screen", "Update Customer");}

    private void loadCustomersTable() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/list-customers-screen.fxml"));

            Parent CustomersView = loader.load();
            ListCustomersController controller = loader.getController();
            GameRentalManager manager = GameRentalManager.getInstance();
            controller.setCustomers(manager.getCustomers());
            mainBorderPane.setCenter(CustomersView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    ////////////////////////////////////////////Game Screens//////////////////////////////////////////////////////
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    void onAddNewGamesMenuItemClick(ActionEvent event) {launchScreen("add-new-games-screen", "Add New Game");}
    @FXML
    void onDeleteGamesMenuItemClick(ActionEvent event) {launchScreen("delete-games-screen", "Delete Game");}

    @FXML
    void onFindGamesMenuItemClick(ActionEvent event) {launchScreen("find-games-screen", "Find Game");}



    @FXML
     void onListGamesMenuItemClick(ActionEvent event) {launchScreen("list-games-screen", "List Games");}





    /*
    @FXML
    private void onListGamesMenuItemClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/list-games-screen.fxml"));

            Parent gameList = loader.load();

            ListGamesController controller = loader.getController();

            controller.setGames(gameRentalManager.getGames());

            mainBorderPane.setCenter(gameList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

*/


    @FXML
    void onUpdateGamesMenuItemClick(ActionEvent event) {launchScreen("update-games-screen", "Update Games");}


    @FXML
    private BorderPane loginBorderPane;



    private void loadGamesTable() {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/tabletopvault/group6/layouts/list-games-screen.fxml"));

            Parent gamesView = loader.load();
            ListGamesController controller = loader.getController();
            GameRentalManager manager = GameRentalManager.getInstance();
            controller.setGames(manager.getGames());
            mainBorderPane.setCenter(gamesView);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }














}
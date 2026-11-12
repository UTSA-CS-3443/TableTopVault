package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Rental;
import edu.utsa.cs3443.tabletopvault.group6.model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Primary dashboard controller (main-screen.fxml) coordinating navigation across
 * The Shelf, Customer Management, Checkout/Check-In dialogs, and Staff Logout.
 */
public class MainScreenController {

    private final GameRentalManager manager;
    private final GameController gameController;
    private final CustomerController customerController;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private MenuButton logoutMenu;

    private Stage mainStage;
    private GameRentalManager gameRentalManager;
    private Staff currentStaff;
    private boolean showingCustomers = false;

    /**
     * Default constructor initializing the controller and loading data.
     */
    public MainScreenController() {
        this.manager = new GameRentalManager();
        this.manager.loadAllData();
        this.gameController = new GameController(this.manager);
        this.customerController = new CustomerController(this.manager);
    }

    /**
     * Constructs MainScreenController with a pre-initialized GameRentalManager.
     *
     * @param manager shared GameRentalManager instance
     */
    public MainScreenController(GameRentalManager manager) {
        this.manager = manager;
        this.gameController = new GameController(manager);
        this.customerController = new CustomerController(manager);
    }

    /**
     * Initializes the dashboard, displays the logged-in staff member, and loads the game table.
     *
     * @throws FileNotFoundException if data files cannot be accessed
     */
    @FXML
    private void initialize() throws FileNotFoundException {
        gameRentalManager = GameRentalManager.getInstance();
        currentStaff = gameRentalManager.getCurrentStaff();
        if (currentStaff != null && logoutMenu != null && !currentStaff.getFullName().isBlank()) {
            logoutMenu.setText("Staff: " + currentStaff.getFullName().trim());
        }
        loadGamesTable();
    }

    /**
     * Opens a modal dialog window for a specific CRUD or rental action and refreshes
     * the center table once the dialog closes.
     *
     * @param fxml  name of the FXML file inside layouts/ (without extension)
     * @param title window title for the modal dialog
     */
    private void launchScreen(String fxml, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/edu/utsa/cs3443/tabletopvault/group6/layouts/" + fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.mainStage);
            stage.setTitle(title);
            stage.showAndWait();

            // Automatically refresh the active center table view after modal closes
            if (showingCustomers) {
                loadCustomersTable();
            } else {
                loadGamesTable();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the owning Stage reference for modal dialogs and logout navigation.
     *
     * @param stage primary application stage
     */
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    /**
     * Handles the Logout menu item click.
     *
     * @param event menu action event
     */
    @FXML
    void onLogoutMenuButtonClick(ActionEvent event) {
        logout();
    }

    /**
     * Clears the active staff session and returns the user to login-screen.fxml.
     */
    private void logout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/edu/utsa/cs3443/tabletopvault/group6/layouts/login-screen.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());
            LoginScreenController loginController = fxmlLoader.getController();
            loginController.setStage(mainStage);
            mainStage.setScene(loginScene);
            gameRentalManager.setCurrentStaff(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==================== CUSTOMER MENU HANDLERS ====================

    @FXML
    void onAddNewCustomersMenuItemClick(ActionEvent event) {
        launchScreen("add-new-customers-screen", "Add New Customer");
    }

    @FXML
    void onDeleteCustomersMenuItemClick(ActionEvent event) {
        launchScreen("delete-customers-screen", "Delete Customer");
    }

    @FXML
    void onFindCustomersMenuItemClick(ActionEvent event) {
        launchScreen("find-customers-screen", "Find Customer");
    }

    @FXML
    void onUpdateCustomersMenuItemClick(ActionEvent event) {
        launchScreen("update-customers-screen", "Update Customer");
    }

    @FXML
    private void onListCustomersMenuItemClick(ActionEvent event) {
        loadCustomersTable();
    }

    /**
     * Loads the customer directory table into the center pane of the dashboard.
     */
    private void loadCustomersTable() {
        try {
            showingCustomers = true;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/edu/utsa/cs3443/tabletopvault/group6/layouts/list-customers-screen.fxml"));
            Parent customersView = loader.load();
            ListCustomersController controller = loader.getController();
            controller.setCustomers(GameRentalManager.getInstance().getCustomers());
            mainBorderPane.setCenter(customersView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==================== GAME MENU HANDLERS ====================

    @FXML
    void onAddNewGamesMenuItemClick(ActionEvent event) {
        launchScreen("add-new-games-screen", "Add New Game");
    }

    @FXML
    void onDeleteGamesMenuItemClick(ActionEvent event) {
        launchScreen("delete-games-screen", "Delete Game");
    }

    @FXML
    void onFindGamesMenuItemClick(ActionEvent event) {
        launchScreen("find-games-screen", "Find Game");
    }

    @FXML
    void onUpdateGamesMenuItemClick(ActionEvent event) {
        launchScreen("update-games-screen", "Update Games");
    }

    @FXML
    private void onListGamesMenuItemClick(ActionEvent event) {
        loadGamesTable();
    }

    /**
     * Loads the board game inventory table into the center pane of the dashboard.
     */
    private void loadGamesTable() {
        try {
            showingCustomers = false;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/edu/utsa/cs3443/tabletopvault/group6/layouts/list-games-screen.fxml"));
            Parent gamesView = loader.load();
            ListGamesController controller = loader.getController();
            controller.setGames(GameRentalManager.getInstance().getGames());
            mainBorderPane.setCenter(gamesView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==================== CHECKOUT / CHECK-IN HANDLERS ====================

    @FXML
    void onCheckoutGameMenuItemClick(ActionEvent event) {
        launchScreen("checkout-game-screen", "Checkout Game");
    }

    @FXML
    void onCheckinGameMenuItemClick(ActionEvent event) {
        launchScreen("checkin-game-screen", "Check-In / Return Game");
    }

    // ==================== SERVICE DELEGATION HELPERS ====================

    public void reloadData() {
        manager.loadAllData();
    }

    public boolean handleCheckout(String gameTitle, String customerName) {
        if (gameTitle == null || gameTitle.isBlank() || customerName == null || customerName.isBlank()) {
            return false;
        }
        return manager.checkoutGame(gameTitle, customerName);
    }

    public boolean handleReturn(String gameTitle, String customerName) {
        if (gameTitle == null || gameTitle.isBlank() || customerName == null || customerName.isBlank()) {
            return false;
        }
        return manager.returnGame(gameTitle, customerName);
    }

    public ArrayList<Rental> getAllRentals() {
        return manager.getRentals();
    }

    public List<Rental> getActiveRentals() {
        return manager.getRentals().stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

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
}

package edu.utsa.cs3443.tabletopvault.group6.controller;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class managing board game catalog interactions (The Shelf & Game Intake).
 * Validates user inputs and coordinates CRUD actions with GameRentalManager.
 */
public class GameController {

    private final GameRentalManager manager;

    /**
     * Constructs a GameController bound to the shared GameRentalManager.
     *
     * @param manager shared GameRentalManager instance
     */
    public GameController(GameRentalManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves the complete list of games in the inventory.
     *
     * @return ArrayList of all Game objects
     */
    public ArrayList<Game> getAllGames() {
        return manager.getGames();
    }

    /**
     * Retrieves only the games that are currently available on the shelf.
     *
     * @return List of available Game objects
     */
    public List<Game> getAvailableGames() {
        return manager.getGames().stream()
                .filter(Game::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Searches for a game by title.
     *
     * @param title game title to search
     * @return matching Game object or null
     */
    public Game findGame(String title) {
        return manager.findGameByTitle(title);
    }

    /**
     * Validates parameters and adds a new board game to the collection.
     *
     * @param title      board game title
     * @param minPlayers minimum player count
     * @param maxPlayers maximum player count
     * @param category   game genre/category
     * @param available  initial shelf availability
     * @param notes      condition or component notes
     * @return true if valid and added, false if validation fails or title already exists
     */
    public boolean createGame(String title, int minPlayers, int maxPlayers, String category, boolean available, String notes) {
        if (title == null || title.isBlank() || minPlayers <= 0 || maxPlayers < minPlayers) {
            return false;
        }
        if (manager.findGameByTitle(title) != null) {
            return false;
        }
        String cleanCategory = (category == null || category.isBlank()) ? "General" : category.trim();
        String cleanNotes = (notes == null || notes.isBlank()) ? "None" : notes.trim();
        Game newGame = new Game(title.trim(), minPlayers, maxPlayers, cleanCategory, available, cleanNotes);
        manager.addGame(newGame);
        return true;
    }

    /**
     * Updates an existing game's availability status and notes.
     *
     * @param title     title of the game to update
     * @param available updated availability flag
     * @param notes     updated condition notes
     * @return true if updated, false if game was not found
     */
    public boolean updateGameAvailabilityAndNotes(String title, boolean available, String notes) {
        return manager.updateGameStatus(title, available, notes);
    }

    /**
     * Deletes a game from the inventory by title.
     *
     * @param title title of the game to remove
     * @return true if removed, false if not found
     */
    public boolean deleteGame(String title) {
        return manager.removeGame(title);
    }
}
package edu.utsa.cs3443.tabletopvault.group6.model;

/**
 * Model class representing a physical board game in the TableTop Vault collection.
 * Tracks player count limits, category, shelf availability, and condition notes.
 */
public class Game {

    private String gameTitle;
    private int minPlayers;
    private int maxPlayers;
    private String category;
    private boolean available;
    private String gameNotes;

    /**
     * Constructs a Game instance.
     *
     * @param gameTitle  the title of the board game
     * @param minPlayers minimum recommended players
     * @param maxPlayers maximum recommended players
     * @param category   board game genre/category
     * @param available  true if available for checkout, false if currently rented
     * @param gameNotes  condition or component notes
     */
    public Game(String gameTitle, int minPlayers, int maxPlayers, String category, boolean available, String gameNotes) {
        this.gameTitle = gameTitle;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.category = category;
        this.available = available;
        this.gameNotes = gameNotes;
    }

    /**
     * Gets the title of the board game.
     *
     * @return game title
     */
    public String getGameTitle() {
        return gameTitle;
    }

    /**
     * Sets the title of the board game.
     *
     * @param gameTitle new game title
     */
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    /**
     * Gets the minimum player count.
     *
     * @return minimum players
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Sets the minimum player count.
     *
     * @param minPlayers new minimum players
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Gets the maximum player count.
     *
     * @return maximum players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Sets the maximum player count.
     *
     * @param maxPlayers new maximum players
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Gets the game category/genre.
     *
     * @return category string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the game category/genre.
     *
     * @param category new category string
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Checks whether the game is currently available on the shelf.
     *
     * @return true if available, false if rented out
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Updates the shelf availability status of the game.
     *
     * @param available true if available, false if rented out
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets the staff/condition notes for the game.
     *
     * @return game notes string
     */
    public String getGameNotes() {
        return gameNotes;
    }

    /**
     * Updates the staff/condition notes for the game.
     *
     * @param gameNotes new game notes string
     */
    public void setGameNotes(String gameNotes) {
        this.gameNotes = gameNotes;
    }

    /**
     * Formats the game details for console output.
     *
     * @return formatted game string
     */
    @Override
    public String toString() {
        String status = available ? "Available" : "Checked Out";
        return gameTitle + " (" + minPlayers + "-" + maxPlayers + " players) | Category: "
                + category + " | Status: " + status + " | Notes: " + gameNotes;
    }
}
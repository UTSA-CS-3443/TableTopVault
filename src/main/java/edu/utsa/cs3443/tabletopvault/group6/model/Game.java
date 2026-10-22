package edu.utsa.cs3443.tabletopvault.group6.model;

public class Game {


    //fields
    private String gameTitle;
    private int minPlayers;
    private int maxPlayers;
    private String category;
    private boolean available;
    private String gameNotes;

    /**
     *
     * @param gameTitle name of game
     * @param minPlayers min num of players
     * @param maxPlayers max number of players
     * @param category type of game
     * @param available if its still available
     * @param gameNotes notes pertaining to game
     */

    //constructor
    public Game(String gameTitle, int minPlayers, int maxPlayers, String category, boolean available, String gameNotes) {
        this.gameTitle = gameTitle;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.category = category;
        this.available = available;
        this.gameNotes =  gameNotes;

    }


    //getters

    /**
     * Game info
     * @return game title
     */
    public String getGameTitle() {return gameTitle;}

    /**
     * Game info
     * @return min players
     */
    public int getMinPlayers() {return minPlayers;}

    /**
     * Game info
     * @return max players
     */
    public int getMaxPlayers() {return maxPlayers;}

    /**
     * Game info
     * @return category
     */
    public String getCategory() {return category;}

    /**
     * Game info
     * @return boolean available
     */
    public boolean isAvailable() {return available;}

    /**
     * Game info
     * @return game notes
     */
    public String getGameNotes() {return gameNotes;}




    //setters
    public void setGameTitle(String gameTitle) {this.gameTitle = gameTitle;}
    public void setMinPlayers(int minPlayers) {this.minPlayers = minPlayers;}
    public void setMaxPlayers(int maxPlayers) {this.maxPlayers = maxPlayers;}
    public void setCategory(String category) {this.category = category;}
    public void setAvailable(boolean available) {this.available = available;}
    public void setGameNotes(String gameNotes) {this.gameNotes = gameNotes;}



    /**
     * Returns the game's information in a formatted string.
     * Aligns the game title, minimum and maximum players,category, availability and notes
     *
     *
     * @return a formatted string containing the game's details
     */
    @Override
    public String toString() {
        return String.format("%-18s | %-11d | %-11d | %-12s | %-9s | %s", getGameTitle(), getMinPlayers(), getMaxPlayers(), getCategory(), isAvailable(), getGameNotes());
    }
}

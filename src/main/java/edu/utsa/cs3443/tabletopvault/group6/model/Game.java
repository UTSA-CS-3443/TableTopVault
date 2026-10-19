package edu.utsa.cs3443.tabletopvault.group6.model;

public class Game {

    //fields
    private String gameTitle;
    private int minPlayers;
    private int maxPlayers;
    private String category;
    private boolean available;
    private String gameNotes;



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
    public String getGameTitle() {return gameTitle;}
    public int getMinPlayers() {return minPlayers;}
    public int getMaxPlayers() {return maxPlayers;}
    public String getCategory() {return category;}
    public boolean isAvailable() {return available;}
    public String getGameNotes() {return gameNotes;}




    //setters
    public void setGameTitle(String gameTitle) {this.gameTitle = gameTitle;}
    public void setMinPlayers(int minPlayers) {this.minPlayers = minPlayers;}
    public void setMaxPlayers(int maxPlayers) {this.maxPlayers = maxPlayers;}
    public void setCategory(String category) {this.category = category;}
    public void setAvailable(boolean available) {this.available = available;}
    public void setGameNotes(String gameNotes) {this.gameNotes = gameNotes;}


    @Override

    public String toString() {
        return getGameTitle() + " | " +
                getMinPlayers() + " | " +
                getMaxPlayers() + " | " +
                getCategory() + " | " +
                isAvailable() + " | " +
                getGameNotes();
    }
}

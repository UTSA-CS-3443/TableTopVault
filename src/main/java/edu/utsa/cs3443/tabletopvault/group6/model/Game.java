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

    //setters

    //@Override
}

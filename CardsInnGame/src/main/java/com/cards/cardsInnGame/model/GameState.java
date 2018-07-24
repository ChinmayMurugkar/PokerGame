package com.cards.cardsInnGame.model;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */
public class GameState {

    //lets create an arraylist where we store all the players objects
    //we have number of players as 4, we create 4 players when the event for player generation is created by controller
    private ArrayList<Player> players = new ArrayList<Player>();
    private int handsDealt;
    private final int  handSize = 5;
    private ArrayList<Player> winners = new ArrayList<Player>();
    private HashMap<Player, Integer> scoreMap = new HashMap<Player,Integer>();

    //scoremap getters and setters
    public HashMap<Player, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Player player, int score) {
        this.scoreMap.put(player,score);
    }


    public ArrayList<Player> getWinners() {
        return winners;
    }

    public void setWinners(Player player) {
        this.winners.add(player);
    }



    //creating a singleton pattern for the gamestate
    private static GameState instance = null;
    protected GameState() {
        // Exists only to defeat instantiation.
    }
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    private Deck deck;




    public int getHandSize() {
        return handSize;
    }


    public int getHandsDealt() {
        return handsDealt;
    }

    public void setHandsDealt(int handsDealt) {
        this.handsDealt = handsDealt;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


}

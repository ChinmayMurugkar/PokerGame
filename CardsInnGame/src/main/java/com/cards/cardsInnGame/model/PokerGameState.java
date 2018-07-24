package com.cards.cardsInnGame.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


public class PokerGameState {


    //keep track of winners
    private ArrayList<Player> winners = new ArrayList<Player>();
    //keep track of pokerscore
    private HashMap<Player, Integer> pokerScoreMap = new HashMap<Player,Integer>();
    private HashMap<String,Integer> gameWinMap = new HashMap<String, Integer>();

    //creating a singleton pattern for the PokerGameState
    private static PokerGameState instance = null;
    protected PokerGameState() {
        // Exists only to defeat instantiation.
    }
    public static PokerGameState getInstance() {
        if (instance == null) {
            instance = new PokerGameState();
        }
        return instance;
    }

    public HashMap<String, Integer> getGameWinMap() {
        return gameWinMap;
    }

    public void setGameWinMap(String ruleName) {
        if(!gameWinMap.containsKey(ruleName)){
            gameWinMap.put(ruleName,1);
        }
        else{
            gameWinMap.put(ruleName,gameWinMap.get(ruleName)+1);
        }
    }



    public ArrayList<Player> getWinners() {
        return winners;
    }

    public void setWinners(Player winner) {
        this.winners.add(winner);
    }

    public HashMap<Player, Integer> getPokerScoreMap() {
        return pokerScoreMap;
    }

    public void setPokerScoreMap(Player player, int score) {
        if(!pokerScoreMap.containsKey(player)){
            pokerScoreMap.put(player,score);
        }
        pokerScoreMap.put(player,pokerScoreMap.get(player)+1);
    }


}

package com.cards.cardsInnGame.model;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */
public class Player {

    private Hand hand ;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private String playerName ;

    public  Hand getHand() {
        return hand;
    }

    public  void setHand(Hand hand) {
        this.hand = hand;
    }



}


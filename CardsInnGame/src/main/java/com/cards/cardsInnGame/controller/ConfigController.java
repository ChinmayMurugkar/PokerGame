package com.cards.cardsInnGame.controller;



import com.cards.cardsInnGame.model.Deck;
import com.cards.cardsInnGame.model.GameState;
import com.cards.cardsInnGame.model.Hand;
import com.cards.cardsInnGame.model.Player;
import org.slf4j.Logger;

import java.util.ArrayList;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


public class ConfigController {

    //creating a singleton pattern for the ConfigController
    private static ConfigController instance = null;
    protected ConfigController() {
        // Exists only to defeat instantiation.
    }
    public static ConfigController getInstance() {
        if (instance == null) {
            instance = new ConfigController();
        }
        return instance;
    }

    public Deck deck = new Deck();
    GameState gameState = GameState.getInstance();

    public void configureGame(Logger LOGGER){

        LOGGER.info("creating a deck now");
        //if no deck has been created then we create a deck
        if(deck.getDeck() == null)
            deck.createDeck();              ///--------------Check out this part !

        LOGGER.info("setting the deck");

        //set the deck in the gamestate
        gameState.setDeck(deck);

        LOGGER.info("Assigning new Hands to our four players");
        //configuring new hands for each the player
        ArrayList<Player> players = gameState.getPlayers();
        for(Player player : players){
            player.setHand(new Hand());
        }

    }



}

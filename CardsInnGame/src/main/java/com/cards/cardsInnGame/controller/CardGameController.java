package com.cards.cardsInnGame.controller;


/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


import com.cards.cardsInnGame.model.GameState;
import com.cards.cardsInnGame.model.Hand;
import com.cards.cardsInnGame.model.Player;
import com.cards.cardsInnGame.model.PokerGameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class CardGameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardGameController.class);
    private int handsToDeal = 50;

    //creating a singleton pattern for the gamestate
    private static CardGameController instance = null;
    protected CardGameController() {
        // Exists only to defeat instantiation.
    }
    public static CardGameController getInstance() {
        if (instance == null) {
            instance = new CardGameController();
        }
        return instance;
    }

    ConfigController configController = ConfigController.getInstance();
    GameManager gameManager =GameManager.getInstance();
    GameState gameState = GameState.getInstance();
    PokerGameState pokerGameState = PokerGameState.getInstance();

    //method to control configurations and game start process
    //we will also create players here as we want the 4 players fixed for all the 50 games we will play
    public void startController() {

        LOGGER.info("configuring game");

        //configuring new players and adding them to the game state
        System.out.println("here");
        createNewPlayers(LOGGER);

        //lets configure the game
        configController.configureGame(LOGGER);

        LOGGER.info("starting game");
        //we will now deal the hands to the player
        gameManager.startGame(LOGGER,handsToDeal);

    }

    public void createNewPlayers(Logger LOGGER){
        ArrayList<Player> players = new ArrayList<Player>();
        //once this is done we will configure four players
        LOGGER.info("configure four players");
        for(int i=0; i<4; i++){
            Player player = new Player();
            Hand hand = new Hand();
            player.setHand(hand);
            players.add(player);
            int temp = i;
            temp++;
            player.setPlayerName("player"+temp);
            gameState.setScoreMap(player,0);
            pokerGameState.setPokerScoreMap(player,0);
        }
        //set players for the game and start configuring the game for the rest deck, cards and dealing hands
        gameState.setPlayers(players);

    }

}

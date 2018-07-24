package com.cards.cardsInnGame.controller;



import com.cards.cardsInnGame.model.Card;
import com.cards.cardsInnGame.model.GameState;
import com.cards.cardsInnGame.model.Player;
import com.cards.cardsInnGame.model.PokerGameState;
import com.cards.cardsInnGame.view.ShowScoreBoard;
import org.slf4j.Logger;

import java.util.ArrayList;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


public class GameManager {

    //creating a singleton pattern for the gamestate
    private static GameManager instance = null;
    protected GameManager() {
        // Exists only to defeat instantiation.
    }
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    GameState gameState = GameState.getInstance();
    PokerGameState pokerGameState = PokerGameState.getInstance();
    ConfigController configController = ConfigController.getInstance();

    ShowScoreBoard showScoreBoard = ShowScoreBoard.getInstance();


    public void startGame(Logger LOGGER,int handsToDeal){

        // System.out.println();
        //we will keep on dealing hands till the games dealt are 50
        while(gameState.getHandsDealt()<handsToDeal){

            gameState.setHandsDealt(gameState.getHandsDealt()+1);

            //shuffle the deck
            if(gameState.getHandsDealt()==1 || gameState.getHandsDealt()%10==0){
                LOGGER.info("Shuffling the deck now");
                gameState.getDeck().shuffleDeck();
            }

            //for every game we will deal the hands
            //to each player we will deal five cards
            //we have to deal 20 cards 5 each in a round robin manner, we get the instances of the four players from the deck
            ArrayList<Player> players = gameState.getPlayers();

            //lets make empty hands and deal cards in round robin fashion for the players
            LOGGER.info("dealing hands now");
            dealHands(LOGGER,players);

            //lets get all the hands and decide winner for the game
            LOGGER.info("deciding the winner now");
            GeneralRules generalRules = new GeneralRules();
            PokerRules pokerRules = new PokerRules();

            //Starting the poker game
            LOGGER.info("Start the poker game");
            Player player = generalRules.decideIfWinner(LOGGER);
            Player pokerWinner = pokerRules.decidePokerWinner(LOGGER);
            System.out.println("poker winner "+ pokerWinner.getPlayerName());

            LOGGER.info("updating the score for the player");
            //Updating the score
            if(player!=null){
                gameState.setWinners(player);
                gameState.setScoreMap(player,gameState.getScoreMap().get(player)+1);
                System.out.println(player);
            }

            //updating the scores for pokerwinner
            pokerGameState.setWinners(pokerWinner);
            pokerGameState.setPokerScoreMap(player,pokerGameState.getPokerScoreMap().get(player)+1);


            //after everything is done empty all the hands and put them back at the bottom of the deck
            LOGGER.info("Dealer taking back all the cards from players and putting them at the bottom of the deck");
            refillDeck();

            LOGGER.info("creating a new deck and replacing it with a new one");
            configController.configureGame(LOGGER);

        }

        //Lets show the score board
        LOGGER.info("Going to the scoreBoard to get all the scores");
        showScoreBoard.showScore();
    }

    //method to add cards to the players hands
    public void dealHands(Logger LOGGER, ArrayList<Player> players){
        int i=1;
        int k=0;
        //System.out.println(gameState.getDeck().getDeck().size());
        LOGGER.info("removing the cards and adding them to the players hand ");
        while(i<=20){
            for(Player player: players){
                Card removedCard = gameState.getDeck().getDeck().remove(i);        //we remove the cards
                //adding to players hand
                player.getHand().addCard(removedCard);
                i++;
            }
        }
    }

    //refill deck by all the cards in the hands at the bottom
    public void refillDeck(){
        int size = gameState.getDeck().getDeck().size();
        System.out.print("size before refill"+ size);
        for(Player player : gameState.getPlayers()){
            ArrayList<Card> hands = player.getHand().hand;
            gameState.getDeck().getDeck().addAll(hands);
        }

    }

}

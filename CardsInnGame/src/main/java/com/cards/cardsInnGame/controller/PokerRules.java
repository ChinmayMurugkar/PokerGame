package com.cards.cardsInnGame.controller;



import com.cards.cardsInnGame.model.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */

//this is the class to decide on the poker rules
public class PokerRules {

    //creating a singleton pattern for the gamestate
    private static PokerRules instance = null;
    protected PokerRules() {
        // Exists only to defeat instantiation.
    }
    public static PokerRules getInstance() {
        if (instance == null) {
            instance = new PokerRules();
        }
        return instance;
    }


    GameState gameState = GameState.getInstance();

    List<Player> winnerSet = new LinkedList<Player>();
    ArrayList<Player> players = gameState.getPlayers();

    //method to put the rules for all the hands decision
    public Player decidePokerWinner(Logger LOGGER){

        //lets get the rank stats for the game state that we currently have
        RankStats rankStats =new RankStats();
        GeneralRules generalRules = new GeneralRules();
        PokerGameState pokerGameState = PokerGameState.getInstance();

        rankStats.rankCount(gameState.getPlayers());

        int fourofRankSize = rankStats.fourOfRank.size();
        int threeOfRankSize = rankStats.threeOfRank.size();
        int twofRankSize = rankStats.twoOfRank.size();

        //lets see if we got RoyalFLush
        if (this.royalFlush())
        {
            pokerGameState.setGameWinMap("Royal Flush");
            LOGGER.info("Yay ! We reached the ROYAL FLUSH state");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);
        }

        //checking for straight flush
        if (this.straightFlush())
        {
            pokerGameState.setGameWinMap("Straight Flush");
            LOGGER.info("Awesome ! Its a straight");
            //System.out.println("staright flush player list "+ winnerSet.size() +" "+ winnerSet);
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);
        }

        //checking for four of rank
        if (this.fourOfaKind(fourofRankSize, rankStats.fourOfRank))
        {
            pokerGameState.setGameWinMap("Four Of A Kind");
            LOGGER.info("Four Of A Kind is good too");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);
        }

        //checking for three of rank
        if (this.fullHouse(threeOfRankSize, rankStats.threeOfRank))
        {
            pokerGameState.setGameWinMap("Full House");
            LOGGER.info("Full House !");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);
        }

        //checking for flush

        if (this.flush())
        {
            pokerGameState.setGameWinMap("Flush");
            LOGGER.info("Not a royal but it`s a FLUSH ! ");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);

        }

        //checking for straight
        if (this.straight())
        {
            pokerGameState.setGameWinMap("Straight");
            LOGGER.info("Straight cards");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);

        }

        //checking for three of rank
        if (this.threeOfAKindHand( threeOfRankSize, rankStats.threeOfRank))
        {
            pokerGameState.setGameWinMap("Three Of A Kind");
            LOGGER.info("Three of a kind cards");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);

        }

        //checking for two pairs
        if (this.twoPairs())
        {
            pokerGameState.setGameWinMap("Two pair");
            LOGGER.info("You have Two PAIRS !");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);

        }

        //checking for two ranks
        if (this.pair(twofRankSize, rankStats.twoOfRank))
        {
            pokerGameState.setGameWinMap("Pair");
            LOGGER.info("Pair");
            if(winnerSet.size()==1)
                return winnerSet.get(0);
            else
                return generalRules.resolveTie(winnerSet);

        }

        //checking for HIGH CARD
        LOGGER.info("Oh well, a high card");
        pokerGameState.setGameWinMap("High Card");
        return generalRules.highCard(players);

    }

    boolean royalFlush(){
        boolean flag = false;
        //for each player we get a hand and see if it is royal flush
        for(Player player:players){
            ArrayList<Card> hand = player.getHand().hand;
            Collections.sort(hand);              //just to be sure that we have sorted all the cards
            if(hand.get(4).getRank()== Rank.ACE && hand.get(3).getRank() == Rank.KING && hand.get(2).getRank() == Rank.QUEEN && hand.get(1).getRank()==Rank.JACK && hand.get(0).getRank()== Rank.TEN){
                winnerSet.add(player);
                flag = true;
            }

        }
        return flag;
    }

    boolean straightFlush(){
        boolean flag = false;
        //for each player we get a hand and see if it is royal flush
        for(Player player:players){
            ArrayList<Card> hand = player.getHand().hand;
            Collections.sort(hand);              //just to be sure that we have sorted all the cards
            int i =3;
            int previous = hand.get(4).getRank().getRankValue();
            Suit suit = hand.get(4).getSuit();
            //checking to see if it matches both suit and ranks in sequence
            while(i >=0){
                if(hand.get(i).getRank().getRankValue() != previous-1 && hand.get(i).getSuit() != suit){
                    break;
                }
                previous = previous-1;
                i--;
            }
            if(i==0){           //this means we checked all the cards and they are in sequence
                flag = true;
                player.getHand().highestMatchedCard = hand.get(4);
                winnerSet.add(player);
            }

        }
        return flag;
    }

    boolean fourOfaKind(int fourofRankSize,List<Player> fourOfRank){
        boolean flag = false;

        if(fourofRankSize >0){      //add all the players to the current set
            winnerSet.addAll(fourOfRank);
            flag = true;
        }
        return flag;
    }

    boolean fullHouse(int threeOfRankSize, List<Player> threeOfRank ){
        boolean flag = false;

        //lets get all the players out of three of rank list and see if rest of they got a pair

        return flag;
    }

    boolean flush(){
        boolean flag = false;
        //for each player we get a hand and see if it is royal flush
        for(Player player:players){
            ArrayList<Card> hand = player.getHand().hand;
            Collections.sort(hand);              //just to be sure that we have sorted all the cards
            int i =3;
            Suit previous = hand.get(4).getSuit();
            while(i >=0){
                if(hand.get(i).getSuit() != previous){
                    break;
                }
                i--;
            }
            if(i==0){           //this means we checked all the cards and they are in sequence
                flag = true;
                player.getHand().highestMatchedCard = hand.get(4);
                winnerSet.add(player);
            }

        }
        return flag;
    }

    boolean straight() {
        boolean flag = false;
        //for each player we get a hand and see if it is royal flush
        for (Player player : players) {
            ArrayList<Card> hand = player.getHand().hand;
            Collections.sort(hand);              //just to be sure that we have sorted all the cards
            int i = 3;
            int previous = hand.get(4).getRank().getRankValue();
            //checking to see if it matches both suit and ranks in sequence
            while (i >= 0) {
                if (hand.get(i).getRank().getRankValue() != previous - 1) {
                    break;
                }
                previous = previous - 1;
                i--;
            }
            if (i == 0) {           //this means we checked all the cards and they are in sequence
                flag = true;
                player.getHand().highestMatchedCard = hand.get(4);
                winnerSet.add(player);
            }
        }
        return flag;
    }

    boolean threeOfAKindHand(int threeOfRankSize, List<Player> threeOfRank){
        boolean flag = false;

        if(threeOfRankSize >0){      //add all the players to the current set
            winnerSet.addAll(threeOfRank);
            flag = true;
        }
        return flag;
    }

    boolean twoPairs(){
        boolean flag = false;

        for(Player player: players){
            int count = 0;
            for(int i = 1; i < 5; i++)
            {
                if (player.getHand().hand.get(i-1).getRank() == player.getHand().hand.get(i).getRank())
                {
                    count++;
                }
            }
            if(count ==2){
                winnerSet.add(player);
                flag = true;
                player.getHand().highestMatchedCard = player.getHand().hand.get(4);
            }
        }
        return flag;
    }

    boolean pair(int twofRankSize, List<Player> twoOfRank){
        boolean flag = false;

        if(twofRankSize >0){      //add all the players to the current set
            winnerSet.addAll(twoOfRank);
            flag = true;
        }
        return flag;
    }

}

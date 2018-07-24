package com.cards.cardsInnGame.controller;



import com.cards.cardsInnGame.model.Card;
import com.cards.cardsInnGame.model.GameState;
import com.cards.cardsInnGame.model.Player;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


/*This class is going to decide the winner
  we will decide for all four players if which one is winner. We will go in order adn check if which one is the winner and whenever we find winners we give back that player
 */


public class GeneralRules {

    GameState gameState = GameState.getInstance();

    //method to get the player and check if the player is winner
    public Player decideIfWinner(Logger LOGGER){

        RankStats rankStats =new RankStats();

        rankStats.rankCount(gameState.getPlayers());

        int fourofRankSize = rankStats.fourOfRank.size();
        int threeOfRankSize = rankStats.threeOfRank.size();
        int twofRankSize = rankStats.twoOfRank.size();

        //----------------------------------------------------------------------------------------
        //check if anyone has the 4 of rank
        if(fourofRankSize==1)
            return rankStats.fourOfRank.get(0);
        else if(rankStats.fourOfRank.size()>1) {
            return resolveTie(rankStats.fourOfRank);
        }

        //----------------------------------------------------------------------------------------
        //check three of rank
        if(threeOfRankSize==1)
            return rankStats.threeOfRank.get(0);
        else if(rankStats.threeOfRank.size()>1)
            return resolveTie(rankStats.threeOfRank);

        //----------------------------------------------------------------------------------------
        //check rule two of rank
        if(twofRankSize==1)
            return rankStats.twoOfRank.get(0);
        else if(rankStats.twoOfRank.size()>1)
            return resolveTie(rankStats.twoOfRank);

        //----------------------------------------------------------------------------------------
        //Here we are resolving the ties and the rank problem by mapping the card to player and sorting the first card of each player hand by rank and suit to get highest
        return highCard(gameState.getPlayers());


    }

    public Player resolveTie(List<Player> playerList){
        HashMap<Card, Player> map = new HashMap<Card,Player>();
        ArrayList<Card> list = new ArrayList<Card>();
        System.out.print(playerList);
        for(Player player: playerList){
            Card tempCard = player.getHand().highestMatchedCard ;
            if(tempCard != null){
                list.add(tempCard);
                map.put(tempCard,player);
            }
        }
        Collections.sort(list);
        System.out.print(list);
        return map.get(list.get(0));

    }


    public Player highCard(ArrayList<Player> players){
        HashMap<Card, Player> map = new HashMap<Card,Player>();
        ArrayList<Card> list = new ArrayList<Card>();
        for(Player player: players){
            Card tempCard = player.getHand().hand.get(0);
            list.add(tempCard);
            map.put(tempCard,player);
        }
        Collections.sort(list);
        return map.get(list.get(0));

    }

}

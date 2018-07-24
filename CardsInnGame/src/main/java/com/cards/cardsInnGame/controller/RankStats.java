package com.cards.cardsInnGame.controller;




import com.cards.cardsInnGame.model.Player;

import java.util.*;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */
public class RankStats {




    public List<Player> fourOfRank = new LinkedList<Player>();
    public List<Player> threeOfRank = new LinkedList<Player>();
    public List<Player> twoOfRank = new LinkedList<Player>();


    public void rankCount(ArrayList<Player> players){
        //lets go through each player to see which has how many ranks similar
        for(Player player : players) {
            int totalCount = 0;
            int sameCount = 0;


            //check if rank of four is true
            if (checkFourRank(player))
                continue;

            //check for rank of three is true
            if (checkThreeRank(player))
                continue;
            //check for two of rank
            checkTwoRank(player);
        }
    }

    //checking for  four rank same
    boolean checkFourRank(Player player){
        if(player.getHand().hand.get(0).getRank() == player.getHand().hand.get(3).getRank()){
            player.getHand().highestMatchedCard =player.getHand().hand.get(0);
            fourOfRank.add(player);
            return true;
        }
        if(player.getHand().hand.get(4).getRank() == player.getHand().hand.get(1).getRank()){
            player.getHand().highestMatchedCard =player.getHand().hand.get(4);
            fourOfRank.add(player);
            return true;
        }
        return false;
    }

    //checking for three ranks same
    boolean checkThreeRank(Player player){
        if(player.getHand().hand.get(0).getRank() == player.getHand().hand.get(2).getRank()){
            player.getHand().highestMatchedCard =player.getHand().hand.get(0);
            threeOfRank.add(player);
            return true;
        }
        if(player.getHand().hand.get(2).getRank() == player.getHand().hand.get(4).getRank()){
            player.getHand().highestMatchedCard =player.getHand().hand.get(4);
            threeOfRank.add(player);
            return true;
        }
        return false;
    }

    //checking for two ranks same
    public void checkTwoRank(Player player){
        int count=0;
        for(int i=1; i<5; i++){
            if(player.getHand().hand.get(i-1).getRank() == player.getHand().hand.get(i).getRank() ){
                player.getHand().highestMatchedCard = player.getHand().hand.get(i);
                count++;
                break;
            }
        }

        if(count==1)
            twoOfRank.add(player);

    }

}

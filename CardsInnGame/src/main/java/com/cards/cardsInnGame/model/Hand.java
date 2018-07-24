package com.cards.cardsInnGame.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


public class Hand {

    public ArrayList<Card> hand = new ArrayList<Card>();

    public Card highestMatchedCard = null;

    public void addCard(Card card){

        hand.add(card);
        sortList();
    }

    public void sortList(){
        Collections.sort(hand);
    }


}

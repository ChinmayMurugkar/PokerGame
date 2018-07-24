package com.cards.cardsInnGame.model;


import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Chinmay Murugkar on 10/15/17.
 */

public class Deck    {


    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    //we will create a deck of the type array list to keep the track of the cards
    private ArrayList<Card> deck;


    //we will create a deck whenever we instantiate a new deck. it should always have 52 cards present in it
    public void createDeck(){
        this.deck = new ArrayList<Card>();
        //creating deck according to the ranks of rank and suit
        for (int i=0; i<13; i++)
        {
            Rank rank = Rank.values()[i];
            for (int j=0; j<4; j++)
            {
                Card card = new Card(rank, Suit.values()[j]);
                this.deck.add(card);
            }
        }

    }


    //lets shuffle the cards in the deck
    public void  shuffleDeck(){
        Collections.shuffle(this.deck);
    }

    //This is created for testing purposes and any of the other places required in view
    public String readDeck(){
        String deckString ="";

        for (Card card : deck) {
            deckString = deckString + card.getRank() + " of " + card.getSuit()+", ";
        }
        return deckString;
    }

}


package com.cards.cardsInnGame.model;


/**
 * Created by Chinmay Murugkar on 10/15/17.
 */


public class Card implements Comparable<Card> {

    //lets have the card two properties one is the rank and the the suit it belongs
    private Suit suit;
    private Rank rank;

    //this is the constructor for the card to instantiate
    public Card (Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }


    //this is the suit we need to get
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }


    @Override
    public int compareTo (Card o) {
        int rankCompare = rank.compareTo(o.rank);
        return rankCompare != 0 ? rankCompare : suit.compareTo(o.suit);
    }

    public String toString () {
        return suit.toString () + " " + rank.toString ();
    }

}

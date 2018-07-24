package com.cards.cardsInnGame.model;

/**
 * Created by Chinmay Murugkar on 10/15/17.
 */

/*lets create an enumeration of the ranks and also, lets try to assign an integer value to each of the rank.
We will provide the int values as 2 through 14 as the 1 can be ACE but we is higher in rank, so we will consider it as 14 for natural oredering purposes
 */

public enum Rank {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private int rank;

    private Rank(int rank) {
        this.rank = rank;
    }

    public int getRankValue(){
        return rank;
    }
}

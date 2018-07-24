A standard deck of cards consists of 4 suits (in descending order: Spades, Hearts, Diamonds and Clubs), and in each suit there are 13 ranks (in descending order: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2), there is one card for each combination (e.g. Queen of Hearts, Jack of Diamonds) for a total of 52 cards.  Please try to use clean and meaningful abstractions as you go through this.  

This code has following features :
1. Constructs a deck of cards containing all suit/rank combinations.
2. Shuffles the deck
3. Given that there are 4 players, deals each player 5 cards.  When the cards are dealt, this is called a “hand”.
4. Determines a “winner” of the “hand” using the following rules:
  a. The most cards of a matching rank win  (4 of a rank beats 3 of a rank, which in turn beats 2 of a rank).
      i If more than one player has matches of the same rank, the winner will be the one who has the highest ranked match (Ace, then King, then Queen, and on down the ranks).   
      ii If two players each have a pair of the same rank, the winner will be one with the highest suit 

  b. If no player has a match, the winner will be the one who has the highest ranked card.   
      i If two players have the same ranked card, the winner will be the highest suit.
5. Deals 50 hands, shuffling between every 10 hands.   
6. Prints a scorecard to the console that indicates how many hands each player won.
7. Another scoring algorithm uses standard poker rules, as found here:  http://www.cardplayer.com/rules-of-poker/hand-rankings.  Have the program play a large number of hands and print out two histograms.  One with winning players, another with winning hands (the number of times two of a kind won vs a full house, vs all the other possibilities listed in the hand rankings).  


This can be run by following command :

./gradlew clean build bootRun            

The game has been divided into model view controller.
Following tech is used :
Springboot - I have not autowired any objects here
Junit - testing framework
Java 8
slj4 Logger

game state models have been made singleton to keep track of scores and player through all the games. 
Once player created they will play 50 games. Dealer deals the cards and puts them back in the deck
Interesting factor is the behavior of the outcome on shuffling every time and shuffling at every 10th game.
I have created histograms in the end to show the outcomes of the players and the game.

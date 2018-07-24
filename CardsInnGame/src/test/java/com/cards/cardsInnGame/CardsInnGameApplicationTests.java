package com.cards.cardsInnGame;

import com.cards.cardsInnGame.controller.CardGameController;
import com.cards.cardsInnGame.controller.ConfigController;
import com.cards.cardsInnGame.controller.RankStats;
import com.cards.cardsInnGame.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardsInnGameApplicationTests {


	/*@Test
	public void contextLoads() {

	}*/
	Deck deck = new Deck();
	CardGameController cardGameController = CardGameController.getInstance();
	ConfigController configController = ConfigController.getInstance();
	GameState gameState = GameState.getInstance();

	@Test
	public void beforeDeckCreation() {

		assertEquals("Deck is generating right",null,deck.getDeck());

	}

	@Test
	public void testDeckCreation() {

		String testString = "TWO of SPADES, TWO of HEARTS, TWO of DIAMONDS, TWO of CLUBS, THREE of SPADES, THREE of HEARTS, THREE of DIAMONDS, THREE of CLUBS, FOUR of SPADES, FOUR of HEARTS, FOUR of DIAMONDS, FOUR of CLUBS, FIVE of SPADES, FIVE of HEARTS, FIVE of DIAMONDS, FIVE of CLUBS, SIX of SPADES, SIX of HEARTS, SIX of DIAMONDS, SIX of CLUBS, SEVEN of SPADES, SEVEN of HEARTS, SEVEN of DIAMONDS, SEVEN of CLUBS, EIGHT of SPADES, EIGHT of HEARTS, EIGHT of DIAMONDS, EIGHT of CLUBS, NINE of SPADES, NINE of HEARTS, NINE of DIAMONDS, NINE of CLUBS, TEN of SPADES, TEN of HEARTS, TEN of DIAMONDS, TEN of CLUBS, JACK of SPADES, JACK of HEARTS, JACK of DIAMONDS, JACK of CLUBS, QUEEN of SPADES, QUEEN of HEARTS, QUEEN of DIAMONDS, QUEEN of CLUBS, KING of SPADES, KING of HEARTS, KING of DIAMONDS, KING of CLUBS, ACE of SPADES, ACE of HEARTS, ACE of DIAMONDS, ACE of CLUBS, ";
		deck.createDeck();
		String deckString = deck.readDeck();
		assertEquals("Deck is generating right",testString,deckString);

	}


	/*@Test
	public void testPlayerCreation(){
		cardGameController.startController();
		assertEquals("Number of players",4,gameState.getPlayers().size());
	}*/

	@Test
	public void HandDifferentRankCreation(){
		Hand hand = new Hand();
		Card card1 = new Card(Rank.ACE,Suit.DIAMONDS);
		Card card2 = new Card(Rank.FOUR,Suit.HEARTS);
		Card card3 = new Card(Rank.QUEEN,Suit.CLUBS);
		Card card4 = new Card(Rank.KING, Suit.HEARTS);
		Card card5 = new Card(Rank.NINE,Suit.SPADES);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		hand.addCard(card5);
		assertEquals("Number of players","[HEARTS FOUR, SPADES NINE, CLUBS QUEEN, HEARTS KING, DIAMONDS ACE]",hand.hand.toString());
	}

	@Test
	public void HandSameRankCreation(){
		Hand hand = new Hand();
		Card card1 = new Card(Rank.ACE,Suit.DIAMONDS);
		Card card2 = new Card(Rank.ACE,Suit.HEARTS);
		Card card3 = new Card(Rank.KING,Suit.CLUBS);
		Card card4 = new Card(Rank.KING, Suit.HEARTS);
		Card card5 = new Card(Rank.NINE,Suit.SPADES);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		hand.addCard(card5);
		assertEquals("Number of players","[SPADES NINE, HEARTS KING, CLUBS KING, HEARTS ACE, DIAMONDS ACE]",hand.hand.toString());
	}

	@Test
	public void testTwoRankCOunting(){
		Hand hand = new Hand();
		Player player = new Player();
		player.setHand(hand);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		Card card1 = new Card(Rank.KING,Suit.DIAMONDS);
		Card card2 = new Card(Rank.KING,Suit.HEARTS);
		Card card3 = new Card(Rank.ACE,Suit.CLUBS);
		Card card4 = new Card(Rank.ACE, Suit.HEARTS);
		Card card5 = new Card(Rank.NINE,Suit.SPADES);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		hand.addCard(card5);
		RankStats rankStats = new RankStats();
		rankStats.rankCount(players);
		assertEquals("Testing ranks",0, rankStats.fourOfRank.size());
		assertEquals("Testing ranks",1, rankStats.twoOfRank.size());
		assertEquals("Testing ranks",0, rankStats.threeOfRank.size());
	}

	@Test
	public void testThreeRankCOunting(){
		Hand hand = new Hand();
		Player player = new Player();
		player.setHand(hand);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		Card card1 = new Card(Rank.ACE,Suit.DIAMONDS);
		Card card2 = new Card(Rank.ACE,Suit.HEARTS);
		Card card3 = new Card(Rank.ACE,Suit.CLUBS);
		Card card4 = new Card(Rank.KING, Suit.HEARTS);
		Card card5 = new Card(Rank.NINE,Suit.SPADES);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		hand.addCard(card5);
		RankStats rankStats = new RankStats();
		rankStats.rankCount(players);
		assertEquals("Testing ranks",0, rankStats.fourOfRank.size());
		assertEquals("Testing ranks",0, rankStats.twoOfRank.size());
		assertEquals("Testing ranks",1, rankStats.threeOfRank.size());
	}

	@Test
	public void testFourRankCOunting(){
		Hand hand = new Hand();
		Player player = new Player();
		player.setHand(hand);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		Card card1 = new Card(Rank.ACE,Suit.DIAMONDS);
		Card card2 = new Card(Rank.ACE,Suit.HEARTS);
		Card card3 = new Card(Rank.ACE,Suit.CLUBS);
		Card card4 = new Card(Rank.ACE, Suit.HEARTS);
		Card card5 = new Card(Rank.NINE,Suit.SPADES);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		hand.addCard(card5);
		RankStats rankStats = new RankStats();
		rankStats.rankCount(players);
		assertEquals("Testing ranks",1, rankStats.fourOfRank.size());
		assertEquals("Testing ranks",0, rankStats.twoOfRank.size());
		assertEquals("Testing ranks",0, rankStats.threeOfRank.size());
	}

}

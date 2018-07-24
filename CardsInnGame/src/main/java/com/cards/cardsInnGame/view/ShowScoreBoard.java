package com.cards.cardsInnGame.view;




import com.cards.cardsInnGame.model.GameState;
import com.cards.cardsInnGame.model.Player;
import com.cards.cardsInnGame.model.PokerGameState;

import java.util.HashMap;

/**
 * Created by Chinmay Murugkar on 10/15/17
 */


public class ShowScoreBoard {

    //creating a singleton pattern for the gamestate
    private static ShowScoreBoard instance = null;
    protected ShowScoreBoard() {
        // Exists only to defeat instantiation.
    }
    public static ShowScoreBoard getInstance() {
        if (instance == null) {
            instance = new ShowScoreBoard();
        }
        return instance;
    }

    GameState gameState = GameState.getInstance();
    PokerGameState pokerGameState = PokerGameState.getInstance();

    public void showScore() {

        HashMap<Player, Integer> scoreMap = gameState.getScoreMap();
        HashMap<Player, Integer> pokerScoreMap = pokerGameState.getPokerScoreMap();
        HashMap<String, Integer> pokerGameCount = pokerGameState.getGameWinMap();
        String histogramStr= "=";

        System.out.println("--------------------------------------------------------------------");
        System.out.println("  _________                            _________                  .___\n" +
                " /   _____/ ____  ___________   ____   \\_   ___ \\_____ _______  __| _/\n" +
                " \\_____  \\_/ ___\\/  _ \\_  __ \\_/ __ \\  /    \\  \\/\\__  \\\\_  __ \\/ __ | \n" +
                " /        \\  \\__(  <_> )  | \\/\\  ___/  \\     \\____/ __ \\|  | \\/ /_/ | \n" +
                "/_______  /\\___  >____/|__|    \\___  >  \\______  (____  /__|  \\____ | \n" +
                "        \\/     \\/                  \\/          \\/     \\/           \\/ ");
        System.out.println("--------------------------------------------------------------------\n");
        //lets scores for the map
        for (Player player : scoreMap.keySet()) {

            System.out.println(player.getPlayerName() +" | " + scoreMap.get(player));

        }
        System.out.println("\n--------------------------------------------------------------------");

        System.out.println("\n\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("\\______   \\____ |  | __ ___________   /   _____/ ____  ___________   ____   ______\n" +
                " |     ___/  _ \\|  |/ // __ \\_  __ \\  \\_____  \\_/ ___\\/  _ \\_  __ \\_/ __ \\ /  ___/\n" +
                " |    |  (  <_> )    <\\  ___/|  | \\/  /        \\  \\__(  <_> )  | \\/\\  ___/ \\___ \\ \n" +
                " |____|   \\____/|__|_ \\\\___  >__|    /_______  /\\___  >____/|__|    \\___  >____  >\n" +
                "                     \\/    \\/                \\/     \\/                  \\/     \\/ ");
        System.out.println("\n--------------------------------------------------------------------");

        System.out.println("HISTOGRAM FROM THE POKER GAME WINNINGS");

        for (Player player : pokerScoreMap.keySet()) {

            //System.out.println(player.getPlayerName() +" | " + pokerScoreMap.get(player));
            System.out.println("\n");
            int count =  pokerScoreMap.get(player);
            System.out.print(count+" ");
            for(int j = 0; j < count; j++)
                System.out.print(histogramStr);
            System.out.print(" "+ player.getPlayerName());

        }

        System.out.println("\n\n--------------------------------------------------------------------\n\n");

        System.out.println("HISTOGRAM FROM THE POKER GAME WINNINGS");

        for (String gameName : pokerGameCount.keySet()) {
            System.out.println("\n");
            int count =  pokerGameCount.get(gameName);
            System.out.print(count+" ");
            for(int j = 0; j < count; j++)
                System.out.print(histogramStr);
            System.out.print(" "+ gameName);
        }

        System.out.println("\n\n\n\n");

    }

}

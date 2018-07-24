package com.cards.cardsInnGame;

import com.cards.cardsInnGame.controller.CardGameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardsInnGameApplication implements CommandLineRunner {

	@Autowired
	private CardGameController cardGameController;

	public static void main(String[] args) {
		//SpringApplication.run(CardsInnApplication.class, args);

		SpringApplication app = new SpringApplication(CardsInnGameApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {


		//lets start the game here
		cardGameController.startController();

		System.exit(0);
	}

}

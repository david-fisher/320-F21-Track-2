package objects;

import java.awt.Color;

public class Demo {

	public static void main(String[] args) {
		Project proj = new Project("testproj", "C:\\users\\bhavs\\Desktop");
		Savable.setProject(proj);
		Savable.initDB();
		
		createObjects();
		
	}
	
	//Things determined
	/**
	 * We can save a custom object inside a custom object
	 * We can save protected members
	 * The set method for something that needs to be saved can only return void
	 * Documentation obtained from the creators of snakeyaml
	 */
	
	public static void createObjects() {
		//TODO
		/**
		 * Figure out how to save color.awt
		 */
		
		Gamepiece car = new Gamepiece();

		car.setLabel("Baby Berk");
		car.setIcon("berk.jpg");
		car.setColor(Color.YELLOW);

		car.setTrait("wheels", 4, true);

		Token token1 = new Token();
		Token token2 = new Token();

		token1.setLabel("ten dining dollars");
		token2.setLabel("ten dining dollars");

		Card ace = new Card();
		ace.setLabel("ace");
		Card king = new Card();
		king.setLabel("king");
		Card queen = new Card();
		queen.setLabel("queen");
		Card jack = new Card();
		jack.setLabel("jack");
		
		Deck deck = new Deck();
		deck.addCard(ace, 2);
		deck.addCard(king, 2);
		deck.addCard(queen, 2);
		deck.addCard(jack, 2);

		Die die = new Die();
		
		Category hamp = new Category();
		hamp.setLabel("hamp");
		hamp.setWeight(0.2);
		Category woo = new Category();
		woo.setLabel("woo");
		woo.setWeight(0.2);
		Category frank = new Category();
		frank.setLabel("frank");
		frank.setWeight(0.2);
		Category berk = new Category();
		berk.setLabel("berk");
		berk.setWeight(0.4);
		Spinner spinner = new Spinner();
		
		//Gameboard needs a default constructor
		GameBoard gboard = new GameBoard("Rectangle", 12, 15);
		
		
		GameTimer gtimer = new GameTimer();
		
		Tile testTile = new Tile();
		
	}

}

package objects;

public class Demo {

	public static void main(String[] args) {
//		createObjects();
//		loadProject();
		deleteThenSave();
	}
	
	
	private static void loadProject() {
		Project monopoly = Savable.getProjects().get(0);
		Savable.setProject(monopoly);
		Savable.initDB();
		
		for(Object obj:Savable.get(Die.class)) {
			System.out.println(Die.class.cast(obj).getLabel());
			System.out.println(Die.class.cast(obj).getNumSides());
			System.out.println(Die.class.cast(obj).getColor());
		}
		
		Savable.closeDB();
	}
	
	private static void deleteThenSave() {
		Project monopoly = Savable.getProjects().get(0);
		Savable.setProject(monopoly);
		Savable.initDB();
		
		Savable.objectMap.get("Die").clear();
		
		Savable.closeDB();
	}
	
	
	//Things determined
	/**
	 * We can save a custom object inside a custom object
	 * We can save protected members
	 * The set method for something that needs to be saved can only return void
	 * Documentation obtained from the creators of snakeyaml
	 */
	
	private static void createObjects() {
		Project proj = new Project("testproj", "C:\\users\\bhavs\\Desktop");
		Savable.setProject(proj);
		Savable.initDB();
		
		Gamepiece car = new Gamepiece();

		car.setLabel("Baby Berk");
		car.setIcon("berk.jpg");
		car.setColor("Yellow");

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
		die.setColor("Pink");
		die.setNumSides(57);
		
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
		
		Savable.closeDB();
	}

}

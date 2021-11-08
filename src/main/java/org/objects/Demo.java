package org.objects;

import java.awt.Color;

public class Demo {
	
	public static void main(String[] args ) {

		System.out.println("This is the demonstration of the Game Object API.\n");
		
		System.out.println("First, the game pieces!") ;
		System.out.println("Let's create a game piece called 'car'\n") ;
		
		Gamepiece car = new Gamepiece() ;
		
		System.out.println("These are its default traits") ;
		System.out.println(car.getAllTraits()) ;
		
		System.out.println("\nNow we can set its specific traits") ;
		
		car.setLabel("Baby Berk") ;
		car.setIcon("berk.jpg") ;
		car.setColor(Color.YELLOW) ;
		
		System.out.println(car.getAllTraits()) ;
		

		System.out.println("\nWe can even add new traits if we need to.") ;
		System.out.println("Lets add a trait called wheels") ;
		
		car.setTrait("wheels", 4, true) ;
		System.out.println(car.getAllTraits()) ;
		

		System.out.println("\nNow let's look at another object type: tokens") ;
		System.out.println("If we create 2 tokens, what happens?\n") ;
		
		Token token1 = new Token() ;
		Token token2 = new Token() ;

		System.out.println("The default labels for these tokens would be:") ;
		System.out.println(token1.getLabel()) ;
		System.out.println(token2.getLabel()) ;
		

		System.out.println("\nThis is important because the label acts as a primary key for GameObjects") ;
		System.out.println("In other words, no 2 GameObjects can have the same label\n") ;

		System.out.println("If we try to force them to have the same label we get:") ;
		
		token1.setLabel("ten dining dollars") ;
		token2.setLabel("ten dining dollars") ;
		
		System.out.println(token1.getLabel()) ;
		System.out.println(token2.getLabel()) ;

		System.out.println("\nNext, let's look at a deck of cards.") ;
		System.out.println("First we need to create some cards.") ;
		
		Card ace = new Card();
		ace.setLabel("ace");
		Card king = new Card();
		king.setLabel("king");
		Card queen = new Card();
		queen.setLabel("queen");
		Card jack = new Card();
		jack.setLabel("jack");

		System.out.println(ace.getLabel()) ;
		System.out.println(king.getLabel()) ;
		System.out.println(queen.getLabel()) ;
		System.out.println(jack.getLabel()) ;
		

		System.out.println("\nThen we add them to a deck.") ;
		
		Deck deck = new Deck() ;
		deck.addCard(ace, 2);
		deck.addCard(king, 2);
		deck.addCard(queen, 2);
		deck.addCard(jack, 2);
		
		System.out.println(deck.getCards()) ;

		System.out.println("\nWe can shuffle the deck.");
		deck.shuffle();
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nWe can draw the top card from the deck.");
		Card c = deck.drawTop();
		System.out.println(c) ;
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nWe can put that card on the bottom of the deck.");
		deck.replaceBottom(c) ;
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nWe can draw the bottom card from the deck.");
		c = deck.drawBottom();
		System.out.println(c) ;
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nWe can put that card back on top of the deck.");
		deck.replaceTop(c) ;
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nWe can draw a random card from the deck.");
		c = deck.drawRandom();
		System.out.println(c) ;
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nWe can put that card back randomly in the deck.");
		deck.replaceRandom(c) ;
		System.out.println(deck.getCards()) ;
		
		System.out.println("\nNext, let's explore the die object.") ;
		System.out.println("First we create a die.") ;
		Die die = new Die() ;
		System.out.println(die.getAllTraits()) ;
		
		System.out.println("\nNow we can roll the die!") ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		System.out.println(die.roll()) ;
		
		System.out.println("\nNext, let's make a spinner object.") ;
		System.out.println("First we create a spinner.") ;
		
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
		Spinner spinner = new Spinner() ;
		spinner.addCategory(hamp);
		spinner.addCategory(woo);
		spinner.addCategory(frank);
		spinner.addCategory(berk);
		System.out.println(spinner.getAllTraits()) ;
		
		System.out.println("\nHere we have explicitly defined the following weights:") ;
		System.out.println(hamp.getLabel() + " : " + hamp.getWeight()) ;
		System.out.println(woo.getLabel() + " : " + woo.getWeight()) ;
		System.out.println(frank.getLabel() + " : " + frank.getWeight()) ;
		System.out.println(berk.getLabel() + " : " + berk.getWeight()) ;
		

		System.out.println("\nNow we can spin it!") ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		System.out.println(spinner.spin()) ;
		
		System.out.println("\nThank you for listening \nStay tuned for API updates") ;
		
	}

}

//package org.objects;
//
//import java.awt.Color;
//
//
//public class Test {
//
//	public static void main(String[] args ) {
//
//		System.out.println("TEST This is the demonstration of the Game Object API.\n");
//
//		System.out.println("First, the game pieces!") ;
//		System.out.println("Let's create a game piece called car\n") ;
//
//		Gamepiece car = new Gamepiece() ;
//
//		System.out.println("These are it's default traits") ;
//		System.out.println(car.getAllTraits()) ;
//
//		System.out.println("\nNow we can set its specific traits") ;
//		car.setLabel("Baby Berk") ;
//		car.setIcon("berk.jpg") ;
//		car.setColor(Color.YELLOW) ;
//		System.out.println(car.getAllTraits()) ;
//
//
//		System.out.println("\nWe can even add new traits if we need to.") ;
//		System.out.println("Lets add a trait called wheels") ;
//		car.setTrait("wheels", 4, true) ;
//		System.out.println(car.getAllTraits()) ;
//
//
//		System.out.println("\nNow let's look at another object type: tokens") ;
//		System.out.println("If we create 2 tokens, what happens?\n") ;
//
//		Token token1 = new Token() ;
//		Token token2 = new Token() ;
//
//		System.out.println("The default labels for these tokens would be:") ;
//		System.out.println(token1.getLabel()) ;
//		System.out.println(token2.getLabel()) ;
//
//
//		System.out.println("\nThis is important because the label acts as a primary key for GameObjects") ;
//		System.out.println("In other words, no 2 GameObjects can have the same label\n") ;
//
//		System.out.println("If we try to force them to have the same label we get:") ;
//		token1.setLabel("ten dining dollars") ;
//		token2.setLabel("ten dining dollars") ;
//		System.out.println(token1.getLabel()) ;
//		System.out.println(token2.getLabel()) ;
//
//		System.out.println("\nNext, let's look at a deck of cards.") ;
//		System.out.println("First we need to create some cards.") ;
//
//		Card ace = new Card();
//		ace.setLabel("ace");
//		Card king = new Card();
//		king.setLabel("king");
//		Card queen = new Card();
//		queen.setLabel("queen");
//		Card jack = new Card();
//		jack.setLabel("jack");
//
//		System.out.println(ace.getLabel()) ;
//		System.out.println(king.getLabel()) ;
//		System.out.println(queen.getLabel()) ;
//		System.out.println(jack.getLabel()) ;
//
//
//		System.out.println("\nThen we add them to a deck.") ;
//
//		Deck deck = new Deck() ;
//		deck.addCard(ace, 2);
//		deck.addCard(king, 2);
//		deck.addCard(queen, 2);
//		deck.addCard(jack, 2);
//
//		System.out.println(deck.getCards()) ;
//
//		System.out.println("\nWe can shuffle the deck.");
//		deck.shuffle();
//		System.out.println(deck.getCards()) ;
//
//		System.out.println("\nWe can draw the top card from the deck.");
//		Card c = deck.drawTop();
//		System.out.println(c) ;
//		System.out.println(deck.getCards()) ;
//
//		System.out.println(deck.drawTop()) ;
//		System.out.println(deck.getCards()) ;
//
//		Tile tile1 = new Tile();
//		Tile tile2 = new Tile();
//		Tile tile3 = new Tile();
//		Tile tile4 = new Tile();
//
//		System.out.println(tile1.getConnect()) ;
//
//		tile1.addConnect(tile2) ;
//		tile1.addConnect(tile3) ;
//
//		System.out.println(tile1.getConnect()) ;
//
//		tile1.addConnect(tile1) ;
//		tile1.addConnect(tile2) ;
//
//		System.out.println(tile1.getConnect()) ;
//
//
//		System.out.println(tile1.getAllTraits()) ;
//	}
//
//}

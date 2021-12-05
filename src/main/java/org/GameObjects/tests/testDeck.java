///*
//package org.GameObjects.tests;
//import org.GameObjects.objects.*;
//
//import java.awt.Color;
//
//public class testDeck {
//
//	//Whitebox testing
//
//	private boolean cardAndDeckNotStatic() {
//		try {
//			Card card = new Card();
//			Deck deck = new Deck();
//			print("PASSED - Card and Deck initializable")
//			return true;
//		}
//		catch {
//			print("ERROR - Card and Deck not initializable")
//			return false;
//		}
//	}
//
//	private boolean testAddCard() {
//		try {
//			Card card = new Card();
//			Deck deck = new Deck();
//			deck.addCard(card, 2);
//			print("PASSED - Card can be added to deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Card can't be added to deck");
//			return false;
//		}
//	}
//
//	private boolean testDeleteCard() {
//		try {
//			Card card = new Card();
//			Deck deck = new Deck();
//			deck.deleteCard(card, 2);
//			print("PASSED - Card can be deleted from deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Card can't be deleted from deck");
//			return false;
//		}
//	}
//
//	private boolean testGetSize() {
//		try {
//			Deck deck = new Deck();
//			int size = deck.getSize();
//			print("PASSED - Can get deck's size");
//			return true;
//		}
//		catch {
//			print("ERROR - Can't get deck's size");
//			return false;
//		}
//	}
//
//	private boolean testDrawTop() {
//    	try {
//			Deck deck = new Deck();
//			Card card = deck.drawTop();
//			print("PASSED - Can draw card on top of deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Can't draw card on top of deck");
//			return false;
//		}
//  	}
//
//	private boolean testDrawRandom() {
//		try {
//			Deck deck = new Deck();
//			Card card = deck.drawRandom();
//			print("PASSED - Can draw card at a random location on the deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Can draw card at a random location on the deck");
//			return false;
//		}
//	}
//
//	private boolean testDrawBottom() {
//		try {
//			Deck deck = new Deck();
//			Card card = deck.drawBottom();
//			print("PASSED - Can draw card at the bottom location of the deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Can draw card at the bottom location on the deck");
//			return false;
//		}
//	}
//
//	private boolean testReplaceTop() {
//		try {
//			Deck deck = new Deck();
//			Card card = new Card();
//			deck.replaceTop(card);
//			print("PASSED - Can replace card at the top location of the deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Can replace card at the top location on the deck");
//			return false;
//		}
//	}
//
//	private boolean testReplaceRandom() {
//		try {
//			Deck deck = new Deck();
//			Card card = new Card();
//			deck.replaceRandom(card);
//			print("PASSED - Can replace card at a random location of the deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Can replace card at a random location on the deck");
//			return false;
//		}
//	}
//
//	private boolean testReplaceBottom() {
//		try {
//			Deck deck = new Deck();
//			Card card = new Card();
//			deck.replaceBottom(card);
//			print("PASSED - Can replace card at the bottom location of the deck");
//			return true;
//		}
//		catch {
//			print("ERROR - Can replace card at the bottom location on the deck");
//			return false;
//		}
//	}
//
//	private boolean testShuffle() {
//		try {
//			Deck deck = new Deck();
//			deck.shuffle();
//			print("PASSED - Deck can be shuffled");
//			return true;
//		}
//		catch {
//			print("ERROR - Deck unable to be shuffled");
//			return false;
//		}
//	}
//
//	private boolean testDeal() {
//		try {
//			Deck deck = new Deck();
//			deck.deal(0);
//			print("PASSED - Deck can deal");
//			return true;
//		}
//		catch {
//			print("ERROR - Deck unable to deal");
//			return false;
//		}
//	}
//
//	private boolean testDeckGettersAndSetters() {
//		try {
//			Deck deck = new Deck();
//			ArrayList<Card> cardList = new ArrayList<Card>();
//			deck.setCards(cardList);
//			cardList = deck.getCards();
//			print("PASSED - Getter and setter functions exist");
//			return true;
//		}
//		catch {
//			print("ERROR - Class has no getter and/or setter functions");
//			return false;
//		}
//	}
//
//	public static void main(String[] args) {
//		cardAndDeckNotStatic();
//		testAddCard();
//		testDeleteCard();
//		testDrawBottom();
//		testDrawTop();
//		testDrawRandom();
//		testReplaceBottom();
//		testReplaceTop();
//		testReplaceRandom();
//		testShuffle();
//		testDeal();
//		testDeckGettersAndSetters();
//	}
//}
//*/
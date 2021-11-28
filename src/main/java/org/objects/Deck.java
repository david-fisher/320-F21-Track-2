package org.objects;

import javafx.scene.paint.Color;
import javafx.scene.paint.*;
import java.util.ArrayList;
import java.util.List;

public class Deck extends GameObject {

  private static int count = 0;
  private List<Card> deck;

  public Deck() {
    super() ;  
    
    deck = new ArrayList<Card>() ;
    
	this.setLabel("deck" + String.format("%02d", ++count));
	this.setIcon("https://i.pinimg.com/originals/23/3e/ff/233eff1a0bcd8812bf8ba73fd94235bc.jpg") ;
	this.setColor(Color.BLACK) ;
    this.setTrait("cards", deck, true) ;
  }
  
//set trait to value. Overrides checking for default traits only
 public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
	  
	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }
	  
//	  // checks for other valid inputs
//	  else if (suppressTraitChecker ||	// if true don't check trait type
//			  (trait.equals("cards") && value instanceof ArrayList)) {	// check value is String
//		  traits.put(trait, value) ;
//		  return true ;
//	  }
	  
	  // returns false if input is invalid
	  return false ;
 }

  public void addCard(Card c, int quantity) {
	for (int i = 0; i < quantity; ++i) {
	    deck.add(c) ;
	}
  }

  public void deleteCard(Card c, int quantity) {
	for (int i = 0; i < quantity; ++i) {
		if (deck.contains(c)) {
			deck.remove(c) ;
		}
	}
  }
  
  public int getSize() {
	  return deck.size() ;
  }
  
  public boolean isEmpty() {
	  return deck.isEmpty() ;
  }

  public Card drawTop() {
	if (this.isEmpty()) return null ;
    return deck.remove(0);
  }

  public Card drawRandom() {
	if (this.isEmpty()) return null ;
    int rand = (int)(Math.random() * this.getSize()) ;
    return deck.remove(rand);
  }

  public Card drawBottom() {
	if (this.isEmpty()) return null ;
    return deck.remove(this.getSize() - 1);
  }

  public void replaceTop(Card card) {
    deck.add(0, card);
  }

  public void replaceBottom(Card card) {
	deck.add(this.getSize(), card);
  }

  public void replaceRandom(Card card) {
	int rand = (int)(Math.random() * this.getSize()) ;
	deck.add(rand, card);
  }

  public void shuffle() {
    java.util.Collections.shuffle(deck);
  }

  public List<Card> deal(int num) {
	List<Card> hand = new ArrayList<Card>(num) ;
	
	for (int i = 0; i < num; ++i) {
		Card card = this.drawTop() ;
		if (card != null) hand.add(card) ;
	}
	
    return hand;
  }
  
//  public boolean setCards(ArrayList<Card> cards) {
//	  return this.setTrait("cards", cards);
//  }

  public List<Card> getCards() {
	  return deck;
  }
}
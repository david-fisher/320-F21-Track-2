package objects;

import java.awt.Color;
import java.util.ArrayList;

public class Deck extends GameObject {

  private static int count = 0;

  public Deck() {
    super() ;  
	this.setLabel("deck" + String.format("%02d", ++count));
	this.setIcon("default_gamepiece_icon.jpg") ;
	this.setColor(Color.BLACK) ;
	this.setCards(new ArrayList<Card>()) ;
  }
  
//set trait to value. Overrides checking for default traits only
 public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
	  
	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }
	  
	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("cards") && value instanceof ArrayList)) {	// check value is String
		  traits.put(trait, value) ;
		  return true ;
	  }
	  
	  // returns false if input is invalid
	  return false ;
 }

  public void addCard(Card c, int quantity) {
	for (int i = 0; i < quantity; ++i) {
	    this.getCards().add(c) ;
	}
  }

  public void deleteCard(Card c, int quantity) {
	for (int i = 0; i < quantity; ++i) {
		if (this.getCards().contains(c)) {
			this.getCards().remove(c) ;
		}
	}
  }
  
  public int getSize() {
	  return this.getCards().size() ;
  }

  public Card drawTop() {
    return this.getCards().remove(0);
  }

  public Card drawRandom() {
    int rand = (int)(Math.random() * this.getSize()) ;
    return this.getCards().remove(rand);
  }

  public Card drawBottom() {
	if (this.getSize() <= 0) {
		return null ;
	}
    return this.getCards().remove(this.getSize() - 1);
  }

  public void replaceTop(Card card) {
    this.getCards().add(0, card);
  }

  public void replaceBottom(Card card) {
	this.getCards().add(this.getSize(), card);
  }

  public void replaceRandom(Card card) {
	int rand = (int)(Math.random() * this.getSize()) ;
	this.getCards().add(rand, card);
  }

  public void shuffle() {
    java.util.Collections.shuffle(this.getCards());
  }

  public ArrayList<Card> deal(int num) {
	ArrayList<Card> hand = new ArrayList<Card>(num) ;
	
	for (int i = 0; i < num; ++i) {
		hand.add(this.drawTop()) ;
	}
	
    return hand;
  }
  
  public boolean setCards(ArrayList<Card> cards) {
	  return this.setTrait("cards", cards);
  }

  public ArrayList<Card> getCards() {
	  return (ArrayList<Card>) this.getTrait("cards");
  }
}
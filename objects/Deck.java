package objects;

import java.util.ArrayList;

public class Deck extends GameObject {
  private ArrayList<Card> deck;

  public Deck() {
    this.deck = new ArrayList<Card>();
  }

  public void addCard(Card c, int quantity) {
    // TODO
  }

  public void deleteCard(Card c, int quantity) {
    // TODO
  }

  public Card drawTop() {
    return deck.get(0);
  }

  public Card drawRandom() {
    // TODO
    return new Card();
  }

  public Card drawBottom() {
    return deck.get(deck.size() - 1);
  }

  public void replaceTop() {
    // TODO
  }

  public void replaceBottom() {
    // TODO
  }

  public void replaceRandom() {
    // TODO
  }

  public void shuffle() {
    // TODO
  }

  public ArrayList<Card> deal(int num) {
    // TODO
    return new ArrayList<Card>();
  }
}
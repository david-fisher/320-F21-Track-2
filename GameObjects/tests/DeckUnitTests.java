package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.List;

import objects.Card;
import objects.Deck;

import org.junit.jupiter.api.Test;

class DeckUnitTests {

  Deck deck = new Deck();
  Card c;
  
  @Test
  public void DeckAddDeleteGetCards() {
    Deck deck = new Deck();
    assert (deck.isEmpty());
    Card c1 = new Card();
    Card c2 = new Card(); 
    Card c3 = new Card();
    c1.setText("zero-th card");
    c1.setText("first card");
    c2.setText("second card");
    c3.setText("third card");
    deck.addCard(c1, 1);
    assert (!deck.isEmpty());
    assert (deck.getCards().size() == 1);
    deck.addCard(c2, 1);
    deck.addCard(c3, 1);
    assert (deck.getCards().size() == 3);
    assert (deck.getSize() == 3);
    List<Card> cards = deck.getCards();
    assert (cards.get(0).getText().equals("first card"));
    assert (cards.get(2).getText().equals("third card"));
    deck.deleteCard(c2, 1);
    assert (cards.get(1).getText().equals("third card"));
    deck.addCard(c2, 3);
    assert (deck.getSize() == 5);
    deck.deleteCard(c2, 5);
    assert (deck.getSize() == 2);
    assert (cards.get(0).getText().equals("first card"));
    assert (cards.get(1).getText().equals("third card"));
  }
  
  @Test
  public void DeckDrawAndReplace() {
    for (int i = 4; i < 19; ++i) {
      c = new Card();
      c.setText(String.format("%02d", i));
      c.setLabel("card" + String.format("%02d", i));
      deck.addCard(c, 1);
    }
    assert (deck.getSize() == 15);
    c = deck.drawTop();
    assert (c.getLabel().equals("card04"));
    assert (deck.getSize() == 14);
    deck.replaceTop(c);
    assert (deck.getCards().get(0).getLabel().equals("card04"));
    assert (deck.getCards().get(1).getLabel().equals("card05"));
    assert (deck.getSize() == 15);
    c = deck.drawBottom();
    assert (c.getLabel().equals("card18"));
    assert (deck.getSize() == 14);
    deck.replaceTop(c);
    assert (deck.getSize() == 15);
    assert (deck.getCards().get(0).getLabel().equals("card18"));
    assert (deck.getCards().get(1).getLabel().equals("card04"));
    assert (deck.getCards().get(14).getLabel().equals("card17"));
    deck.drawTop();
    deck.replaceBottom(c);
    assert (deck.getSize() == 15);
    assert (deck.getCards().get(0).getLabel().equals("card04"));
    assert (deck.getCards().get(14).getLabel().equals("card18"));
    for (int i = 19; i < 100; ++i) {
      c = new Card();
      c.setText(String.format("%02d", i));
      c.setLabel("card" + String.format("%02d", i));
      deck.addCard(c, 1);
    }
    /*
     * Note:
     * Aside from the size checks, the tests of drawRandom and replaceRandom 
     * can fail on occasion, it's consistent failure that indicates an issue.
     */
    c = deck.drawRandom();
    assert (deck.getSize() == 95);
    assert (deck.getCards().get(0).getLabel().equals("card04"));
    assert (deck.getCards().get(94).getLabel().equals("card99"));
    deck.replaceRandom(c);
    assert (deck.getSize() == 96);
    assert (deck.getCards().get(0).getLabel().equals("card04"));
    assert (deck.getCards().get(95).getLabel().equals("card99"));
    c = deck.drawRandom();
    deck.replaceTop(c);
    assert (!deck.getCards().get(0).getLabel().equals("card04"));
    c = deck.drawRandom();
    deck.replaceBottom(c);
    assert (!deck.getCards().get(95).getLabel().equals("card99"));
  } 
  // to be continued
  
  /*
   * Cases to check for:
   *  -- 1. Deleting more cards than exist in the deck
   *  -- 2. Adding multiple instances of a single card
   *  -- 3. Replacing a card does not affect order or unintentionally remove other cards
   *  4. Shuffling actually shuffles properly
   *  -- 5. Drawing a card always removes it from the proper spot in the deck
   *  -- 6. drawRandom and replaceRandom are random
   *  ... more to come
   */

}

//
//package GameObjects.tests;
//
//
//import java.awt.Color;
//import java.util.List;
//
//import GameObjects.objects.Card;
//import GameObjects.objects.Deck;
//
//import org.junit.Test;
//
//class DeckUnitTests {
//
//  Deck deck = new Deck();
//
//  @Test
//  public void DeckAddDeleteGetCards() {
//    Deck deck = new Deck();
//    Card c1 = new Card();
//    Card c2 = new Card();
//    Card c3 = new Card();
//    c1.setText("zero-th card");
//    c1.setText("first card");
//    c2.setText("second card");
//    c3.setText("third card");
//    deck.addCard(c1, 1);
//    assert (deck.getCards().size() == 1);
//    deck.addCard(c2, 1);
//    deck.addCard(c3, 1);
//    assert (deck.getCards().size() == 3);
//    assert (deck.getSize() == 3);
//    List<Card> cards = deck.getCards();
//    assert (cards.get(0).getText().equals("first card"));
//    assert (cards.get(2).getText().equals("third card"));
//    deck.deleteCard(c2, 1);
//    assert (cards.get(1).getText().equals("third card"));
//  }
//
//  // to be continued
//
//  /*
//   * Cases to check for:
//   *  1. Deleting more cards than exist in the deck
//   *  2. Adding multiple instances of a single card
//   *  3. Replacing a card does not affect order or unintentionally remove other cards
//   *  4. Shuffling actually shuffles properly
//   *  5. Drawing a card always removes it from the proper spot in the deck
//   *  6. drawRandom and replaceRandom are random
//   *  ...
//   */
//
//}

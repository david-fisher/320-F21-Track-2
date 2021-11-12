package editors.game_object_ui.controllers;

import javafx.scene.control.*;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;

import objects.*;

public class GameObjectUIController {
    // Card tab
    @FXML private TextField cardName;
    @FXML private TextField textureFilename;
    @FXML private ColorPicker cardColor;
    @FXML private MenuButton action;

    // Deck tab
    private ObservableList<Card> deckCards;
    @FXML private ListView deckDeckList;
    @FXML private ListView deckCardList;

    // Die tab
    @FXML private TextField dieName;
    @FXML private TextField dieNumSides;
    @FXML private ColorPicker dieColor;
    @FXML private ColorPicker diePipColor;


    public GameObjectUIController() {
        deckCards = FXCollections.observableArrayList(new Card(), new Card());
    }

    @FXML private void saveCard(ActionEvent event) {
        Card card = new Card();
        String cardNameString = cardName.getCharacters().toString();
        String textureFilenameString = textureFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = cardColor.getValue();
        java.awt.Color awtColor = new java.awt.Color((float)jfxColor.getRed(), (float)jfxColor.getGreen(), (float)jfxColor.getBlue());
        boolean labelRes = card.setTrait("label", cardNameString, false);
        boolean iconRes = card.setTrait("icon", textureFilenameString, false);
        boolean colorRes = card.setTrait("color", awtColor, false);
        if (!(labelRes && iconRes && colorRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new card: " + card.toString());
            deckCards.add(card);
        }
    }

    @FXML private void saveDie(ActionEvent event) {
        Die die = new Die();
    }

    @FXML private void populateCardList(Event event) {
        deckCardList.setItems(deckCards);
        deckCardList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckDeckList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML private void addHighlighted(ActionEvent event) {
        ObservableList<Integer> selectedCardIndices = deckCardList.getSelectionModel().getSelectedIndices();
        ObservableList<Card> cards = deckCardList.getItems();
        ObservableList<Card> deck = deckDeckList.getItems();
        ArrayList<Card> removedCards = new ArrayList();

        // For every card selected, add it to the deck list
        for (Integer i: selectedCardIndices) {
            Card c = cards.get(i);
            deck.add(c);
            removedCards.add(c);
        }

        // Then remove all the cards that are selected from the card list
        for (int i = 0; i < removedCards.size(); i += 1) {
            cards.remove(removedCards.get(i));
        }

        // Now update the ListViews with the appropriate changes
        deckCardList.setItems(cards);
        deckDeckList.setItems(deck);
    }

    @FXML private void removeHighlighted(ActionEvent event) {
        ObservableList<Integer> selectedCardIndices = deckDeckList.getSelectionModel().getSelectedIndices();
        ObservableList<Card> cards = deckCardList.getItems();
        ObservableList<Card> deck = deckDeckList.getItems();
        ArrayList<Card> removedCards = new ArrayList();

        // For every card selected, add it to the deck list
        for (Integer i: selectedCardIndices) {
            Card c = deck.get(i);
            cards.add(c);
            removedCards.add(c);
        }

        // Then remove all the cards that are selected from the card list
        for (int i = 0; i < removedCards.size(); i += 1) {
            deck.remove(removedCards.get(i));
        }

        // Now update the ListViews with the appropriate changes
        deckCardList.setItems(cards);
        deckDeckList.setItems(deck);
    }

    @FXML private void saveDeck(ActionEvent event) {
        Deck deck = new Deck();
        ObservableList<Card> cardsInDeck = (ObservableList<Card>)deckDeckList.itemsProperty().getValue();
        ObservableList<Card> cardsInCardList = (ObservableList<Card>)deckCardList.itemsProperty().getValue();
        System.out.println(cardsInCardList.toString());
    }
}

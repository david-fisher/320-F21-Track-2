package editors.game_object_ui.controllers;

import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import editors.main_menu.MainMenu;
import objects.*;

public class GameObjectUIController {
    // Card tab
    @FXML private TextField cardName;
    @FXML private TextField textureFilename;
    @FXML private ColorPicker cardColor;
    @FXML private MenuButton cardAction;

    // Deck tab
    private ObservableList<Card> deckCards;
    @FXML private ListView deckDeckList;
    @FXML private ListView deckCardList;

    // Die tab
    @FXML private TextField dieName;
    @FXML private TextField dieNumSides;
    @FXML private ColorPicker dieColor;
    @FXML private ColorPicker diePipColor;

    // Spinner tab
    @FXML private TextField spinnerName;
    @FXML private ColorPicker spinnerColor;
    @FXML private TextField spinnerFilename;
    @FXML private TextField spinnerNumCategories;
    private ObservableList<Category> spinnerCategories;

    // Token tab
    @FXML private TextField tokenName;
    @FXML private ColorPicker tokenColor;
    @FXML private TextField tokenValue;
    @FXML private TextField tokenFilename;

    // Timer tab
    @FXML private TextField timerName;
    @FXML private ColorPicker timerColor;
    @FXML private TextField initialTime;
    @FXML private TextField timerFilename;

    // Category tab
    @FXML private TextField categoryName;
    @FXML private ColorPicker categoryColor;
    @FXML private TextField categoryWeight;
    @FXML private TextField categoryFilename;

    // GamePiece tab
    @FXML private TextField gamepieceName;
    @FXML private ColorPicker gamepieceColor;
    @FXML private TextField gamepieceLocation;
    @FXML private TextField gamepieceFilename;

    public GameObjectUIController() {
        deckCards = FXCollections.observableArrayList(new Card(), new Card());
    }

    @FXML private void switchToMainMenu(Event event) {
        URL location = getClass().getResource("../../../resources/MainMenuScreen.fxml");
        try {
            Parent root = (Parent)FXMLLoader.load(location);
            MainMenu.stage.getScene().setRoot(root);
            MainMenu.stage.show();
        } catch (IOException e){ 
            e.printStackTrace();
        }
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
        String dieNameString = dieName.getCharacters().toString();
        Integer numSides = Integer.valueOf(dieNumSides.getCharacters().toString());
        javafx.scene.paint.Color dieSideColor = dieColor.getValue();
        javafx.scene.paint.Color pipColor = diePipColor.getValue();
        java.awt.Color colorOne = new java.awt.Color((float)dieSideColor.getRed(),
                (float)dieSideColor.getGreen(), (float)dieSideColor.getBlue());
        java.awt.Color colorTwo = new java.awt.Color((float)pipColor.getRed(),
                (float)pipColor.getGreen(), (float)pipColor.getBlue());
        boolean nameRes = die.setTrait("label", dieNameString, false);
        boolean numRes = die.setTrait("numSides", numSides, false);
        boolean colorRes = die.setTrait("color", colorOne, false);
        boolean pipRes = die.setTrait("dotColor", colorTwo, false);
        if (!(nameRes && numRes && colorRes && pipRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new die: " + dieNameString);
        }
    }


    @FXML private void saveSpinner(ActionEvent event) {
        // Spinner is ambiguous, is it worth changing the name of the class?
        objects.Spinner spinner = new objects.Spinner();
        
    }

    @FXML private void saveCategory(ActionEvent event) {
        Category category = new Category();
        String categoryNameString = categoryName.getCharacters().toString();
        Double categoryWeight = Double.valueOf(dieNumSides.getCharacters().toString());
        String categoryFilenameString = categoryFilename.getCharacters().toString();
        javafx.scene.paint.Color catColor = categoryColor.getValue();
        java.awt.Color colorOne = new java.awt.Color((float)catColor.getRed(),
                (float)catColor.getGreen(), (float)catColor.getBlue());
        boolean nameRes = category.setTrait("category", categoryNameString, false);
        boolean weightRes = category.setWeight("weight", categoryWeight, false);
        boolean colorRes = category.setTrait("color", colorOne, false);
        boolean fileRes = category.setTrait("icon", categoryFilenameString, false);
        if (!(nameRes && weightRes && colorRes && fileRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new category: " + categoryNameString);
        }
    }

    @FXML private void saveGamepiece(ActionEvent event) {
        Gamepiece piece = new Gamepiece();
        String pieceNameString = gamepieceName.getCharacters().toString();
        String textureFilenameString = textureFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = gamepieceColor.getValue();

        // TODO: This is just a dummy tile as of now
        Tile tile = new Tile();
        Tile location = tile;

        java.awt.Color awtColor = new java.awt.Color((float)jfxColor.getRed(),
                (float)jfxColor.getGreen(), (float)jfxColor.getBlue());
        boolean labelRes = piece.setTrait("label", pieceNameString, false);
        boolean iconRes = piece.setTrait("icon", textureFilenameString, false);
        boolean colorRes = piece.setTrait("color", awtColor, false);
        boolean locRes = piece.setTrait("location", tile, false);
        if (!(labelRes && iconRes && colorRes && locRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Gamepiece: " + pieceNameString);
        }
    }

    @FXML private void saveToken(ActionEvent event) {
        Token token = new Token();
        String tokenNameString = tokenName.getCharacters().toString();
        String textureFilenameString = tokenFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = tokenColor.getValue();
        Integer value = Integer.valueOf(tokenValue.getCharacters().toString());
        java.awt.Color awtColor = new java.awt.Color((float)jfxColor.getRed(),
                (float)jfxColor.getGreen(), (float)jfxColor.getBlue());
        boolean labelRes = token.setTrait("label", tokenNameString, false);
        boolean iconRes = token.setTrait("icon", textureFilenameString, false);
        boolean colorRes = token.setTrait("color", awtColor, false);
        boolean valRes = token.setTrait("value", value, false);
        if (!(labelRes && iconRes && colorRes && valRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Token: " + tokenNameString);
        }
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

        // For every card selected, add it to the card list
        for (Integer i: selectedCardIndices) {
            Card c = deck.get(i);
            cards.add(c);
            removedCards.add(c);
        }

        // Then remove all the cards that are selected from the deck list
        for (int i = 0; i < removedCards.size(); i += 1) {
            deck.remove(removedCards.get(i));
        }

        // Now update the ListViews with the appropriate changes
        deckCardList.setItems(cards);
        deckDeckList.setItems(deck);
    }

    @FXML private void saveDeck(ActionEvent event) {
        Deck deck = new Deck();
        ObservableList<Card> cardsInDeck = deckDeckList.getItems();
        for (Card c: cardsInDeck) {
            deck.addCard(c, 1);
        }
        System.out.println(deck.getCards().toString());
    }
}

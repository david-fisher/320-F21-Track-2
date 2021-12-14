package org.Editors.controllers;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
import org.Editors.MainMenu;
import org.GameObjects.objects.*;

public class GameObjectUIController {
    // Card tab
    @FXML private TextField cardName;
    @FXML private ColorPicker cardColor;
    @FXML private MenuButton cardAction;
    private String cardTextureFilename;

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
    private String spinnerTextureFilename;
    @FXML private ListView spinnerElements;
    @FXML private ListView spinnerCategoryList;
    private ObservableList<Category> spinnerCategories;

    // Token tab
    @FXML private TextField tokenName;
    @FXML private ColorPicker tokenColor;
    @FXML private TextField tokenValue;
    private String tokenTextureFilename;

    // Timer tab
    @FXML private TextField timerName;
    @FXML private ColorPicker timerColor;
    @FXML private TextField initialTime;
    private String timerTextureFilename;

    // Category tab
    @FXML private TextField categoryName;
    @FXML private ColorPicker categoryColor;
    @FXML private TextField categoryWeight;
    private String categoryTextureFilename;

    // GamePiece tab
    @FXML private TextField gamepieceName;
    @FXML private ColorPicker gamepieceColor;
    @FXML private TextField gamepieceLocation;
    private String gamepieceTextureFilename;

    public GameObjectUIController() {
        deckCards = FXCollections.observableArrayList();
        spinnerCategories = FXCollections.observableArrayList();
        gamepieceTextureFilename = timerTextureFilename = categoryTextureFilename = tokenTextureFilename = cardTextureFilename = "";
    }

    @FXML private void switchToMainMenu(Event event) {
        URL location = getClass().getResource("../../../resources/MainMenuScreen.fxml");
        try {
            Parent root = (Parent) FXMLLoader.load(location);
            MainMenu.stage.getScene().setRoot(root);
            MainMenu.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void saveCard(ActionEvent event) {
        Card card = new Card();
        String cardNameString = cardName.getCharacters().toString();
        Color jfxColor = cardColor.getValue();
        boolean labelRes = card.setLabel(cardNameString);
        boolean iconRes = card.setIcon(cardTextureFilename);
        boolean colorRes = card.setColor(jfxColor);
        if (!(labelRes && iconRes && colorRes)) {
            System.err.println("Failure!");
            System.err.println(labelRes);
            System.err.println(iconRes);
            System.err.println(colorRes);
        } else {
            System.out.println("Successfully created new card: " + card.toString());
            deckCards.add(card);
        }
    }

    @FXML private void saveDie(ActionEvent event) {
        Die die = new Die();
        String dieNameString = dieName.getCharacters().toString();
        Integer numSides = Integer.valueOf(dieNumSides.getCharacters().toString());
        Color dieSideColor = dieColor.getValue();
        Color pipColor = diePipColor.getValue();
        boolean labelRes = die.setLabel(dieNameString);
        boolean pipRes = die.setDotColor(pipColor);
        boolean colorRes = die.setColor(dieSideColor);
        boolean numRes = die.setNumSides(numSides);

        if (!(labelRes && numRes && colorRes && pipRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new die: " + dieNameString);
        }
    }

    @FXML private void saveCategory(ActionEvent event) {
        Category category = new Category();
        String categoryNameString = categoryName.getCharacters().toString();
        double catWeight = Double.parseDouble(categoryWeight.getCharacters().toString());
        Color catColor = categoryColor.getValue();
        boolean nameRes = category.setLabel(categoryNameString);
        boolean weightRes = category.setWeight(catWeight);
        boolean colorRes = category.setColor(catColor);
        boolean fileRes = category.setIcon(categoryTextureFilename);
        if (!(nameRes && weightRes && colorRes && fileRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new category: " + categoryNameString);
            spinnerCategories.add(category);
        }
    }

    @FXML private void saveGamepiece(ActionEvent event) {
        Gamepiece piece = new Gamepiece();
        String pieceNameString = gamepieceName.getCharacters().toString();
        Color jfxColor = gamepieceColor.getValue();

        // TODO: This is just a dummy tile as of now
        Tile tile = new Tile();
        Tile location = tile;

        boolean labelRes = piece.setLabel(pieceNameString);
        boolean iconRes = piece.setIcon(gamepieceTextureFilename);
        boolean colorRes = piece.setColor(jfxColor);
        boolean locRes = piece.setLocation(tile);
        if (!(labelRes && iconRes && colorRes && locRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Gamepiece: " + pieceNameString);
        }
    }

    @FXML private void saveToken(ActionEvent event) {
        Token token = new Token();
        String tokenNameString = tokenName.getCharacters().toString();
        Color jfxColor = tokenColor.getValue();
        Integer value = Integer.valueOf(tokenValue.getCharacters().toString());
        boolean labelRes = token.setLabel(tokenNameString);
        boolean iconRes = token.setIcon(tokenTextureFilename);
        boolean colorRes = token.setColor(jfxColor);
        boolean valRes = token.setValue(value);
        if (!(labelRes && iconRes && colorRes && valRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Token: " + tokenNameString);
        }
    }

    @FXML private void saveTimer(ActionEvent event) {
        GameTimer timer = new GameTimer();
        String timerNameString = timerName.getCharacters().toString();
        Color jfxColor = timerColor.getValue();
        Double timerTime = Double.valueOf(initialTime.getCharacters().toString());
        boolean labelRes = timer.setLabel(timerNameString);
        boolean iconRes = timer.setIcon(timerTextureFilename);
        boolean colorRes = timer.setColor(jfxColor);
        boolean valRes = timer.setInitialTime(timerTime);
        if (!(labelRes && iconRes && colorRes && valRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Timer: " + timerNameString);
        }
    }

    @FXML private void populateCardList(Event event) {
        deckCardList.setItems(deckCards);
        deckCardList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckDeckList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML private void deckAddHighlighted(ActionEvent event) {
        ObservableList<Integer> selectedCardIndices = deckCardList.getSelectionModel().getSelectedIndices();
        ObservableList<Card> cards = deckCardList.getItems();
        ObservableList<Card> deck = deckDeckList.getItems();
        ArrayList<Card> removedCards = new ArrayList();

        // For every card selected, add it to the deck list
        for (Integer i : selectedCardIndices) {
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

    @FXML private void deckRemoveHighlighted(ActionEvent event) {
        ObservableList<Integer> selectedCardIndices = deckDeckList.getSelectionModel().getSelectedIndices();
        ObservableList<Card> cards = deckCardList.getItems();
        ObservableList<Card> deck = deckDeckList.getItems();
        ArrayList<Card> removedCards = new ArrayList();

        // For every card selected, add it to the card list
        for (Integer i : selectedCardIndices) {
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
        for (Card c : cardsInDeck) {
            deck.addCard(c, 1);
        }
        System.out.println(deck.getCards().toString());
        Card c1 = deck.drawRandom();
        Card c2 = deck.drawRandom();
        deck.replaceRandom(c1);
        deck.replaceRandom(c2);
        System.out.println(c1.toString());
        System.out.println(c2.toString());

    }

    @FXML private void populateSpinnerList(Event event) {
        spinnerCategoryList.setItems(spinnerCategories);
        spinnerCategoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        spinnerElements.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML private void spinnerAddHighlighted(ActionEvent event) {
        ObservableList<Integer> selectedIndices = spinnerCategoryList.getSelectionModel().getSelectedIndices();
        ObservableList<Category> elements = spinnerElements.getItems();
        ObservableList<Category> categories = spinnerCategoryList.getItems();
        ArrayList<Category> removedCategories = new ArrayList();

        // For every category selected, add it to the spinner elements list
        for (Integer i : selectedIndices) {
            Category c = categories.get(i);
            elements.add(c);
            removedCategories.add(c);
        }

        // Then remove all the categories that are selected from the categories list
        for (int i = 0; i < removedCategories.size(); i += 1) {
            categories.remove(removedCategories.get(i));
        }

        // Now update the ListViews with the appropriate changes
        spinnerCategoryList.setItems(categories);
        spinnerElements.setItems(elements);
    }

    @FXML private void spinnerRemoveHighlighted(ActionEvent event) {
        ObservableList<Integer> selectedIndices = spinnerElements.getSelectionModel().getSelectedIndices();
        ObservableList<Category> elements = spinnerElements.getItems();
        ObservableList<Category> categories = spinnerCategoryList.getItems();
        ArrayList<Category> removedElements = new ArrayList();

        // For every category selected, add it to the spinner elements list
        for (Integer i : selectedIndices) {
            Category c = elements.get(i);
            categories.add(c);
            removedElements.add(c);
        }

        // Then remove all the categories that are selected from the categories list
        for (int i = 0; i < removedElements.size(); i += 1) {
            elements.remove(removedElements.get(i));
        }

        // Now update the ListViews with the appropriate changes
        spinnerCategoryList.setItems(categories);
        spinnerElements.setItems(elements);
    }

    @FXML private void saveSpinner(ActionEvent event) {
        org.GameObjects.objects.Spinner spinner = new org.GameObjects.objects.Spinner();
        ObservableList<Category> elements = spinnerElements.getItems();
        spinner.setNumCategories(elements.size());
        boolean labelRes = spinner.setLabel(spinnerName.getCharacters().toString());
        boolean colorRes = spinner.setColor(spinnerColor.getValue());
    }

    @FXML private void getCardFile(ActionEvent e) {
        cardTextureFilename = getFilePath();
    }

    @FXML private void getTokenFile(ActionEvent e) {
        tokenTextureFilename = getFilePath();
    }

    @FXML private void getTimerFile(ActionEvent e) {
        timerTextureFilename = getFilePath();
    }

    @FXML private void getCategoryFile(ActionEvent e) {
        categoryTextureFilename = getFilePath();
    }

    @FXML private void getGamePieceFile(ActionEvent e) {
        gamepieceTextureFilename = getFilePath();
    }

    @FXML private void getSpinnerFile(ActionEvent e) {
        spinnerTextureFilename = getFilePath();
    }

    private String getFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open texture file");
        fileChooser.getExtensionFilters().addAll(
          new ExtensionFilter("Image files", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(MainMenu.stage);
        if (selectedFile != null) {
            try {
                return selectedFile.getAbsolutePath();
            } catch (SecurityException e) {
                System.err.println(e.getMessage());
            } 
        }
        return "";
    }
}

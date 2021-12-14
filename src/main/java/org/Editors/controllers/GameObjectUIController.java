package org.Editors.controllers;

import java.io.File;

import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.io.IOException;

import javafx.stage.Stage;
import org.Editors.MainMenu;
import org.GameObjects.objects.*;
import org.GamePlay.BasicApplication;
import org.GamePlay.controllers.ScreenController;
import org.RuleEngine.engine.GameState;
import org.RuleEngine.impossible.Game;

import javax.swing.*;

public class GameObjectUIController extends ScreenController {
    // Card tab
    @FXML
    private TextField cardName;
    @FXML
    private TextField cardFilename;
    @FXML
    private ColorPicker cardColor;
    @FXML
    private MenuButton cardAction;

    // Deck tab
    private ObservableList<Card> deckCards;
    @FXML
    private ListView deckDeckList;
    @FXML
    private ListView deckCardList;

    // Die tab
    @FXML
    private TextField dieName;
    @FXML
    private TextField dieNumSides;
    @FXML
    private ColorPicker dieColor;
    @FXML
    private ColorPicker diePipColor;

    // Spinner tab
    @FXML
    private TextField spinnerName;
    @FXML
    private ColorPicker spinnerColor;
    @FXML
    private TextField spinnerFilename;
    @FXML
    private TextField spinnerNumCategories;
    @FXML
    private ListView spinnerElements;
    @FXML
    private ListView spinnerCategoryList;
    private ObservableList<Category> spinnerCategories;

    // Token tab
    @FXML
    private TextField tokenName;
    @FXML
    private ColorPicker tokenColor;
    @FXML
    private TextField tokenValue;
    @FXML
    private TextField tokenFilename;

    // Timer tab
    @FXML
    private TextField timerName;
    @FXML
    private ColorPicker timerColor;
    @FXML
    private TextField initialTime;
    @FXML
    private TextField timerFilename;

    // Category tab
    @FXML
    private TextField categoryName;
    @FXML
    private ColorPicker categoryColor;
    @FXML
    private TextField categoryWeight;
    @FXML
    private TextField categoryFilename;

    // GamePiece tab
    @FXML
    private TextField gamepieceName;
    @FXML
    private ColorPicker gamepieceColor;
    @FXML
    private TextField gamepieceLocation;
    @FXML
    private TextField gamepieceFilename;

    // Button tab
    @FXML
    private TextField buttonName;
    @FXML
    private ColorPicker buttonColor;
    @FXML
    private TextField buttonText;
    @FXML
    private TextField buttonFilename;
    @FXML
    private TextField onClick;

    // Tile tab
    @FXML
    private TextField tileName;
    @FXML
    private ColorPicker tileColor;
    @FXML
    private TextField tileFilename;
    @FXML
    private TextField tileShape;
    @FXML
    private TextField tileOnLand;

    // Player tab
    @FXML
    private TextField playerName;
    @FXML
    private ColorPicker playerColor;
    @FXML
    private TextField playerGamepieces;
    @FXML
    private TextField playerInventory;
    @FXML
    private ToggleGroup playerIsHuman;

    private GameState gameState = BasicApplication.getProject().getIntiGS();

    public GameObjectUIController() {
        deckCards = FXCollections.observableArrayList(new Card(), new Card());
        Category cat1 = new Category();
        Category cat2 = new Category();
        cat1.setTrait("label", "category 03", true);
        cat2.setTrait("label", "category 04", true);
        spinnerCategories = FXCollections.observableArrayList(cat1, cat2);

    }

    @FXML
    private void switchToMainMenu(ActionEvent event) throws IOException {
        Savable.closeDB();
        changeScene(event, "MainMenuScreen.fxml");
    }

    public void changeScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }

    @FXML
    private void saveCard(ActionEvent event) {
        Card card = new Card();
        String cardNameString = cardName.getCharacters().toString();
        String textureFilenameString = cardFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = cardColor.getValue();
        boolean labelRes = card.setTrait("label", cardNameString, false);
        boolean iconRes = card.setTrait("icon", textureFilenameString, false);
        boolean colorRes = card.setTrait("color", jfxColor, false);
        if (!(labelRes && iconRes && colorRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new card: " + card.toString());
            deckCards.add(card);
            gameState.getAllCards().add(card);
        }
    }

    @FXML
    private void saveDie(ActionEvent event) {
        Die die = new Die();
        String dieNameString = dieName.getCharacters().toString();
        Integer numSides = Integer.valueOf(dieNumSides.getCharacters().toString());
        String dieSideColor = dieColor.getValue().toString();
        String pipColor = diePipColor.getValue().toString();
        boolean nameRes = die.setTrait("label", dieNameString, false);
        boolean numRes = die.setTrait("numSides", numSides, false);
        boolean colorRes = die.setTrait("color", dieSideColor, false);
        boolean pipRes = die.setTrait("dotColor", pipColor, false);
        if (!(nameRes && numRes && colorRes && pipRes)) {
            System.err.println("Failure!" + nameRes + numRes + colorRes + pipRes);
        } else {
            System.out.println("Successfully created new die: " + dieNameString);
            gameState.getAllDice().add(die);
        }
    }

    @FXML
    private void saveCategory(ActionEvent event) {
        Category category = new Category();
        String categoryNameString = categoryName.getCharacters().toString();
        double catWeight = Double.parseDouble(categoryWeight.getCharacters().toString());
        String categoryFilenameString = categoryFilename.getCharacters().toString();
        javafx.scene.paint.Color catColor = categoryColor.getValue();
        boolean nameRes = category.setTrait("label", categoryNameString, false);
        boolean weightRes = category.setWeight(catWeight);
        boolean colorRes = category.setTrait("color", catColor, false);
        boolean fileRes = category.setTrait("icon", categoryFilenameString, false);
        if (!(nameRes && weightRes && colorRes && fileRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new category: " + categoryNameString);
            spinnerCategories.add(category);
        }
    }

    @FXML
    private void saveGamepiece(ActionEvent event) {
        Gamepiece piece = new Gamepiece();
        String pieceNameString = gamepieceName.getCharacters().toString();
        String textureFilenameString = gamepieceFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = gamepieceColor.getValue();

        // TODO: This is just a dummy tile as of now
        Tile tile = new Tile();
        Tile location = tile;

        boolean labelRes = piece.setTrait("label", pieceNameString, false);
        boolean iconRes = piece.setTrait("icon", textureFilenameString, false);
        boolean colorRes = piece.setTrait("color", jfxColor, false);
        boolean locRes = piece.setTrait("location", tile, false);
        if (!(labelRes && iconRes && colorRes && locRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Gamepiece: " + pieceNameString);
            gameState.getAllGamePieces().add(piece);
        }
    }

    @FXML
    private void saveToken(ActionEvent event) {
        Token token = new Token();
        String tokenNameString = tokenName.getCharacters().toString();
        String textureFilenameString = tokenFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = tokenColor.getValue();
        Integer value = Integer.valueOf(tokenValue.getCharacters().toString());
        boolean labelRes = token.setTrait("label", tokenNameString, false);
        boolean iconRes = token.setTrait("icon", textureFilenameString, false);
        boolean colorRes = token.setTrait("color", jfxColor, false);
        boolean valRes = token.setTrait("value", value, false);
        if (!(labelRes && iconRes && colorRes && valRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Token: " + tokenNameString);
            gameState.getAllTokens().add(token);
        }
    }

    @FXML
    private void saveTile(ActionEvent event) {
        Tile tile = new Tile();
        String tileNameString = tileName.getCharacters().toString();
        String textureFilenameString = tileFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = tileColor.getValue();
        String shape = tileShape.getCharacters().toString();
        String onLand = tileOnLand.getCharacters().toString();
        boolean labelRes = tile.setTrait("label", tileNameString, false);
        boolean iconRes = tile.setTrait("icon", textureFilenameString, false);
        boolean colorRes = tile.setTrait("color", jfxColor, false);
        boolean shapeRes = tile.setTrait("shape", shape, false);
        boolean onLandRes = tile.setTrait("onLand", onLand, false);
        if (!(labelRes && iconRes && colorRes && shapeRes && onLandRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Tile: " + tileNameString);
        }
    }

    @FXML
    private void saveTimer(ActionEvent event) {
        GameTimer timer = new GameTimer();
        String timerNameString = timerName.getCharacters().toString();
        String textureFilenameString = timerFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = timerColor.getValue();
        Double timerTime = Double.valueOf(initialTime.getCharacters().toString());
        boolean labelRes = timer.setTrait("label", timerNameString, false);
        boolean iconRes = timer.setTrait("icon", textureFilenameString, false);
        boolean colorRes = timer.setTrait("color", jfxColor, false);
        boolean valRes = timer.setTrait("initialTime", timerTime, false);
        if (!(labelRes && iconRes && colorRes && valRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Timer: " + timerNameString);
            gameState.getAllTimers().add(timer);
        }
    }

    @FXML
    private void saveButton(ActionEvent event) {
        org.GameObjects.objects.Button button = new org.GameObjects.objects.Button();
        String buttonNameString = buttonName.getCharacters().toString();
        String textureFilenameString = buttonFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = buttonColor.getValue();
        String buttonTextString = buttonText.getCharacters().toString();
        String onClickString = onClick.getCharacters().toString();
        boolean labelRes = button.setTrait("label", buttonNameString, false);
        boolean iconRes = button.setTrait("icon", textureFilenameString, false);
        boolean colorRes = button.setTrait("color", jfxColor, false);
        boolean textRes = button.setTrait("text", buttonTextString, false);
        boolean onClickRes = button.setTrait("onClick", onClickString, false);
        if (!(labelRes && iconRes && colorRes && textRes && onClickRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Button: " + buttonNameString);
            gameState.getAllButtons().add(button);
        }
    }

    @FXML
    private void populateCardList(Event event) {
        deckCardList.setItems(deckCards);
        deckCardList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckDeckList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void deckAddHighlighted(ActionEvent event) {
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

    @FXML
    private void deckRemoveHighlighted(ActionEvent event) {
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

    @FXML
    private void saveDeck(ActionEvent event) {
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
        gameState.getAllDecks().add(deck);
    }

    @FXML
    private void savePlayer(ActionEvent event) {
        String playerNameString = playerName.getCharacters().toString();
        String textureFilenameString = tokenFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = playerColor.getValue();

        // TODO: Inventory UI
        ArrayList<GameObject> inventory = new ArrayList<GameObject>();

        // TODO: Gamepiece UI
        ArrayList<Gamepiece> gamepieces = new ArrayList<Gamepiece>();
        gamepieces.add(new Gamepiece());

        String isHuman = ((RadioButton)playerIsHuman.getSelectedToggle()).getText();
        boolean human = false;
        if(isHuman.equals("Yes")){
            human = true;
            System.out.println(human);
        }

        Player player = new Player();
        boolean labelRes = player.setTrait("label", playerNameString, false);
        boolean pieceRes = player.setTrait("GamePieces", gamepieces, true);
        boolean colorRes = player.setTrait("color", jfxColor.toString(), false);
        boolean invRes = player.setTrait("inventory", inventory, true);
        boolean humanRes = player.setTrait("isHuman", human, false);
        if (!(labelRes && pieceRes && colorRes && invRes && humanRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Player: " + playerNameString);
            System.out.println(player.getColor());
            System.out.println(player.getColorString());
            gameState.getAllPlayers().add(player);
        }
    }

    @FXML
    private void populateSpinnerList(Event event) {
        spinnerCategoryList.setItems(spinnerCategories);
        spinnerCategoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        spinnerElements.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void spinnerAddHighlighted(ActionEvent event) {
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

    @FXML
    private void spinnerRemoveHighlighted(ActionEvent event) {
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

    @FXML
    private void saveSpinner(ActionEvent event) {
        org.GameObjects.objects.Spinner spinner = new org.GameObjects.objects.Spinner();
        ObservableList<Category> elements = spinnerElements.getItems();
        spinner.setNumCategories(elements.size());
        System.out.println("Created spinner with " + spinner.getNumCategories() + " categories");
        System.out.println(spinner.spin().toString());
        System.out.println(spinner.spin().toString());
        System.out.println(spinner.spin().toString());
        gameState.getAllSpinners().add(spinner);
    }

    @FXML
    private void getFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open texture file");
        fileChooser.getExtensionFilters().addAll(
          new ExtensionFilter("Image files", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(MainMenu.stage);
        if (selectedFile != null) {
            System.out.println(selectedFile.getName());
        }
    }
}

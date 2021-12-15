package org.Editors.controllers;

import java.io.File;

import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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

public class GameObjectUIController extends ScreenController {
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
    @FXML private TextField gamepieceLocationX;
    @FXML private TextField gamepieceLocationY;
    @FXML private ListView gamepieceAllTileList;
    @FXML private ListView gamepieceSelectedTileList;
    private String gamepieceTextureFilename;

    // Tile tab
    @FXML private TextField tileName;
    @FXML private ColorPicker tileColor;
    @FXML private TextField tileFilename;
    @FXML private TextField tileShape;
    @FXML private TextField tileOnLand;

    // Player tab
    @FXML private TextField playerName;
    @FXML private ColorPicker playerColor;
    @FXML private TextField playerGamepieces;
    @FXML private TextField playerInventory;
    @FXML private ListView playerInventoryList;
    @FXML private ListView gameObjectList;
    @FXML private ListView playerGamepiecesList;
    @FXML private ListView gamePieceList;
    @FXML private ToggleGroup playerIsHuman;

    // Button tab
    @FXML private TextField buttonName;
    @FXML private ColorPicker buttonColor;
    @FXML private TextField buttonText;
    @FXML private TextField buttonFilename;
    @FXML private MenuButton buttonOnClick;
    @FXML private ListView buttonEventList;

    private GameState gameState = BasicApplication.getProject().getIntiGS();

    private ArrayList<Tile> gameTiles = gameState.getAllTiles();

    public GameObjectUIController() {
        deckCards = FXCollections.observableArrayList();
        spinnerCategories = FXCollections.observableArrayList();
        cardTextureFilename = spinnerTextureFilename = tokenTextureFilename = timerTextureFilename = categoryTextureFilename = gamepieceTextureFilename = "";
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

    @FXML private void saveCard(ActionEvent e) {
        Card card = new Card();
        String cardNameString = cardName.getCharacters().toString();
        Color jfxColor = cardColor.getValue();

        boolean labelRes = card.setLabel(cardNameString);
        boolean iconRes = card.setIcon(cardTextureFilename);
        boolean colorRes = card.setColor(jfxColor);
        if (!(labelRes && iconRes && colorRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new card: " + card.repr(true));
            deckCards.add(card);
            gameState.getAllCards().add(card);
        }

    }

    @FXML private void saveDie(ActionEvent e) {
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
            System.out.println("Successfully created new category: " + category.repr(true));
            spinnerCategories.add(category);
        }
    }

    @FXML private void saveGamepiece(ActionEvent event) {
        Gamepiece piece = new Gamepiece();
        String pieceNameString = gamepieceName.getCharacters().toString();
        Color jfxColor = gamepieceColor.getValue();

        Tile tile = (Tile) gamepieceSelectedTileList.getItems().get(0);

        boolean labelRes = piece.setLabel(pieceNameString);
        boolean iconRes = piece.setIcon(gamepieceTextureFilename);
        boolean colorRes = piece.setColor(jfxColor);
        boolean locRes = piece.setLocation(tile);
        if (!(labelRes && iconRes && colorRes && locRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Gamepiece: " + piece.repr(true));
            gameState.getAllGamePieces().add(piece);
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
            System.out.println("Successfully created new Token: " + token.repr(true));
        }
    }

    @FXML
    private void saveTimer(ActionEvent event) {
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
            System.out.println("Successfully created new Timer: " + timer.repr(true));
            gameState.getAllTimers().add(timer);
        }
    }

    @FXML
    private void saveButton(ActionEvent event) {
        org.GameObjects.objects.Button button = new org.GameObjects.objects.Button();
        String buttonNameString = buttonName.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = buttonColor.getValue();
        String buttonTextString = buttonText.getCharacters().toString();
        String onClickString = buttonOnClick.getText();
        System.out.println(onClickString);

        boolean labelRes = button.setLabel(buttonNameString);
        boolean colorRes = button.setColor(jfxColor);
        boolean textRes = button.setText(buttonTextString);
        boolean onClickRes = button.setOnClick(onClickString);

        if (!(labelRes && colorRes && textRes && onClickRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Button: " + buttonNameString);
            gameState.getAllButtons().add(button);
        }
    }

    @FXML private void populateTileList(Event e) {
        ObservableList<Tile> observable = FXCollections.observableArrayList(gameTiles);
        gamepieceAllTileList.setItems(observable);
    }

    @FXML private void populateEventList(Event e) {
        String[] l = gameState.events.keySet().toArray(new String[0]);
        ObservableList<MenuItem> observable = buttonOnClick.getItems();
        for (int i = 0; i < l.length; i++) {
            observable.addAll(new MenuItem(l[i]));
        }
        //buttonEventList.setItems(observable);
    }

    @FXML private void populatePlayerInventory(Event e) {
        //Populate gamePiecesList
        ObservableList<Gamepiece> pieces = FXCollections.observableArrayList(gameState.getAllGamePieces());
        // Filter through the list of gamepieces to only populate with pieces belonging to a
        // particular player.
        gamePieceList.setItems(pieces);
        gamePieceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        playerGamepiecesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Populate gameObjectList
        ObservableList<Card> objects = FXCollections.observableArrayList(gameState.getAllCards());
        // Filter through the list of gamepieces to only populate with pieces belonging to a
        // particular player.
        gameObjectList.setItems(objects);
        gameObjectList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        playerInventoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML private void populateCardList(Event event) {
        deckCardList.setItems(deckCards);
        deckCardList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckDeckList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML private void playerAddHighlightedGamepiece(ActionEvent e) {
        ObservableList<Integer> selectedIndices = gamePieceList.getSelectionModel().getSelectedIndices();
        ObservableList<Gamepiece> chosen = gamePieceList.getItems();
        ObservableList<Gamepiece> playerPieces = playerGamepiecesList.getItems();
        ArrayList<Gamepiece> removed = new ArrayList();

        // For every gamepiece selected, add it to the other list
        for (Integer i : selectedIndices) {
            Gamepiece g = chosen.get(i);
            playerPieces.add(g);
            removed.add(g);
        }

        // Then remove all the gamepieces that are selected from the inventory list
        for (int i = 0; i < removed.size(); i += 1) {
            chosen.remove(removed.get(i));
        }

        // Now update the ListViews with the appropriate changes
        gamePieceList.setItems(chosen);
        playerGamepiecesList.setItems(playerPieces);
    }

    @FXML private void playerRemoveHighlightedGamepiece(ActionEvent e) {
        ObservableList<Integer> selectedIndices = playerGamepiecesList.getSelectionModel().getSelectedIndices();
        ObservableList<Gamepiece> chosen = gamePieceList.getItems();
        ObservableList<Gamepiece> playerPieces = playerGamepiecesList.getItems();
        ArrayList<Gamepiece> removed = new ArrayList();

        // For every gamepiece selected, add it to the other list
        for (Integer i : selectedIndices) {
            Gamepiece g = playerPieces.get(i);
            chosen.add(g);
            removed.add(g);
        }

        // Then remove all the gamepieces that are selected from the player pieces list
        for (int i = 0; i < removed.size(); i += 1) {
            playerPieces.remove(removed.get(i));
        }

        // Now update the ListViews with the appropriate changes
        gamePieceList.setItems(chosen);
        playerGamepiecesList.setItems(playerPieces);
    }

    @FXML private void playerAddHighlightedObject(ActionEvent e) {
        ObservableList<Integer> selectedIndices = gameObjectList.getSelectionModel().getSelectedIndices();
        ObservableList<GameObject> chosen = gameObjectList.getItems();
        ObservableList<GameObject> playerPieces = playerInventoryList.getItems();
        ArrayList<GameObject> removed = new ArrayList();

        // For every gamepiece selected, add it to the other list
        for (Integer i : selectedIndices) {
            GameObject g = chosen.get(i);
            playerPieces.add(g);
            removed.add(g);
        }

        // Then remove all the gamepieces that are selected from the inventory list
        for (int i = 0; i < removed.size(); i += 1) {
            chosen.remove(removed.get(i));
        }

        // Now update the ListViews with the appropriate changes
        gameObjectList.setItems(chosen);
        playerInventoryList.setItems(playerPieces);
    }

    @FXML private void playerRemoveHighlightedObject(ActionEvent e) {
        ObservableList<Integer> selectedIndices = playerInventoryList.getSelectionModel().getSelectedIndices();
        ObservableList<GameObject> chosen = gameObjectList.getItems();
        ObservableList<GameObject> playerPieces = playerInventoryList.getItems();
        ArrayList<GameObject> removed = new ArrayList();

        // For every gamepiece selected, add it to the other list
        for (Integer i : selectedIndices) {
            GameObject g = playerPieces.get(i);
            chosen.add(g);
            removed.add(g);
        }

        // Then remove all the gamepieces that are selected from the player pieces list
        for (int i = 0; i < removed.size(); i += 1) {
            playerPieces.remove(removed.get(i));
        }

        // Now update the ListViews with the appropriate changes
        gameObjectList.setItems(chosen);
        playerInventoryList.setItems(playerPieces);
    }

    @FXML private void gamepieceAddHighlighted(ActionEvent e) {
        Tile selected = (Tile) gamepieceAllTileList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        ObservableList<Tile> tiles = gamepieceAllTileList.getItems();
        tiles.remove(selected);
        if (gamepieceSelectedTileList.getItems().size() != 0) {
            tiles.add((Tile) gamepieceSelectedTileList.getItems().get(0));
        }
        gamepieceAllTileList.setItems(tiles);
        ArrayList<Tile> list = new ArrayList();
        list.add(selected);
        gamepieceSelectedTileList.setItems(FXCollections.observableArrayList(list));
    }

    @FXML private void gamepieceRemoveHighlighted(ActionEvent e) {
        gamepieceAllTileList.setItems(FXCollections.observableArrayList(gameState.getAllTiles()));
        gamepieceSelectedTileList.setItems(FXCollections.observableArrayList());
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
        gameState.getAllDecks().add(deck);
    }

    @FXML private void savePlayer(ActionEvent event) {
        String playerNameString = playerName.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = playerColor.getValue();

//        // TODO: Inventory UI
        ArrayList<GameObject> inventory = new ArrayList<GameObject>();

        // TODO: Gamepiece UI
        ArrayList<Gamepiece> gamepieces = new ArrayList<>(playerGamepiecesList.getItems());
        boolean human = false;
        try {
            String isHuman = ((RadioButton) playerIsHuman.getSelectedToggle()).getText();
            if(isHuman.equals("Yes")){
                human = true;
            }}
        catch (NullPointerException n) {}
        if (gameState.getAllPlayers().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("This game already has a player:");
            alert.setContentText("All other players will be added when you play the game.");
            alert.showAndWait();
            return;
        }
        Player player = new Player();
        boolean labelRes = player.setTrait("label", playerNameString, false);
        player.setGamePieces(gamepieces);
        boolean colorRes = player.setTrait("color", jfxColor.toString(), false);
        boolean invRes = player.setTrait("inventory", inventory, true);
        boolean humanRes = player.setTrait("isHuman", human, false);
        if (!(labelRes && colorRes && invRes && humanRes)) {
            System.out.println(labelRes);
            System.out.println(colorRes);
            System.out.println(invRes);
            System.out.println(humanRes);
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully created new Player: " + playerNameString);
            gameState.getAllPlayers().add(player);
        }
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
        if (labelRes && colorRes) {
            System.out.println("Successfully created new spinner: " + spinner.repr(true));
        } else {
            System.err.println("Failure!");
        }
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

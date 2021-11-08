package editors.game_object_ui.controllers;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;

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


    @FXML private void saveCard(ActionEvent event) {
        Card card = new Card();
        String cardNameString = cardName.getCharacters().toString();
        String textureFilenameString = textureFilename.getCharacters().toString();
        javafx.scene.paint.Color jfxColor = cardColor.getValue();
        java.awt.Color awtColor = new java.awt.Color((float)jfxColor.getRed(), (float)jfxColor.getGreen(), (float)jfxColor.getBlue());
        ObservableList<MenuItem> list = action.getItems();
        boolean labelRes = card.setTrait("label", cardNameString, false);
        boolean iconRes = card.setTrait("icon", textureFilenameString, false);
        boolean colorRes = card.setTrait("color", awtColor, false);
        if (!(labelRes && iconRes && colorRes)) {
            System.err.println("Failure!");
        } else {
            System.out.println("Successfully populated card object!");
            // For affirming the values have been set correctly
            System.out.println(card.getColor().toString());
            System.out.println(card.getLabel().toString());
            System.out.println(card.getIcon().toString());
            deckCards.addAll(card);
        }
    }

    @FXML private void saveDeck(ActionEvent event) {
        Deck deck = new Deck();
        
    }
}

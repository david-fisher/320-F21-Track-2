package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Set;

public class BasicApplication extends Application {

    private static ArrayList<DummyGame> newGames = new ArrayList<>();
    private static ArrayList<DummyGame> savedGames = new ArrayList<>();

    private static DummyGame selectedGame;
    private static SetupData setupData;


    public static void loadNewGames() {
        // do stuff to get list of playable games (on start) from Persistent Data team

        // filler for now
        ArrayList<DummyTile> tiles = new ArrayList<>();
        ArrayList<DummyDeck> decks = new ArrayList<>();
        ArrayList<DummyPlayer> players = new ArrayList<>();
        ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
        ArrayList<DummyRNG> rng = new ArrayList<>();
        DummyGameBoard gameBoard1 = new DummyGameBoard("Game 1", "Circle", 1200, 800, 10, 10, tiles);
        DummyGameBoard gameBoard2 = new DummyGameBoard("Game 2", "Rectangle", 1200, 800, 10, 10, tiles);
        DummyGameRules gameRules = new DummyGameRules();
        DummyGamestate gamestate = new DummyGamestate(players, tiles, decks, rng, gameTokens);

        DummyGame game1 = new DummyGame(gameBoard1, gameRules, gamestate);
        DummyGame game2 = new DummyGame(gameBoard2, gameRules, gamestate);

        newGames.clear();
        newGames.add(game1);
        newGames.add(game2);
    }

    public static void loadSavedGames() {
        // do stuff to get list of saved games

        // filler for now
        ArrayList<DummyTile> tiles = new ArrayList<>();
        ArrayList<DummyDeck> decks = new ArrayList<>();
        ArrayList<DummyPlayer> players = new ArrayList<>();
        ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
        ArrayList<DummyRNG> rng = new ArrayList<>();
        DummyGameBoard gameBoard1 = new DummyGameBoard("Game 1", "Circle", 1200, 800, 10, 10, tiles);
        DummyGameBoard gameBoard2 = new DummyGameBoard("Game 2", "Rectangle", 1200, 800, 10, 10, tiles);
        DummyGameRules gameRules = new DummyGameRules();
        DummyGamestate gamestate = new DummyGamestate(players, tiles, decks, rng, gameTokens);

        DummyGame game1 = new DummyGame(gameBoard1, gameRules, gamestate);
        DummyGame game2 = new DummyGame(gameBoard2, gameRules, gamestate);

        newGames.clear();
        newGames.add(game1);
        newGames.add(game2);
    }


    public static void setSelectedGame(DummyGame game) {
        selectedGame = game;
    }

    public static void setSetupData(SetupData data) {
        setupData = data;
    }


    public static ArrayList<DummyGame> getNewGames() {
        return newGames;
    }

    public static ArrayList<DummyGame> getSavedGames() {
        return savedGames;
    }

    public static DummyGame getSelectedGame() {
        return selectedGame;
    }

    public static SetupData getSetupData() {
        return setupData;
    }


    @Override
    public void start(Stage stage) throws Exception {

        // load fxml file (which specifies the controller)
        Parent root = FXMLLoader.load(getClass().getResource("mainFXML.fxml"));

        // create new instance of the controller class
        // inject all fx:id tagged objects from fxml file
        // and marked with @FXML annotation in controller

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
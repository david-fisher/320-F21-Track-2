package org.GamePlay.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.GameObjects.objects.Project;
import org.GameObjects.objects.Savable;
import org.RuleEngine.engine.GameState;
import org.GamePlay.GlobalCSSValues;
import org.GamePlay.BasicApplication;

import java.io.IOException;
import java.util.ArrayList;

public class EditController extends ScreenController {

    HBox gamesLabelHBox;
    Label gamesLabel;
    private void initGamesLabel() {

        // bind HBox to vbox width to represent a row
        gamesLabelHBox = new HBox();
        gamesLabelHBox.setStyle("-fx-background-color: transparent");

        gamesLabel = new Label("New Games");
        gamesLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        gamesLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 45;");
        HBox.setMargin(gamesLabel, new Insets(10, 10, 10, 15));

        gamesLabelHBox.getChildren().add(gamesLabel);
        screenVBox.getChildren().add(gamesLabelHBox);
    }

    HBox gamesPaneHBox;
    ScrollPane gamesScrollPane;
    HBox gamesHBox;
    private void initGamesScrollPane() {

        gamesPaneHBox = new HBox();
        gamesPaneHBox.setStyle("-fx-background-color: transparent");
        gamesPaneHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        gamesScrollPane = new ScrollPane();
        gamesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gamesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        gamesScrollPane.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 20));
        gamesScrollPane.prefHeightProperty().bind(Bindings.multiply(screenVBox.heightProperty(), 0.35));
        gamesScrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: " + GlobalCSSValues.text);
        HBox.setMargin(gamesScrollPane, new Insets(10, 10, 10, 10));

        gamesHBox = new HBox();
        gamesHBox.setAlignment(Pos.CENTER_LEFT);
        gamesHBox.prefWidthProperty().bind(Bindings.subtract(gamesScrollPane.widthProperty(), 2));
        gamesHBox.prefHeightProperty().bind(Bindings.subtract(gamesScrollPane.heightProperty(), 2));
        gamesHBox.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);

        gamesScrollPane.setContent(gamesHBox);
        gamesPaneHBox.getChildren().add(gamesScrollPane);
        screenVBox.getChildren().add(gamesPaneHBox);
    }

    HBox buttonsHBox;
    Label backButton;
    Pane fillerPane;
    Label selectGameButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonsHBox.setStyle("-fx-background-color: transparent");
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Label("Back");
        setStyle(backButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 200, 70);

        backButton.setPadding(new Insets(5, 20, 5, 20));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnMouseClicked(event -> {
            MainController controller = new MainController();
            controller.initialize(BasicApplication.restartStage(new Stage()));
        });

        fillerPane = new Pane();
        HBox.setHgrow(fillerPane, Priority.ALWAYS);

        selectGameButton = new Label("Select a Game");
        setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 290, 70);
        selectGameButton.setPadding(backButton.getPadding());
        selectGameButton.setDisable(true);

        HBox.setMargin(selectGameButton, HBox.getMargin(backButton));

        buttonsHBox.getChildren().addAll(backButton, fillerPane, selectGameButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    public void initialize(Stage stage) {

        super.initialize(stage);
        stage.setMaximized(false);
        initGamesLabel();
        initGamesScrollPane();
        initButtons();
        populateSelectionMenus();
    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    private ArrayList<GameState> newGames = new ArrayList<>();
    private ArrayList<GameState> savedGames = new ArrayList<>();
    private GameState selectedGame;

    public void populateSelectionMenus() {

        // convert games to nodes
        ArrayList<Node> savedGameNodes = projectsToNodes(Savable.getProjects());

        savedGameNodes.forEach((n) -> {

            n.setOnMouseClicked(mouseEvent -> {

                selectGameButton.setDisable(false);
                selectGameButton.setText("Edit Game");
                setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 350, 70);

                selectGameButton.setOnMouseClicked(event -> {
                    setSelectedGame((VBox)n);
                    try {
                        changeScene(event, "/org/Editors/controllers/MainMenuScreen.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                // deselect all rectangles
                ObservableList<Node> children = gamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px"));
                children = gamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px"));

                // select this rectangles
                n.setStyle("-fx-border-color: " + GlobalCSSValues.accent + ";-fx-border-style: solid; -fx-border-width: 3px");

                // focus the node
                //n.requestFocus();
            });
            gamesHBox.getChildren().add(n);
        });

    }
    public void changeScene(MouseEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)screenVBox.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }

    public ArrayList<Node> projectsToNodes(ArrayList<Project> projects) {

        ArrayList<Node> nodes = new ArrayList<>();

        projects.forEach(
                (n) -> {

                    VBox tempVBox = new VBox();
                    tempVBox.setAlignment(Pos.CENTER);

                    tempVBox.prefHeightProperty().bind(Bindings.subtract(gamesScrollPane.heightProperty(), 20));
                    tempVBox.minHeightProperty().bind(tempVBox.prefHeightProperty());
                    tempVBox.maxHeightProperty().bind(tempVBox.prefHeightProperty());
                    tempVBox.prefWidthProperty().bind(tempVBox.heightProperty());
                    tempVBox.minWidthProperty().bind(tempVBox.heightProperty());
                    tempVBox.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px");

                    // store the game in the selection VBox
                    tempVBox.setUserData(n);

                    ImageView tempImageView = new ImageView();
                    tempImageView.setPreserveRatio(true);
                    tempImageView.setFitHeight(150);
                    tempImageView.setFitWidth(200);
                    //tempImageView.setImage(n.getIcon());

                    Label tempLabel = new Label();
                    tempLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
                    tempLabel.setText(n.getProjectName());
                    tempLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 20;");

                    tempVBox.getChildren().addAll(tempImageView, tempLabel);

                    HBox.setMargin(tempVBox, new Insets(5,5,5,5));

                    // add node to list of nodes
                    nodes.add(tempVBox);
                }
        );

        return nodes;
    }

    public void setSelectedGame(VBox vbox) {
        BasicApplication.setProject((Project)vbox.getUserData());
    }

    public void initDarken(Label label) {
        label.setOnMouseEntered(e -> {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.2);
            label.setEffect(colorAdjust);
        });

        label.setOnMouseExited(e -> {
            label.setEffect(null);
        });
    }

    public void setStyle(Label label, String size, String color, String textColor, double width, double height) {
        label.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: " + size + "; " +
                "-fx-background-color: " + color + "; " +
                "-fx-border-color: BLACK;");
        label.setTextFill(Color.valueOf(textColor));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        initDarken(label);
    }

    private String invertColor(String myColorString) {

        Color invertedColor = Color.valueOf(myColorString).invert();

        // 6 symbols in capital letters.
        String hex3 = Integer.toHexString(invertedColor.hashCode()).substring(0, 6).toUpperCase();

        return "#" + hex3;
    }
}

package org.GamePlay.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.GameObjects.objects.Project;
import org.GameObjects.objects.Savable;
import org.RuleEngine.engine.GameState;
import org.GamePlay.GlobalCSSValues;
import org.GamePlay.BasicApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditController extends ScreenController {

    @FXML
    AnchorPane editAnchor;
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
    Label removeGameButton;
    Label selectGameButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonsHBox.setStyle("-fx-background-color: transparent");
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Label("Back");
        Style.setStyle(backButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 200, 70);

        backButton.setPadding(new Insets(5, 20, 5, 20));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnMouseClicked(event -> {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            MainController controller = new MainController();
            controller.initialize(BasicApplication.restartStage(new Stage()));
        });

        fillerPane = new Pane();
        HBox.setHgrow(fillerPane, Priority.ALWAYS);

        removeGameButton = new Label("Remove Game");
        Style.setStyle(removeGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 290, 70);

        removeGameButton.setPadding(new Insets(5, 20, 5, 20));
        HBox.setMargin(removeGameButton, new Insets(10, 10, 10, 10));

        removeGameButton.setDisable(true);

        removeGameButton.setOnMouseClicked(e -> {
            removeProject(e);
        });

        selectGameButton = new Label("Select a Game");
        Style.setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 290, 70);
        selectGameButton.setPadding(backButton.getPadding());
        selectGameButton.setDisable(true);

        HBox.setMargin(selectGameButton, HBox.getMargin(backButton));

        buttonsHBox.getChildren().addAll(backButton, fillerPane, removeGameButton, selectGameButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    private void removeProject(MouseEvent event) {
        VBox selectedProjectVBox = (VBox) event.getSource();
        Project selectedProject = (Project) selectedProjectVBox.getUserData();
        Savable.getProjects().remove(selectedProject);
        gamesHBox.getChildren().removeAll(selectedProjectVBox);
        Savable.closeDB();
    }

    public void initialize(Stage stage) {

        super.initialize(stage);
        screenVBox.setAlignment(Pos.CENTER);
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

                removeGameButton.setDisable(false);
                removeGameButton.setOnMouseClicked(e -> {
                    removeProject(mouseEvent);
                });
                selectGameButton.setDisable(false);
                selectGameButton.setText("Edit Game");
                Style.setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 290, 70);

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

                    tempVBox.prefHeight(100);
                    tempVBox.minHeightProperty().bind(tempVBox.prefHeightProperty());
                    tempVBox.maxHeightProperty().bind(tempVBox.prefHeightProperty());
                    tempVBox.prefWidthProperty().bind(tempVBox.heightProperty());
                    tempVBox.minWidthProperty().bind(tempVBox.heightProperty());
                    tempVBox.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px");

                    // store the game in the selection VBox
                    tempVBox.setUserData(n);

                    Label tempLabel = new Label();
                    tempLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
                    tempLabel.setText(n.getProjectName());
                    tempLabel.setAlignment(Pos.CENTER);
                    tempLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 20;");

                    tempVBox.getChildren().addAll(tempLabel);

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

    private String invertColor(String myColorString) {

        Color invertedColor = Color.valueOf(myColorString).invert();

        // 6 symbols in capital letters.
        String hex3 = Integer.toHexString(invertedColor.hashCode()).substring(0, 6).toUpperCase();

        return "#" + hex3;
    }
}

package org.GamePlay.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.GameObjects.objects.Project;
import org.GameObjects.objects.Savable;
import org.RuleEngine.engine.GameState;
import org.GamePlay.GlobalCSSValues;
import org.GamePlay.BasicApplication;

import java.util.ArrayList;

public class SelectionController extends ScreenController {

    HBox newGamesLabelHBox;
    Label newGamesLabel;
    private void initNewGamesLabel() {

        // bind HBox to vbox width to represent a row
        newGamesLabelHBox = new HBox();
        newGamesLabelHBox.setStyle("-fx-background-color: transparent");
        newGamesLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        newGamesLabel = new Label("New Games");
        newGamesLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        newGamesLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 45;");
        HBox.setMargin(newGamesLabel, new Insets(10, 10, 10, 15));

        newGamesLabelHBox.getChildren().add(newGamesLabel);
        screenVBox.getChildren().add(newGamesLabelHBox);
    }

    HBox newGamesScrollPaneHBox;
    ScrollPane newGamesScrollPane;
    HBox newGamesHBox;
    private void initNewGamesScrollPane() {

        newGamesScrollPaneHBox = new HBox();
        newGamesScrollPaneHBox.setStyle("-fx-background-color: transparent");
        newGamesScrollPaneHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        newGamesScrollPane = new ScrollPane();
        newGamesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        newGamesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        newGamesScrollPane.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 20));
        newGamesScrollPane.prefHeightProperty().bind(Bindings.multiply(screenVBox.heightProperty(), 0.35));
        newGamesScrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: " + GlobalCSSValues.text);
        HBox.setMargin(newGamesScrollPane, new Insets(10, 10, 10, 10));

        newGamesHBox = new HBox();
        newGamesHBox.setAlignment(Pos.CENTER_LEFT);
        newGamesHBox.prefWidthProperty().bind(Bindings.subtract(newGamesScrollPane.widthProperty(), 2));
        newGamesHBox.prefHeightProperty().bind(Bindings.subtract(newGamesScrollPane.heightProperty(), 2));
        newGamesHBox.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);

        newGamesScrollPane.setContent(newGamesHBox);
        newGamesScrollPaneHBox.getChildren().add(newGamesScrollPane);
        screenVBox.getChildren().add(newGamesScrollPaneHBox);
    }

    HBox savedGamesLabelHBox;
    Label savedGamesLabel;
    private void initSavedGamesLabel() {
        savedGamesLabelHBox = new HBox();
        savedGamesLabelHBox.setStyle("-fx-background-color: transparent");
        savedGamesLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        savedGamesLabel = new Label("Saved Games");
        savedGamesLabel.setTextFill(newGamesLabel.getTextFill());
        savedGamesLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 45;");
        HBox.setMargin(savedGamesLabel, new Insets(10, 10, 10, 15));

        savedGamesLabelHBox.getChildren().add(savedGamesLabel);
        screenVBox.getChildren().add(savedGamesLabelHBox);
    }

    HBox savedGamesScrollPaneHBox;
    ScrollPane savedGamesScrollPane;
    HBox savedGamesHBox;
    private void initSavedGamesScrollPane() {

        savedGamesScrollPaneHBox = new HBox();
        savedGamesScrollPaneHBox.setStyle(newGamesScrollPaneHBox.getStyle());
        savedGamesScrollPaneHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        savedGamesScrollPane = new ScrollPane();
        savedGamesScrollPane.setVbarPolicy(newGamesScrollPane.getVbarPolicy());
        savedGamesScrollPane.setHbarPolicy(newGamesScrollPane.getHbarPolicy());
        savedGamesScrollPane.prefWidthProperty().bind(newGamesScrollPane.widthProperty());
        savedGamesScrollPane.prefHeightProperty().bind(newGamesScrollPane.heightProperty());
        savedGamesScrollPane.setStyle(newGamesScrollPane.getStyle());
        HBox.setMargin(savedGamesScrollPane, HBox.getMargin(newGamesScrollPane));

        savedGamesHBox = new HBox();
        savedGamesHBox.setAlignment(newGamesHBox.getAlignment());
        savedGamesHBox.prefWidthProperty().bind(Bindings.subtract(savedGamesScrollPane.widthProperty(), 2));
        savedGamesHBox.prefHeightProperty().bind(Bindings.subtract(savedGamesScrollPane.heightProperty(), 2));
        savedGamesHBox.setStyle(newGamesHBox.getStyle());

        savedGamesScrollPane.setContent(savedGamesHBox);
        savedGamesScrollPaneHBox.getChildren().add(savedGamesScrollPane);
        screenVBox.getChildren().add(savedGamesScrollPaneHBox);
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
        Style.setStyle(backButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 200, 70);

        backButton.setPadding(new Insets(5, 20, 5, 20));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnMouseClicked(event -> {
            MainController controller = new MainController();
            controller.initialize(stage);
        });

        fillerPane = new Pane();
        HBox.setHgrow(fillerPane, Priority.ALWAYS);

        selectGameButton = new Label("Select a Game");
        Style.setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 290, 70);
        selectGameButton.setPadding(backButton.getPadding());
        selectGameButton.setDisable(true);

        HBox.setMargin(selectGameButton, HBox.getMargin(backButton));

        buttonsHBox.getChildren().addAll(backButton, fillerPane, selectGameButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    public void initialize(Stage stage) {

        super.initialize(stage);

        initNewGamesLabel();
        initNewGamesScrollPane();
        initSavedGamesLabel();
        initSavedGamesScrollPane();
        initButtons();

        Savable.intitDB();
        newProjects = Savable.getProjects();
        //TODO: load saved games
        savedProjects = new ArrayList<>();
        populateSelectionMenus(newProjects, savedProjects);
    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    private ArrayList<Project> newProjects = new ArrayList<>();
    private ArrayList<Project> savedProjects = new ArrayList<>();

    public void populateSelectionMenus(ArrayList<Project> newProjects, ArrayList<Project> savedProjects) {

        // convert games to nodes
        ArrayList<Node> newProjectNodes = projectsToNodes(newProjects);
        ArrayList<Node> savedProjectNodes = projectsToNodes(savedProjects);

        // populate the menus
        newProjectNodes.forEach((n) -> {

            n.setOnMouseClicked(mouseEvent -> {

                selectGameButton.setDisable(false);
                selectGameButton.setText("Start New Game");
                Style.setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 320, 70);

                selectGameButton.setOnMouseClicked(event -> {
                    setSelectedGame((VBox)n);
                    if (BasicApplication.getProject().getIntiGS().getAllPlayers().size() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error!");
                        alert.setHeaderText("This game does not yet have a player:");
                        alert.setContentText("Please add a player to this game, then proceed.");
                        alert.showAndWait();
                    } else {
                        SetupController controller = new SetupController();
                        controller.initialize(stage);
                    }
                });

                // deselect all rectangles
                ObservableList<Node> children = newGamesHBox.getChildren();

                children.forEach((n1) -> n1.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px"));
                children = savedGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px"));

                // select this rectangles
                n.setStyle("-fx-border-color: " + GlobalCSSValues.accent + ";-fx-border-style: solid; -fx-border-width: 3px");

                // focus the node
                //n.requestFocus();
            });
            newGamesHBox.getChildren().add(n);
        });

        savedProjectNodes.forEach((n) -> {

            n.setOnMouseClicked(mouseEvent -> {

                selectGameButton.setDisable(false);
                selectGameButton.setText("Load Saved Game");
                Style.setStyle(selectGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 350, 70);

                selectGameButton.setOnMouseClicked(event -> {
                    setSelectedGame((VBox)n);
                    PlayController controller = new PlayController();
                    controller.initialize(stage);
                });

                // deselect all rectangles
                ObservableList<Node> children = newGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px"));
                children = savedGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: " + GlobalCSSValues.text + ";-fx-border-style: solid; -fx-border-width: 3px"));

                // select this rectangles
                n.setStyle("-fx-border-color: " + GlobalCSSValues.accent + ";-fx-border-style: solid; -fx-border-width: 3px");

                // focus the node
                //n.requestFocus();
            });
            savedGamesHBox.getChildren().add(n);
        });

    }

    public ArrayList<Node> projectsToNodes(ArrayList<Project> projects) {

        ArrayList<Node> nodes = new ArrayList<>();

        projects.forEach(
                (n) -> {

                    VBox tempVBox = new VBox();
                    tempVBox.setAlignment(Pos.CENTER);

                    tempVBox.prefHeightProperty().bind(Bindings.subtract(newGamesScrollPane.heightProperty(), 20));
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

    private String invertColor(String myColorString) {

        Color invertedColor = Color.valueOf(myColorString).invert();

        // 6 symbols in capital letters.
        String hex3 = Integer.toHexString(invertedColor.hashCode()).substring(0, 6).toUpperCase();

        return "#" + hex3;
    }
}

package org.scenebuilder.scenebuilder;
import javafx.geometry.Rectangle2D;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.event.*;
import com.jfoenix.controls.JFXDrawer;



import java.io.IOException;

public class PlayFXMLController {

    @FXML
    private Label playGameLabel;
    @FXML
    private Label playSetupLabel;
    @FXML
    private JFXDrawer deckDrawer;
    @FXML
    private JFXDrawer rngDrawer;

    private Stage stage;

    private SetupData setupData;
    private DummyGame activeGame;

    @FXML
    public void initialize() {
        // load relevant data
        setupData = BasicApplication.getSetupData();
        activeGame = BasicApplication.getSelectedGame();

        playGameLabel.setText(activeGame.toString());
        playSetupLabel.setText(setupData.toString());

        initializeDeckDrawer(deckDrawer);
        initializeRNGDrawer(rngDrawer);
    }

    //A method to add all the decks to the deck slider
    private static void initializeDeckDrawer(JFXDrawer drawer) {
        drawer.open();
        //drawer.setOpacity(0.0);
        drawer.toggle();

        drawer.setMinWidth(0.0);

        ScrollPane decksPane = new ScrollPane();
        decksPane.setPrefHeight(209.0);
        decksPane.setPrefWidth(800);
        decksPane.setMaxWidth(decksPane.getPrefWidth());
        decksPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        decksPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox decks = new HBox();
        decks.setSpacing(5);
        StackPane stack1 = new StackPane();
        StackPane stack2 = new StackPane();
        StackPane stack3 = new StackPane();
        StackPane stack4 = new StackPane();

        Rectangle rect1 = new Rectangle(100, 200);
        Rectangle rect2 = new Rectangle(100, 200);
        Rectangle rect3 = new Rectangle(100, 200);
        Rectangle rect4 = new Rectangle(100, 200);

        rect1.setStyle("-fx-fill: red;");
        rect2.setStyle("-fx-fill: blue");
        rect3.setStyle("-fx-fill: green");
        rect4.setStyle("-fx-fill: yellow");

        Text text1 = new Text("Deck 1");
        Text text2 = new Text("Deck 2");
        Text text3 = new Text("Deck 3");
        Text text4 = new Text("Deck 4");

        stack1.getChildren().addAll(rect1, text1);
        stack2.getChildren().addAll(rect2, text2);
        stack3.getChildren().addAll(rect3, text3);
        stack4.getChildren().addAll(rect4, text4);

        decks.getChildren().addAll(stack1, stack2, stack3, stack4);

        decksPane.setContent(decks);

        drawer.setSidePane(decksPane);
    }

    public static void initializeRNGDrawer(JFXDrawer drawer) {
        drawer.open();
        //drawer.setOpacity(0.0);
        drawer.toggle();

        drawer.setMinWidth(0.0);

        ScrollPane decksPane = new ScrollPane();
        decksPane.setPrefHeight(209.0);
        decksPane.setPrefWidth(800);
        decksPane.setMaxWidth(decksPane.getPrefWidth());
        decksPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        decksPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox RNGs = new HBox();
        RNGs.setSpacing(20);
        StackPane stack1 = new StackPane();

        Rectangle rect1 = new Rectangle(100, 100);

        rect1.setStyle("-fx-fill: white;");

        Text text1 = new Text("Dice");
        text1.setStyle("-fx-font-size: 25; -fx-border-color: black;");
        stack1.getChildren().addAll(rect1, text1);
        stack1.setAlignment(Pos.CENTER);
        RNGs.getChildren().addAll(stack1);
        RNGs.setAlignment(Pos.CENTER);
        decksPane.setContent(RNGs);

        drawer.setSidePane(decksPane);
    }
    @FXML
    public void exitFromPlay(ActionEvent event, Stage baseStage) throws IOException {
        switchScene(event, "mainFXML.fxml", baseStage);
    }

    @FXML
    public void switchScene(ActionEvent event, String nextScene, Stage baseStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        if (baseStage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = baseStage;
        }
        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }

    @FXML
    public void slideDeckDrawer(MouseEvent event) {
        Label label = (Label) event.getSource();
        if (deckDrawer.isClosed()) {
            label.setText("");
            deckDrawer.open();
            //drawer.setOpacity(0.0);
        } else {
            //Automate this in some way, maybe some object that stores id and text and then have this
            //for all objects so that you can get original text back
            if (label.getId().equals("buttonDecks")) {
                label.setText("Decks");
            }
            if (label.getId().equals("buttonRNG")) {
                label.setText("RNG");
            }
            deckDrawer.close();
            //drawer.setOpacity(1.0);
        }
    }
    @FXML
    public void slideRNGDrawer(MouseEvent event) {
        Label label = (Label) event.getSource();
        if (rngDrawer.isClosed()) {
            label.setText("");
            rngDrawer.open();
            //drawer.setOpacity(0.0);
        } else {
            //Automate this in some way, maybe some object that stores id and text and then have this
            //for all objects so that you can get original text back
            if (label.getId().equals("buttonDecks")) {
                label.setText("Decks");
            }
            if (label.getId().equals("buttonRNG")) {
                label.setText("RNG");
            }
            rngDrawer.close();
            //drawer.setOpacity(1.0);
        }
    }










    @FXML
    public void switchPauseResume(MouseEvent event) {
        Button curButton = (Button) event.getTarget();
        String curText = curButton.getText();
        if (curText.equals("Pause")) {
            //do things to pause game
            curButton.setText("Resume");
            curButton.setStyle("-fx-font-size: 16; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #00FF00, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4;");
        }
        if (curText.equals("Resume")) {
            //do things to resume game
            curButton.setText("Pause");
            curButton.setStyle("-fx-font-size: 16; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4;");
        }
    }

    public class Popup {
        public boolean saved = false;
        public void setButtonSize(Button button, float prefWidth, float prefHeight, int fontSize) {
            button.setPrefWidth(prefWidth);
            button.setPrefHeight(prefHeight);

            button.setMinWidth(button.getPrefWidth());
            button.setMaxWidth(button.getPrefWidth());
            button.setMinHeight(button.getPrefHeight());
            button.setMaxHeight(button.getPrefHeight());

            button.setStyle("-fx-font-size: "+fontSize+"; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4;");
        }
        public void displayRestart(Stage baseStage, Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();

            Label restartMessage = new Label("Are you sure you want to restart? Your progress in the current game will be lost.");
            restartMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            restartMessage.setWrapText(true);
            restartMessage.setAlignment(Pos.CENTER);

            borderPane.setAlignment(restartMessage, Pos.CENTER);
            borderPane.setCenter(restartMessage);

            HBox buttons = new HBox(10);

            Button yes = new Button("Yes");
            setButtonSize(yes, 70, 30, 15);
            Button no = new Button("No");
            setButtonSize(no, 70, 30, 15);

            //Change these actions to actually handle restarting
            yes.setOnAction(e-> {
                popupWindow.close();
                parentPopup.close();
            });
            no.setOnAction(e->popupWindow.close());

            buttons.getChildren().addAll(yes);
            buttons.getChildren().addAll(no);

            buttons.setAlignment(Pos.CENTER);
            borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
            borderPane.setBottom(buttons);
            Scene exitScene = new Scene(borderPane, 300, 250);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayExitWithoutSave(Stage baseStage, Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();

            Label exitMessage = new Label("Are you sure you want to exit? Your progress will be lost.");
            exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            exitMessage.setWrapText(true);
            exitMessage.setAlignment(Pos.CENTER);

            borderPane.setAlignment(exitMessage, Pos.CENTER);
            borderPane.setCenter(exitMessage);

            HBox buttons = new HBox(10);

            Button yes = new Button("Yes");
            setButtonSize(yes, 70, 30, 15);
            Button no = new Button("No");
            setButtonSize(no, 70, 30, 15);

            yes.setOnAction(e-> {
                popupWindow.close();
                parentPopup.close();
                try {
                    exitFromPlay(e, baseStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            no.setOnAction(e->popupWindow.close());

            buttons.getChildren().addAll(yes);
            buttons.getChildren().addAll(no);

            buttons.setAlignment(Pos.CENTER);
            borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
            borderPane.setBottom(buttons);
            Scene exitScene = new Scene(borderPane, 300, 250);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayExit(Stage baseStage) {
            Stage popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            Button saveButton = new Button("Save");
            setButtonSize(saveButton, 170, 80, 40);
            Button exitButton = new Button("Exit");
            setButtonSize(exitButton, 170, 80, 40);
            Button restartButton = new Button("Restart");
            setButtonSize(restartButton, 170, 80, 40);
            saveButton.setOnAction(e->{
                saved = true;
                System.out.println("Save");
            });
            exitButton.setOnAction(e->{
                if (!saved) {
                    displayExitWithoutSave(baseStage, popupWindow);
                } else {
                    popupWindow.close();
                    try {
                        exitFromPlay(e, baseStage);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            restartButton.setOnAction(e->{
                displayRestart(baseStage, popupWindow);
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(saveButton);
            layout.getChildren().addAll(exitButton);
            layout.getChildren().addAll(restartButton);
            layout.setAlignment(Pos.CENTER);
            Scene exitScene = new Scene(layout, 300, 400);
            exitScene.setFill(Color.MAROON);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
    }

    @FXML
    public void displayPopup(MouseEvent event) {
        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Popup popup = new Popup();
        popup.displayExit(curStage);
    }
}


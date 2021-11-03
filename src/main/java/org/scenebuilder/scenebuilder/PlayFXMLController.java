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
    private JFXDrawer drawer;

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

        initializeDrawer(drawer);
    }

    //A method to add all the decks to the deck slider
    private static void initializeDrawer(JFXDrawer drawer) {

        drawer.setMinWidth(0.0);

        ScrollPane decksPane = new ScrollPane();
        decksPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        decksPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        decksPane.setPrefHeight(209.0);
        decksPane.setPrefWidth(400.0);
        decksPane.setMaxWidth(400.0);

        HBox decks = new HBox();
        decks.getChildren().add(new Rectangle());
        decksPane.setContent(decks);

        drawer.setSidePane(decksPane);
        drawer.close();
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
    public void slideDrawer() {
        if (drawer.isOpened()) {
            drawer.close();
        } else {
            drawer.open();
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


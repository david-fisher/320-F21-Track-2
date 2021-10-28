package org.scenebuilder.scenebuilder;
/*
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
*/
import javafx.geometry.Rectangle2D;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.event.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayFXMLController {

    private Stage stage;

    @FXML
    public void initialize() {

    }

    @FXML
    public void backFromPlay(ActionEvent event) throws IOException {
        switchScene(event, "setupFXML.fxml");
    }

    @FXML
    public void exitFromPlay(ActionEvent event) throws IOException {
        switchScene(event, "mainFXML.fxml");
    }

    @FXML
    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchPauseResume(ActionEvent event) {
        Button curButton = (Button) event.getTarget();
        String curText = curButton.getText();
        if (curText.equals("Pause")) {
            //do things to pause game
            curButton.setText("Resume");
            curButton.setStyle("-fx-font-size: 16; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #00FF00, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4; -fx-skin: ButtonSkin;");
        }
        if (curText.equals("Resume")) {
            //do things to resume game
            curButton.setText("Pause");
            curButton.setStyle("-fx-font-size: 16; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4; -fx-skin: ButtonSkin;");
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

            button.setStyle("-fx-font-size: "+fontSize+"; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4; -fx-skin: ButtonSkin;");
        }
        public void displayRestart(){
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

            yes.setOnAction(e->popupWindow.close());
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
        public void displayExitWithoutSave(){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();

            Label restartMessage = new Label("Are you sure you want to exit? Your progress will be lost.");
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

            yes.setOnAction(e->popupWindow.close());
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
        public void displayExit(){
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
                    displayExitWithoutSave();
                } else {
                    System.out.println("Exit");
                    popupWindow.close();
                }
            });
            restartButton.setOnAction(e->{
                displayRestart();
                popupWindow.close();
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
    public void displayPopup(ActionEvent event) {
        Popup popup = new Popup();
        popup.displayExit();
    }
}

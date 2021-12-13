package org.GamePlay.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.GamePlay.GlobalCSSValues;

public class Popup extends PlayController {
    private Stage stage;
    Label yes;
    Label no;
    public boolean saved = false;

    public Popup(Stage s) {
        this.stage = s;
    }

    public void displayRestart(Stage parentPopup){
        Stage popupWindow = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);

        Label restartMessage = new Label("Are you sure you want to restart? Your progress in the current game will be lost.");
        restartMessage.setTextFill(Color.valueOf(GlobalCSSValues.text));
        restartMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
        restartMessage.setWrapText(true);
        restartMessage.setAlignment(Pos.CENTER);
        restartMessage.setPrefWidth(250);

        borderPane.setAlignment(restartMessage, Pos.CENTER);
        borderPane.setCenter(restartMessage);

        HBox buttons = new HBox(10);

        //TODO: Change these actions to actually handle restarting
        yes.setOnMouseClicked(e-> {
            popupWindow.close();
            parentPopup.close();
        });
        no.setOnMouseClicked(e -> {
            popupWindow.close();
        });

        buttons.getChildren().addAll(yes, no);
        buttons.setMargin(yes, new Insets(0, 5, 10, 0));
        buttons.setMargin(no, new Insets(0, 0, 10, 5));

        buttons.setAlignment(Pos.CENTER);
        borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
        borderPane.setBottom(buttons);
        Scene exitScene = new Scene(borderPane, 300, 250);
        popupWindow.setScene(exitScene);
        popupWindow.showAndWait();
    }

    public void displayExitWithoutSave(Stage parentPopup){
        Stage popupWindow = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);

        Label exitMessage = new Label("Are you sure you want to exit to desktop? Your progress will be lost.");
        exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
        exitMessage.setTextFill(Color.valueOf(GlobalCSSValues.text));
        exitMessage.setWrapText(true);
        exitMessage.setAlignment(Pos.CENTER);
        exitMessage.setPrefWidth(250);

        borderPane.setAlignment(exitMessage, Pos.CENTER);
        borderPane.setCenter(exitMessage);

        HBox buttons = new HBox(10);

        yes.setOnMouseClicked(e-> {
            popupWindow.close();
            parentPopup.close();
            clearPlayParent();
            exitFromPlay();
        });
        no.setOnMouseClicked(e -> {
            popupWindow.close();
        });

        buttons.getChildren().addAll(yes, no);
        buttons.setMargin(yes, new Insets(0, 5, 10, 0));
        buttons.setMargin(no, new Insets(0, 0, 10, 5));

        buttons.setAlignment(Pos.CENTER);
        borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
        borderPane.setBottom(buttons);
        Scene exitScene = new Scene(borderPane, 300, 250);
        popupWindow.setScene(exitScene);
        popupWindow.showAndWait();
    }

    public void displayMainMenuWithoutSave(Stage parentPopup){
        Stage popupWindow = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);

        Label exitMessage = new Label("Are you sure you want to return to the Main Menu? Your progress will be lost.");
        exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
        exitMessage.setTextFill(Color.valueOf(GlobalCSSValues.text));
        exitMessage.setWrapText(true);
        exitMessage.setAlignment(Pos.CENTER);
        exitMessage.setPrefWidth(250);

        borderPane.setAlignment(exitMessage, Pos.CENTER);
        borderPane.setCenter(exitMessage);

        HBox buttons = new HBox(10);

        yes.setOnMouseClicked(e-> {
            popupWindow.close();
            parentPopup.close();
            clearPlayParent();
            mainMenuFromPlay(stage);
        });
        no.setOnMouseClicked(e -> {
            popupWindow.close();
        });

        buttons.getChildren().addAll(yes, no);
        buttons.setMargin(yes, new Insets(0, 5, 10, 0));
        buttons.setMargin(no, new Insets(0, 0, 10, 5));

        buttons.setAlignment(Pos.CENTER);
        borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
        borderPane.setBottom(buttons);
        Scene exitScene = new Scene(borderPane, 300, 250);
        popupWindow.setScene(exitScene);
        popupWindow.showAndWait();
    }

    public void displaySettingsPopup() {
        Stage popupWindow = new Stage();

        yes = new Label("Yes");
        yes.setStyle("-fx-border-radius: 2 2 2 2; " +
                "-fx-background-radius: 2 2 2 2; " +
                "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                "; -fx-text-fill: " + GlobalCSSValues.buttonText +
                "; -fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
        yes.setAlignment(Pos.CENTER);
        yes.setPrefWidth(100);
        yes.setPrefHeight(35);
        no = new Label("No");
        no.setStyle(yes.getStyle());
        no.setAlignment(Pos.CENTER);
        no.setPrefWidth(yes.getPrefWidth());
        no.setPrefHeight(yes.getPrefHeight());
        no.setStyle(yes.getStyle());

        outlineYesNo(yes);
        outlineYesNo(no);

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        Label saveButton = new Label("Save");
        setStyle(saveButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
        saveButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        Label exitButton = new Label("Exit");
        setStyle(exitButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
        exitButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        Label restartButton = new Label("Restart");
        setStyle(restartButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
        restartButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        Label mainMenuButton = new Label("Main Menu");
        setStyle(mainMenuButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
        mainMenuButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        saveButton.setOnMouseClicked(e->{
            saved = true;
            System.out.println("Save");
        });

        exitButton.setOnMouseClicked(e->{
            if (!saved) {
                displayExitWithoutSave(popupWindow);
            } else {
                clearPlayParent();
                popupWindow.close();
                exitFromPlay();
            }
        });

        restartButton.setOnMouseClicked(e->{
            displayRestart(popupWindow);
        });

        mainMenuButton.setOnMouseClicked(e -> {
            if (!saved) {
                displayMainMenuWithoutSave(popupWindow);
            } else {
                clearPlayParent();
                popupWindow.close();
                mainMenuFromPlay(stage);
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
        layout.getChildren().addAll(saveButton, restartButton, mainMenuButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        Scene exitScene = new Scene(layout, 350, 400);
        exitScene.setFill(Color.MAROON);
        popupWindow.setScene(exitScene);
        popupWindow.showAndWait();
    }

    public void outlineYesNo(Label label) {
        label.setOnMouseEntered(e -> {
            label.setStyle("-fx-border-radius: 2 2 2 2; " +
                    "-fx-background-radius: 2 2 2 2; " +
                    "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                    "; -fx-text-fill: " + GlobalCSSValues.buttonText +
                    "; -fx-font-size: 25; -fx-font-family: serif; -fx-border-color: "+ GlobalCSSValues.accent);
        });

        label.setOnMouseExited(e -> {
            label.styleProperty().setValue("-fx-border-radius: 2 2 2 2; " +
                    "-fx-background-radius: 2 2 2 2; " +
                    "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                    "; -fx-text-fill: " + GlobalCSSValues.buttonText +
                    "; -fx-font-size: 25; -fx-font-family: serif; -fx-border-color: Black;");
        });
    }
}

package org.GamePlay.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.GameObjects.objects.Project;
import org.GameObjects.objects.Savable;
import org.GamePlay.BasicApplication;
import org.GamePlay.GlobalCSSValues;
import org.RuleEngine.engine.GameState;

import java.util.ArrayList;

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
            clearPlayParent();
            clearParents();
            restartPlay(stage);
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
            clearParents();
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
            clearParents();
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
        Style.setStyle(yes, "25", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,100, 35);
        yes.setTextAlignment(TextAlignment.CENTER);

        no = new Label("No");
        Style.setStyle(no, "25", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 100, 35);
        no.setTextAlignment(TextAlignment.CENTER);

        popupWindow.initModality(Modality.APPLICATION_MODAL);

        Label saveButton = new Label("Save and Exit");
        Style.setStyle(saveButton, "30", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,170, 80);
        saveButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        Label exitButton = new Label("Exit");
        Style.setStyle(exitButton, "30", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,170, 80);
        exitButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        Label restartButton = new Label("Restart");
        Style.setStyle(restartButton, "30", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,170, 80);
        restartButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        Label mainMenuButton = new Label("Main Menu");
        Style.setStyle(mainMenuButton, "30", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,170, 80);
        mainMenuButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

        saveButton.setOnMouseClicked(e->{
            saved = true;
            Project project = BasicApplication.getProject();
            clearPlayParent();
            clearParents();
            GameState temp = BasicApplication.getGameState();
            System.out.println(temp.getRegistry("currPlayer"));
            Savable.initDB();
            ArrayList<Project> projects = Savable.getProjects();
            for(int i=0; i < projects.size(); i++) {
                Project tempProject = projects.get(i);
                if (tempProject.getProjectName().equals(BasicApplication.getProject().getProjectName())) {
                    project.setInitGS(tempProject.getInitGS());
                    System.out.println(tempProject.getInitGS().getRegistry("currPlayer"));
                    tempProject.setSavedGS(temp);
                }
            }
            Savable.closeDB();
            popupWindow.close();
            mainMenuFromPlay(stage);
        });

        exitButton.setOnMouseClicked(e->{
            if (!saved) {
                displayExitWithoutSave(popupWindow);
            } else {
                clearPlayParent();
                clearParents();
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
                clearParents();
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
}

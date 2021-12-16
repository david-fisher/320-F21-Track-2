package org.GamePlay.controllers;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.GamePlay.BasicApplication;
import org.GamePlay.GlobalCSSValues;

public class ScreenController {

    AnchorPane anchorPane;
    VBox screenVBox;
    private void initAnchorPane() {

        anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: black");

        //anchorPane.prefHeightProperty().bind(stage.heightProperty());
        //anchorPane.prefWidthProperty().bind(stage.widthProperty());

        screenVBox = new VBox();
        AnchorPane.setTopAnchor(screenVBox, 3.0);
        AnchorPane.setBottomAnchor(screenVBox, 3.0);
        AnchorPane.setLeftAnchor(screenVBox, 3.0);
        AnchorPane.setRightAnchor(screenVBox, 3.0);
        screenVBox.setStyle("-fx-background-color:" + GlobalCSSValues.background);


        if(!BasicApplication.getSettingsObject().getIsFullScreen()) {

            Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();   // screen - task bar

            anchorPane.setPrefWidth(screenDimensions.getWidth());
            anchorPane.setPrefHeight(screenDimensions.getHeight());
        } else {

            Rectangle2D fullScreenDimensions = Screen.getPrimary().getBounds();     // full screen

            anchorPane.setPrefWidth(fullScreenDimensions.getWidth());
            anchorPane.setPrefHeight(fullScreenDimensions.getHeight());

        }
        anchorPane.getChildren().add(screenVBox);
    }

    protected Stage stage;

    public void initialize(Stage stage) {

        this.stage = stage;
        stage = BasicApplication.updateStage(stage);

        initAnchorPane();

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        stage.setMaximized(true);
        stage.show();
    }
}

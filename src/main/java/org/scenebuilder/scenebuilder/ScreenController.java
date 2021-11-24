package org.scenebuilder.scenebuilder;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenController {

    AnchorPane anchorPane;
    VBox screenVBox;
    private void initAnchorPane() {

        anchorPane = new AnchorPane();
        anchorPane.prefHeightProperty().bind(stage.heightProperty());
        anchorPane.prefWidthProperty().bind(stage.widthProperty());

        screenVBox = new VBox();
        AnchorPane.setTopAnchor(screenVBox, 3.0);
        AnchorPane.setBottomAnchor(screenVBox, 3.0);
        AnchorPane.setLeftAnchor(screenVBox, 3.0);
        AnchorPane.setRightAnchor(screenVBox, 3.0);
        screenVBox.setStyle("-fx-border-color: black;");

        //Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();   // screen - task bar
        //Rectangle2D fullScreenDimensions = Screen.getPrimary().getBounds();     // full screen

        //anchorPane.setPrefWidth(screenDimensions.getWidth());
        //anchorPane.setPrefHeight(screenDimensions.getHeight());

        anchorPane.getChildren().add(screenVBox);
    }

    protected Stage stage;

    public void initialize(Stage stage) {

        this.stage = stage;

        initAnchorPane();

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
    }
}

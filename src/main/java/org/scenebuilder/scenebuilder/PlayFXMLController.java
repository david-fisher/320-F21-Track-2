package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class PlayFXMLController {

    private Stage stage;

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
            curButton.setStyle("-fx-font-size:13; -fx-background-color: #00FF00; -fx-border-color: #000000; -fx-background-insets: 0;");
        }
        if (curText.equals("Resume")) {
            //do things to resume game
            curButton.setText("Pause");
            curButton.setStyle("-fx-font-size:15; -fx-background-color: #D3D3D3; -fx-border-color: #000000; -fx-background-insets: 0;");
        }
    }
}

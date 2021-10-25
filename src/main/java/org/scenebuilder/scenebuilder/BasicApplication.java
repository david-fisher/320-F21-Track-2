package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class BasicApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        // load fxml file (which specifies the controller)
        Parent root = FXMLLoader.load(getClass().getResource("mainFXML.fxml"));

        // create new instance of the controller class
        // inject all fx:id tagged objects from fxml file
        // and marked with @FXML annotation in controller

        // full screen dimensions
        //Rectangle2D screenDimensions = Screen.getPrimary().getBounds();
        //double width = screenDimensions.getWidth();
        //double height = screenDimensions.getHeight();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        //stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
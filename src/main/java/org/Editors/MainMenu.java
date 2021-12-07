package org.Editors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class MainMenu extends Application {
    public static Stage stage;

    public static void main(String[] args) {
      launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        URL location = getClass().getResource("../../resources/MainMenuScreen.fxml");
        try {
            Parent root = (Parent)FXMLLoader.load(location);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}

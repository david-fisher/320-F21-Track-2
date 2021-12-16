package org.GameEditor.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			URL location = getClass().getResource("TemplateOrFresh.fxml");
			Parent root = FXMLLoader.load(location);
			Scene scene = new Scene(root,900,600);
			
			primaryStage.sizeToScene();
			primaryStage.setTitle("Game Creator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

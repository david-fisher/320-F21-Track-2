package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			Parent root = FXMLLoader.load(getClass().getResource("GameCreator.fxml"));
			Scene scene = new Scene(root,900,600);
			
			primaryStage.sizeToScene();
			primaryStage.setTitle("Game Creator");
			primaryStage.setScene(scene);
			//primaryStage.setWidth(900);
			//primaryStage.setHeight(600);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

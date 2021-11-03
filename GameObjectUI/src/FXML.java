package GameObjectUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.net.URL;
import java.util.ResourceBundle;
import GameObjectUI.controllers.MyController;

public class FXML extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL location = getClass().getResource("../game_object_ui.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = (Parent)fxmlLoader.load();
        MyController controller = (MyController)fxmlLoader.getController();

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("FXML");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

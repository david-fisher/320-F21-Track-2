package editors.game_object_ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.net.URL;
import java.util.ResourceBundle;
import editors.game_object_ui.controllers.GameObjectUIController;

public class GameObjectUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL location = getClass().getResource("../GameObjectUI.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = (Parent)fxmlLoader.load();
        GameObjectUIController controller = (GameObjectUIController)fxmlLoader.getController();

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("FXML");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

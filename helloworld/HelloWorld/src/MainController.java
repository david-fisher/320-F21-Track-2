import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

public class MainController {
    @FXML
    private ListView<String> listBox;

    private int counter = 0;

    public void next(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("second.fxml"));
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Main app");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void add(ActionEvent event) throws Exception {
        listBox.getItems().add("Object " + Integer.toString(++counter));
    }
    public void quit(ActionEvent event) throws Exception {
        System.exit(0);
    }
}

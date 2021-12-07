package application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaveAsController {

    @FXML
    private Button cancel;

    @FXML
    private TextField name;

    @FXML
    private Button save;

    @FXML
    void cancelButton(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveButton(ActionEvent event) {

    }

}

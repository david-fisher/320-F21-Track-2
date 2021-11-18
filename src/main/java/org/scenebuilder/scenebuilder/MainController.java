package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController extends ScreenController {

    Button playButton;
    Button newButton;
    Button editButton;
    Button exitButton;
    private void initButtons() {

        playButton = new Button();
        playButton.setText("Play Game");
        playButton.setFont(new Font(36));
        VBox.setMargin(playButton, new Insets(10, 10, 10, 10));

        playButton.setOnAction(event -> {
            playFromMain();
        });

        newButton = new Button();
        newButton.setText("New Game");
        newButton.setFont(playButton.getFont());
        VBox.setMargin(newButton, VBox.getMargin(playButton));

        newButton.setOnAction(event -> {
            try {
                newFromMain(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton = new Button();
        editButton.setText("Edit Game");
        editButton.setFont(playButton.getFont());
        VBox.setMargin(editButton, VBox.getMargin(playButton));

        editButton.setOnAction(event -> {
            try {
                editFromMain(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton = new Button();
        exitButton.setText("Exit To Desktop");
        exitButton.setFont(playButton.getFont());
        VBox.setMargin(exitButton, VBox.getMargin(playButton));

        exitButton.setOnAction(event -> {
            exitFromMain(event);
        });

        playButton.prefWidthProperty().bind(exitButton.widthProperty());
        newButton.prefWidthProperty().bind(exitButton.widthProperty());
        editButton.prefWidthProperty().bind(exitButton.widthProperty());

        screenVBox.getChildren().addAll(playButton, newButton, editButton, exitButton);
    }

    public void initialize(Stage stage) {

        super.initialize(stage);

        screenVBox.setAlignment(Pos.CENTER);
        initButtons();
    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    public void playFromMain() {

        // update playable and saved games in selection panes
        BasicApplication.loadNewGames();
        BasicApplication.loadSavedGames();

        SelectionController controller = new SelectionController();
        controller.initialize(stage);
    }

    public void newFromMain(ActionEvent event) throws IOException {
        System.out.println("New");
    }

    public void editFromMain(ActionEvent event) throws IOException {
        System.out.println("Edit");
    }

    public void exitFromMain(ActionEvent event) {
        System.exit(0);
    }

    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }
}

package org.GamePlay;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.*;
import org.GamePlay.controllers.MainController;

import java.util.ArrayList;
import java.util.List;

public class BasicApplication extends Application {

    private static SettingsObject settingsObject = new SettingsObject();

    private static Project project;

    // setters
    public static void setSettingsObject(SettingsObject obj) { settingsObject = obj; }

    public static SettingsObject getSettingsObject() { return settingsObject; }

    public static int[] calculateScreenDimensions() {

        int[] screenDimensions = new int[2];

        Rectangle2D dims = Screen.getPrimary().getBounds();
        screenDimensions[0] = (int)dims.getWidth();
        screenDimensions[1] = (int)dims.getHeight();

        return screenDimensions;
    }

    public static void initScreenStyle(Stage stage) {

        if(settingsObject.getIsFullScreen() == true) {
            stage.initStyle(StageStyle.UNDECORATED); // remove title bar
        } else {
            stage.initStyle(StageStyle.DECORATED); // title bar intact
        }
    }
    public static void initScreenDimensions(Stage stage) {

        if(settingsObject.getIsFullScreen() == true) {
            stage.setMaximized(true);
        } else {

            // theoretically keep the stage from becoming unuseable by going off the monitor
//            int[] maxScreenDimensions = calculateScreenDimensions();
//            stage.setMaxWidth(maxScreenDimensions[0]);
//            stage.setMaxHeight(maxScreenDimensions[1]);
        }
    }

    public static Stage restartStage(Stage stage) {
        stage.close();
        Stage newStage = new Stage();
        initScreenStyle(newStage);
        initScreenDimensions(newStage);
        return newStage;
    }

    public static Stage newStage(Stage stage) {
        stage.close();
        Stage newStage = new Stage();
        initScreenDimensions(newStage);
        return newStage;
    }

    public static Stage updateStage(Stage stage) {
        initScreenDimensions(stage);
        return stage;
    }

    public static void setProject(Project p) { project = p; }
    public static Project getProject() { return project; }

    @Override
    public void start(Stage stage) {

        // calculate default screen dimensions
        //int[] screenDimensions = calculateScreenDimensions();
        //System.out.println("Your Screen Dimensions: " + screenDimensions[0] + " x " + screenDimensions[1]);

        //stage = updateStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);

        // initialize controller and set initial scene
        MainController controller = new MainController();
        controller.initialize(stage);
    }

    public static void main(String[] args) {

        // stuff done before the app launches
        GlobalCSSValues.initDefaultPalette();

        launch();
    }

}

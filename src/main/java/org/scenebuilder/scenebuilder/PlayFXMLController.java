package org.scenebuilder.scenebuilder;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PlayFXMLController {

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private StackPane rootPane;

    /*
    // @Override
    public void initialize() {
        rootPane.setOpacity(0);
        makeInTransition();
    }
    */

    /*
    public void makeInTransition(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    @FXML
    private void loadSecondScene(ActionEvent event) throws IOException {




    }

    */

}

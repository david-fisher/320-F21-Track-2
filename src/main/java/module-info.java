module org.scenebuilder.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens org.scenebuilder.scenebuilder to javafx.fxml;
    exports org.scenebuilder.scenebuilder;
}
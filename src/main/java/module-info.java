module org.scenebuilder.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;


    opens org.scenebuilder to javafx.fxml;
    exports org.scenebuilder;
    exports org.scenebuilder.dummy;
    opens org.scenebuilder.dummy to javafx.fxml;
    exports org.scenebuilder.controllers;
    opens org.scenebuilder.controllers to javafx.fxml;
}
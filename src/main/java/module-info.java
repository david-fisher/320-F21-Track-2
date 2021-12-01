module org.scenebuilder.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;


    opens org.scenebuilder.scenebuilder to javafx.fxml;
    exports org.scenebuilder.scenebuilder;
    exports org.scenebuilder.scenebuilder.dummy;
    opens org.scenebuilder.scenebuilder.dummy to javafx.fxml;
}
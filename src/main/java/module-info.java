module org.scenebuilder.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.jfoenix;
    requires java.desktop;
    requires org.yaml.snakeyaml;


    opens org.scenebuilder.scenebuilder to javafx.fxml;
    exports org.scenebuilder.scenebuilder;
//    exports org.scenebuilder.scenebuilder.dummy;
    
    opens org.GameObjects.objects;
    opens org.RuleEngine.nodes;
    opens org.RuleEngine.engine;
    opens org.scenebuilder.scenebuilder.dummy to javafx.fxml;
}
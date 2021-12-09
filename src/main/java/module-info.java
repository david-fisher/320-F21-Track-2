module org {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.jfoenix;
    requires java.desktop;
    requires org.yaml.snakeyaml;


    opens org.scenebuilder to javafx.fxml;
    exports org.scenebuilder;
//    exports org.scenebuilder.scenebuilder.dummy;
    
    opens org.GameObjects.objects;
    opens org.RuleEngine.nodes;
    opens org.RuleEngine.engine;
}
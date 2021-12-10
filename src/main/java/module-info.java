module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;
    requires javafx.web;


    opens org.scenebuilder to javafx.fxml;
    exports org.scenebuilder;

    opens org.Editors to javafx.fxml;
    exports org.Editors to javafx.graphics;

    opens org.GameObjects.objects;
    opens org.RuleEngine.nodes;
    opens org.RuleEngine.engine;
}
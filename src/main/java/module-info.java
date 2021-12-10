module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;
//
    exports org.scenebuilder;
//    opens org.Editors to javafx.fxml;
    exports org.Editors;

    opens org.Editors.controllers;
    exports org.Editors.controllers;

//    opens org.GameObjects.objects;
//    opens org.RuleEngine.nodes;
//    opens org.RuleEngine.engine;
}
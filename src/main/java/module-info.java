module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;
    requires javafx.web;

    opens org.GameEditor.application;
    exports org.GameEditor.application;

    opens org.scenebuilder.controllers;
    exports org.scenebuilder.controllers;

    opens org.scenebuilder to javafx.fxml;
    exports org.scenebuilder;

    opens org.Editors.controllers;
    exports org.Editors.controllers;

    opens org.Editors to javafx.fxml;
    exports org.Editors to javafx.graphics;

    opens org.GameObjects.objects;

    opens org.RuleEngine.nodes;
    exports org.RuleEngine.engine;
//    opens org.RuleEngine.engine;
}
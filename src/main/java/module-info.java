module org {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;
    requires javafx.web;

    opens org.GameEditor.application;
    exports org.GameEditor.application;

    opens org.GamePlay.controllers;
    exports org.GamePlay.controllers;

    opens org.GamePlay to javafx.fxml;
    exports org.GamePlay;

    opens org.Editors.controllers;
    exports org.Editors.controllers;

    opens org.Editors to javafx.fxml;
    exports org.Editors to javafx.graphics;

    opens org.GameObjects.objects;

    opens org.RuleEngine.nodes;
    exports org.RuleEngine.engine;
//    opens org.RuleEngine.engine;
}
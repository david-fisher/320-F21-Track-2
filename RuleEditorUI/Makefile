fxmodules = javafx.controls,javafx.fxml

all: archive

fxml: src/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

controllers: src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

archive: controllers fxml
	jar cfm GameObjectEditor.jar manifest.txt GameObjectUI/

cmd: controllers fxml

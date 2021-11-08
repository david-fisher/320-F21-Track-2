fxmodules = javafx.controls,javafx.fxml

all: archive

rule: RuleEditorUI/src/*.java RuleEditorUI/src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

gameobj: GameObjectUI/src/*.java GameObjectUI/src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

menu: MainMenu/src/*.java MainMenu/src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

objects: ../GameObjects/objects/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

archive: controllers fxml
	jar cfm GameObjectEditor.jar manifest.txt GameObjectUI/

cmd: objects gameobj rule menu

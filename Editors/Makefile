fxmodules = javafx.controls,javafx.fxml

rule: RuleEditorUI/src/*.java RuleEditorUI/src/controllers/*.java RuleEditor/src/blocks/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

gameobj: GameObjectUI/src/*.java GameObjectUI/src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

menu: MainMenu/src/*.java MainMenu/src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

objects: ../GameObjects/objects/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

archive: controllers fxml
	jar cfm GameObjectEditor.jar manifest.txt GameObjectUI/

run:
	java --module-path $(PATH_TO_FX) --add-modules $(fxmodules) editors.main_menu.MainMenu

cmd: objects gameobj rule menu
debug: objects gameobj rule menu run

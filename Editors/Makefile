fxmodules = javafx.controls,javafx.fxml

rule: RuleEditorUI/src/*.java RuleEditorUI/src/controllers/*.java RuleEditorUI/src/blocks/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

gameobj: GameObjectUI/src/*.java GameObjectUI/src/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

menu: MainMenu/*.java MainMenu/controllers/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

objects: ../GameObjects/objects/*.java
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

archive: objects gameobj rule menu
	jar cfmv Editors.jar manifest.txt editors/ resources/

run:
	java --module-path $(PATH_TO_FX) --add-modules $(fxmodules) editors.main_menu.MainMenu

cmd: objects gameobj rule menu
debug: objects gameobj rule menu run

fxmodules = javafx.controls,javafx.fxml

board = ../scenebuilder/scenebuilder/*.java

dummies = ../scenebuilder/scenebuilder/dummy/*.java

objects = ../GameObjects/objects/*.java

nodes = ../RuleEngine/nodes/*.java 

engine = ../RuleEngine/engine/*.java

controllers = controllers/*.java

menu = MainMenu.java

lib = *.jar

blocks = blocks/*.java

all: $(board) $(dummies) $(objects) $(nodes) $(engine) $(blocks) $(controllers) $(menu)
	javac -d . --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

run:
	java --module-path $(PATH_TO_FX) --add-modules $(fxmodules) org.Editors.MainMenu

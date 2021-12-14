fxmodules = javafx.controls,javafx.fxml,javafx.web
src_path = ../java/org
compile_path = ./

play = $(src_path)/GamePlay/*.java
play_controllers = $(src_path)/GamePlay/controllers/*.java

board = $(src_path)/GameEditor/application/*.java

objects = $(src_path)/GameObjects/objects/*.java

nodes = $(src_path)/RuleEngine/nodes/*.java 
engine = $(src_path)/RuleEngine/engine/*.java
impossible = $(src_path)/RuleEngine/impossible/*.java

controllers = $(src_path)/Editors/controllers/*.java
menu = $(src_path)/Editors/MainMenu.java
blocks = $(src_path)/Editors/blocks/*.java

lib = *.jar


all: $(play) $(play_controllers) $(board) $(impossible) $(objects) $(nodes) $(engine) $(blocks) $(controllers) $(menu)
	javac -d $(compile_path) --module-path $(PATH_TO_FX) --add-modules $(fxmodules) $^

run:
	java --module-path $(PATH_TO_FX) --add-modules $(fxmodules) org.GamePlay.BasicApplication

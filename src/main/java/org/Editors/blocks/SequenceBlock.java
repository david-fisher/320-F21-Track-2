package org.Editors.blocks;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

//node field for this class should always be null

public class SequenceBlock extends Block {
  //Width of the sequence block
  final int SEQ_WIDTH = 100;

  //Pointer to parent of tree
  private org.RuleEngine.nodes.Node parentPtr; 

  private TextField field;

  public SequenceBlock() {
    this.block = new StackPane();

    this.blockHeight += 25;

    //Make the block draggable
    this.block.setOnMousePressed(e -> {
      //calculate offset
      this.startX = e.getSceneX() - this.block.getTranslateX();
      this.startY = e.getSceneY() - this.block.getTranslateY();
    });

    this.block.setOnMouseDragged(e -> {
      //set new position
      this.block.setTranslateX(e.getSceneX() - this.startX);
      this.block.setTranslateY(e.getSceneY() - this.startY);
    });

    //Pane for placing the controls and text for the block
    this.grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    this.grid.setPadding(new Insets(10, 0, 10, 0));
    this.grid.setMinSize(SEQ_WIDTH, this.blockHeight);
    this.grid.setPrefSize(SEQ_WIDTH, this.blockHeight);
    //Set vertical gap between columns and horizontal gap
    this.grid.setVgap(VGAP);
    this.grid.setHgap(HGAP);

    final int leftColSize = 10;
    final int rightColSize = 35;
    final int middleColSize = SEQ_WIDTH - leftColSize - rightColSize;
    //Set width of column 0
    grid.getColumnConstraints().add(new ColumnConstraints(leftColSize));
    //Set width of column 1
    grid.getColumnConstraints().add(new ColumnConstraints(middleColSize));
    //Set width of column 2
    grid.getColumnConstraints().add(new ColumnConstraints(rightColSize));

    //Base visual of the stackpane
    Rectangle base = new Rectangle(SEQ_WIDTH, this.blockHeight, GREY);

    field = new TextField();
    field.setPrefWidth(middleColSize);
    field.setMaxWidth(middleColSize);
    grid.add(field, 1, 0);

    //Create outgoing connection for block
    Rectangle connBlock = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    ObservableList<javafx.scene.Node> seqConnBlockList = FXCollections.observableArrayList();
    seqConnBlockList.addAll(connBlock);

    //Add the sequence connection block list to the rule group list
    this.ruleGroupList.addAll(seqConnBlockList);
    
    //Stack the base Rectangle and grid GridPane of the block on the pane
    this.block.getChildren().addAll(base, this.grid, field, connBlock);
    this.block.setAlignment(connBlock, Pos.CENTER_RIGHT);
  }

  public int getBlockWidth(){
    return SEQ_WIDTH;
  }

  public org.RuleEngine.nodes.Node getParentPtr() {
    return this.parentPtr;
  }

  public void setParentPtr(org.RuleEngine.nodes.Node parent) {
    this.parentPtr = parent;
  }

  public String getFieldText() {
    return field.getText();
  }
}

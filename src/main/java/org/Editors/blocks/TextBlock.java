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

import org.RuleEngine.nodes.NodeMaker;
import org.RuleEngine.nodes.LiteralNode;

//SHOULD NOT CALL getNode() ON THIS CLASS!!! This class uses LiteralNode instead of OpNode.

public class TextBlock extends Block {
  private TextField field;
  
  //Since LiteralNodes are not of type OpNode, we must use this instead of the "node" field from the Block class.
  private LiteralNode litNode;

  public TextBlock() {
    this.block = new StackPane();

    this.blockHeight += 25;

    //Make the block draggable and deletable
    makeDraggableAndDeletable();

    //Pane for placing the controls and text for the block
    this.grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    this.grid.setPadding(new Insets(10, 0, 10, 0));
    this.grid.setMinSize(BLOCK_WIDTH, this.blockHeight);
    this.grid.setPrefSize(BLOCK_WIDTH, this.blockHeight);
    //Set vertical gap between columns and horizontal gap
    this.grid.setVgap(VGAP);
    this.grid.setHgap(HGAP);

    final int leftColSize = 35;
    final int rightColSize = 10;
    final int middleColSize = BLOCK_WIDTH - leftColSize - rightColSize;
    //Set width of column 0
    grid.getColumnConstraints().add(new ColumnConstraints(leftColSize));
    //Set width of column 1
    grid.getColumnConstraints().add(new ColumnConstraints(middleColSize));
    //Set width of column 2
    grid.getColumnConstraints().add(new ColumnConstraints(rightColSize));

    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, this.blockHeight, GREY);

    field = new TextField();
    field.setPrefWidth(middleColSize);
    field.setMaxWidth(middleColSize);
    grid.add(field, 1, 0);

    //Create result connection for block
    this.result = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);
    

    //Stack the base Rectangle and grid GridPane of the block on the pane
    this.block.getChildren().addAll(base, grid, field, result);
    this.block.setAlignment(result, Pos.CENTER_LEFT);

    //Set node for this block
    //this.node = NodeMaker.makeStringNode("");
    this.litNode = new LiteralNode<String>("");
  }

  public String getFieldText() {
    return field.getText();
  }

  public LiteralNode getLiteralNode() {
    return this.litNode;
  }
}

package editors.rule_editor_ui.blocks;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.HPos;

import nodes.OpNode;
import nodes.NodeMaker;

public class Block {
  protected StackPane block;
  protected OpNode node;

  //Variables for dragging
  private double startX;
  private double startY;

  //Color styling
  final Color SILVER = Color.rgb(200,200,200);
  final Color GREY = Color.rgb(55,55,55);
  final Color WHITE = Color.rgb(255,255,255);
  final Color BLUE = Color.rgb(0,180,255);

  //Sizing and spacing
  final int HEADER_SIZE = 15;
  final int VGAP = 10;
  final int HGAP = 5;
  final int BLOCK_WIDTH = 150;
  final int CONNECTION_WIDTH = 19;
  final int CONNECTION_HEIGHT = 30;
  //Total height of block is 70 + 50 + 20 + 2*5 = 150px
  //The 3*5 term comes from the fact that each column has 5px spacing from HGAP
  final int COL0_WIDTH = 70;
  final int COL1_WIDTH = 50;
  final int COL2_WIDTH = 20;
  
  //Default height of 20
  private int blockHeight = 20;
  //Resize factor for when adding row
  final int RESIZE_FACTOR = 40;

  //Initialize block as a stackpane and make it draggable
  public Block(String blockName, String[] valueNames) {
    this.block = new StackPane();

    //Size block to fit the number of values that it will be passing in.
    //We will have 1 row in the gridpane for each value.
    this.blockHeight = this.blockHeight + (RESIZE_FACTOR * valueNames.length);

    //Make the block draggable
    this.block.setOnMousePressed(e -> {
      //calculate offset
      startX = e.getSceneX() - this.block.getTranslateX();
      startY = e.getSceneY() - this.block.getTranslateY();
    });

    this.block.setOnMouseDragged(e -> {
      //set new position
      this.block.setTranslateX(e.getSceneX() - startX);
      this.block.setTranslateY(e.getSceneY() - startY);
    });

    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(BLOCK_WIDTH, blockHeight);
    grid.setPrefSize(BLOCK_WIDTH, blockHeight);
    //Set vertical gap between columns and horizontal gap
    grid.setVgap(VGAP);
    grid.setHgap(HGAP);

    //Set width of column 0
    grid.getColumnConstraints().add(new ColumnConstraints(COL0_WIDTH));
    //Set width of column 1
    grid.getColumnConstraints().add(new ColumnConstraints(COL1_WIDTH));
    //Set width of column 2
    grid.getColumnConstraints().add(new ColumnConstraints(COL2_WIDTH));

    //Create text that will be displayed on top of block
    Text name = new Text(blockName);
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);

    //Create the names and connections for each value being passed to block
    for(int i = 0; i < valueNames.length; i++) {
      Text newValName = new Text(valueNames[i]);
      newValName.setFill(WHITE);
      grid.add(newValName, 1, i+1);
      //Right-justify the text
      grid.setHalignment(newValName, HPos.RIGHT); 
      grid.add(new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER), 2, i+1);
    }

    //Create result connection for block
    Rectangle result = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);

    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, blockHeight, GREY);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
    //Attach result connection if there is >= 1 rows in the block
    if (valueNames.length >= 1) {
      this.block.getChildren().addAll(result);
      this.block.setAlignment(result, Pos.CENTER_LEFT);
    }
  }

  //Return the current instance of the block
  public StackPane getBlock() {
    return this.block;
  }

  //Position the block in the top left portion of the given pane
  public void setTopLeft(AnchorPane pane) {
    this.block.setTranslateX(pane.getWidth() / 16);
    this.block.setTranslateY(pane.getHeight() / 16);
  }

  protected void createNode(String nodeType) {
    this.node = NodeMaker.makeNode(nodeType);
  }
}

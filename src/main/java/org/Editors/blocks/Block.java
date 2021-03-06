package org.Editors.blocks;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.HPos;

import javafx.scene.input.MouseEvent; 
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import org.Editors.controllers.RuleEditorUIController;
import org.RuleEngine.nodes.NodeMaker;
import org.RuleEngine.nodes.OpNode;

import java.util.ArrayList;

public class Block {
  protected StackPane block;
  protected org.RuleEngine.nodes.Node node;

  //Variables for dragging
  protected double startX;
  protected double startY;

  //Color styling
  protected final Color SILVER = Color.rgb(200,200,200);
  protected final Color GREY = Color.rgb(55,55,55);
  protected final Color WHITE = Color.rgb(255,255,255);
  protected final Color BLUE = Color.rgb(0,180,255);

  //Sizing and spacing
  protected final int HEADER_SIZE = 15;
  protected final int VGAP = 10;
  protected final int HGAP = 5;
  protected final int BLOCK_WIDTH = 150;
  protected final int CONNECTION_WIDTH = 19;
  protected final int CONNECTION_HEIGHT = 30;
  //Total width of block is 70 + 50 + 20 + 2*5 = 150px
  //The 3*5 term comes from the fact that each column has 5px spacing from HGAP
  protected final int COL0_WIDTH = 70;
  protected final int COL1_WIDTH = 50;
  protected final int COL2_WIDTH = 20;
  
  //Default height of 35
  protected int blockHeight = 35;
  //Resize factor for when adding row
  protected final int RESIZE_FACTOR = 40;

  protected Rectangle result;
  protected GridPane grid;
  protected Boolean isConnected = false;
  protected AnchorPane editorPane;

  //List of lists of connection blocks
  protected ObservableList<ObservableList<javafx.scene.Node>> ruleGroupList = FXCollections.observableArrayList();
  protected ArrayList<Line> links = new ArrayList<>();

  //Add event handlers for left and right click - left click & hold = drag, right click = delete
  protected void makeDraggableAndDeletable() {
    //Make the block draggable and deletable
    this.block.setOnMousePressed(e -> {
      if (e.isPrimaryButtonDown()) {
        //calculate offset
        startX = e.getSceneX() - this.block.getTranslateX();
        startY = e.getSceneY() - this.block.getTranslateY();
      } else if (e.isSecondaryButtonDown()) {
        // Delete block if right clicked.
        if(this.node instanceof OpNode){
          try {
            this.node.parent.removeOperand((OpNode) this.node);
          } catch (NullPointerException excep) {
            //System.out.println(excep);
          }
          ArrayList ops = ((OpNode) this.node).getAllOperands();
          for (Object aNode: ops) {
            if (aNode instanceof OpNode) {
              ((OpNode) aNode).parent = null;
            } else if (aNode instanceof ArrayList) {
              for (Object bNode : ((ArrayList) aNode)) {
                if (bNode instanceof OpNode) {
                  ((OpNode) bNode).parent = null;
                }
              }
            }
          }
        }
        this.node = null;
        this.block.setVisible(false);
        this.block = null;
        this.grid = null;
        deleteAllLinks();
      }
    });
    this.block.setOnMouseDragged(e -> {
      if (!isConnected){
        //set new position
        this.block.setTranslateX(e.getSceneX() - startX);
        this.block.setTranslateY(e.getSceneY() - startY);
      }
    });
  }

  protected void createGenBlock(String blockName, String[] valueNames) {
    this.block = new StackPane();

    //Make the block draggable and deletable
    makeDraggableAndDeletable();

    //Size block to fit the number of values that it will be passing in.
    //We will have 1 row in the gridpane for each value.
    //If there are no arguments, still give it the size of as if it has 1 argument.
    int resize = RESIZE_FACTOR * (valueNames.length == 0 ? 1 : valueNames.length);
    this.blockHeight = this.blockHeight + resize;

    //Pane for placing the controls and text for the block
    this.grid = new GridPane();
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

    //List to hold the connection blocks we create
    ObservableList<javafx.scene.Node> connBlockList = FXCollections.observableArrayList();
    //Create the names and connections for each value being passed to block
    for(int i = 0; i < valueNames.length; i++) {
      Text newValName = new Text(valueNames[i]);
      newValName.setFill(WHITE);
      grid.add(newValName, 1, i+1);
      //Right-justify the text
      grid.setHalignment(newValName, HPos.RIGHT); 
      Rectangle connBlock = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
      //Store the conneciton blocks in a list
      connBlockList.addAll(connBlock);
      grid.add(connBlock, 2, i+1);
    }
    //Add list of connection blocks to list of lists
    ruleGroupList.addAll(connBlockList);
    
    //Create result connection for block
    this.result = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);

    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, blockHeight, GREY);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
    
    //Attach result connection
    this.block.getChildren().addAll(result);
    this.block.setAlignment(result, Pos.CENTER_LEFT);
  }

  //Return the current instance of the block
  public StackPane getBlock() {
    return this.block;
  }

  public org.RuleEngine.nodes.Node getNode() {
    return this.node;
  }

  //Position the block in the top left portion of the given pane
  public void setTopLeft(AnchorPane pane) {
    this.block.setTranslateX(pane.getWidth() / 16);
    this.block.setTranslateY(pane.getHeight() / 16);
  }

  protected void createNode(String nodeType) {
    this.node = NodeMaker.makeNode(nodeType);
  }

  public Rectangle getResultRect(){
    return this.result; 
  }

  public int getBlockHeight(){
    return blockHeight;
  }

  public int getBlockWidth(){
    return BLOCK_WIDTH;
  }

  public ObservableList<ObservableList<javafx.scene.Node>> getRuleGroupList(){
    return this.ruleGroupList;
  }

  public int getGreyRectHeight(){
    return CONNECTION_HEIGHT;
  }

  public void setBlockAnchor(){
    isConnected = true;
  }

  public void addLink(Line link){
    links.add(link);
  }

  private void deleteAllLinks(){
      for(Line l: links){
        this.editorPane.getChildren().remove(l);
      }
      links.clear();
  }
  public void setEditorPane(AnchorPane editorPane){
    this.editorPane = editorPane;
  }
}

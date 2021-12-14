package org.Editors.blocks;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.HPos;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.control.ComboBox;

public class ALBlock extends Block {
  private final int BLOCK_HEIGHT = 150;

  private final int FIRST_COL_WIDTH = 20;
  private final int MID_COL_WIDTH = 100;
  private final int LAST_COL_WIDTH = 20;

  //Holds connection blocks
  private ObservableList<javafx.scene.Node> connBlockList = FXCollections.observableArrayList();

  //Combo box for the user to select which operator they want to use in the block
  private ComboBox opDropDown;

  public ALBlock() {
    this.block = new StackPane();

    //Make the block draggable and deletable
    makeDraggableAndDeletable();

    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top & bottom to 10px; padding of left  & right 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(BLOCK_WIDTH, BLOCK_HEIGHT);
    grid.setPrefSize(BLOCK_WIDTH, BLOCK_HEIGHT);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(VGAP);
    grid.setHgap(HGAP);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(FIRST_COL_WIDTH));
    //Set width of column 1 to 100px
    grid.getColumnConstraints().add(new ColumnConstraints(MID_COL_WIDTH));
    //Set width of column 2 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(LAST_COL_WIDTH));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 0 of grid
    Text name = new Text("Arithmetic-Logic");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);

    //Defining row 1 of grid
    Rectangle valOneInput = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    this.connBlockList.addAll(valOneInput);
    Text valOneText = new Text("val1:");
    valOneText.setFill(WHITE);
    grid.add(valOneText, 1, 1);
    grid.add(valOneInput, 2, 1);
    grid.setHalignment(valOneText, HPos.RIGHT);
    grid.setHalignment(valOneInput, HPos.RIGHT);

    //Defining row 2 of grid
    this.opDropDown = new ComboBox();
    this.opDropDown.getItems().addAll("+", "-", "*", "/", ">", "<", ">=", "<=", "==", "%", "&&", "||");
    //Default operator of ALBlock is '+'
    this.opDropDown.setValue("+");
    grid.add(this.opDropDown, 1, 2);
    grid.setHalignment(this.opDropDown, HPos.CENTER);

    //Defining row 3 of grid
    Rectangle valTwoInput = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    this.connBlockList.addAll(valTwoInput);
    Text valTwoText = new Text("val2:");
    valTwoText.setFill(WHITE);
    grid.add(valTwoText, 1, 3);
    grid.add(valTwoInput, 2, 3);
    grid.setHalignment(valTwoText, HPos.RIGHT);
    grid.setHalignment(valTwoInput, HPos.RIGHT);
    
    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, BLOCK_HEIGHT, GREY);

    Rectangle resultConn = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);
    
    //Set the result block for this block to the result connection we made
    this.result = resultConn;
    
    //Add all the lists of connection blocks to the rule group list
    this.ruleGroupList.addAll(connBlockList);

    //Stack the base Rectangle, result connection, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name, resultConn);
    //Position the name of the block at the top of the stackpane and the result on the left
    this.block.setAlignment(name, Pos.TOP_CENTER);
    this.block.setAlignment(resultConn, Pos.CENTER_LEFT);
    //Create the actual Node object for this block
    this.createNode("AL");
  }

  //Get the selected operator from the combo box
  public String getOp() {
    return (String)this.opDropDown.getValue();
  }
}

package org.Editors.blocks;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent; 
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/* Condition block stored in connBlockList from Block class. Statements from while stored
in stmntsConnBlockList defined below*/

public class WhileBlock extends Block {
  private int HEIGHT = 150;

  private int firstColWidth = 20;
  private int midColWidth = 100;
  private int lastColWidth = 20;

  protected ObservableList<javafx.scene.Node> stmntsConnBlockList = FXCollections.observableArrayList();

  public WhileBlock(int numStmnts) {
    this.block = new StackPane();

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
    GridPane grid = new GridPane();
    //Padding of top & bottom to 10px; padding of left  & right 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(BLOCK_WIDTH, HEIGHT);
    grid.setPrefSize(BLOCK_WIDTH, HEIGHT);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(firstColWidth));
    //Set width of column 1 to 130px
    grid.getColumnConstraints().add(new ColumnConstraints(midColWidth));
    //Set width of column 2 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(lastColWidth));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));
    
    //Defining row 0 of grid
    Text name = new Text("while");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);
  
    //Defining row 1 of grid
    Rectangle conditionInput = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    connBlockList.addAll(conditionInput);
    Text connText = new Text("Condition");
    connText.setFill(WHITE);
    Rectangle result = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);
    grid.add(result, 0, 1);
    grid.add(connText, 1, 1);
    grid.add(conditionInput, 2, 1);
    grid.setHalignment(connText, HPos.RIGHT);

    //Defining row 2 of grid
    Text stmntsText = new Text("Statements");
    stmntsText.setFill(WHITE);
    grid.add(stmntsText, 1, 2);
    grid.setHalignment(stmntsText, HPos.CENTER);
    
    //Start row is the row at which we want to start adding the statements. Since "Statements" text is on
    //row 2, we start at row 3.
    int startRow = 3;
    for(int i = 0; i < numStmnts; i++) {
      Rectangle connBlock = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
      //Store the conneciton blocks in a list
      connBlockList.addAll(connBlock);
      grid.add(connBlock, 2, i+startRow);
      if (i + 1 != numStmnts) {
        HEIGHT = HEIGHT + 35;
      }
    }

    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, HEIGHT, GREY);
    
    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
    //Create the actual Node object for this block
    //this.createNode("while");
  }
}

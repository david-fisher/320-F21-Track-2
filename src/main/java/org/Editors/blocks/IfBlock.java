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

public class IfBlock extends Block {
  //Default height
  private int HEIGHT = 220;
  private final int VBOX_PADDING = 10;
  //For readjusting size of if & else vboxes. 40 because each connection is 30px tall and vboxes have padding of 10px.
  private final int SIZE_REFACTOR = 40;

  private int firstColWidth = 20;
  private int midColWidth = 100;
  private int lastColWidth = 20;

  private ObservableList<javafx.scene.Node> conditionConnBlockList = FXCollections.observableArrayList();
  private ObservableList<javafx.scene.Node> ifConnBlockList = FXCollections.observableArrayList();
  private ObservableList<javafx.scene.Node> elseConnBlockList = FXCollections.observableArrayList();

  public IfBlock(int numIfStmnts, int numElseStmnts) {
    this.block = new StackPane();

    //Make the block draggable and deletable
    makeDraggableAndDeletable();

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
    Text name = new Text("if");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);
  
    //Defining row 1 of grid
    Rectangle conditionInput = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    conditionConnBlockList.addAll(conditionInput);
    Text connText = new Text("Condition");
    connText.setFill(WHITE);
    Rectangle resultConn = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);
    grid.add(resultConn, 0, 1);
    grid.add(connText, 1, 1);
    grid.add(conditionInput, 2, 1);
    grid.setHalignment(connText, HPos.RIGHT); 
    
    // Defining row 2 of grid
    Text thenText  = new Text("Then");
    thenText.setFill(WHITE);
    grid.add(thenText, 1, 2);
    grid.setHalignment(thenText, HPos.CENTER); 

    //Defining row 3 of grid
    VBox vboxIf = new VBox(VBOX_PADDING);
    grid.add(vboxIf, 2, 3);
    for(int i = 0; i < numIfStmnts; i++) {
      Rectangle connBlock = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
      //Store the conneciton blocks in a list
      ifConnBlockList.addAll(connBlock);
      vboxIf.getChildren().add(connBlock);
      if (i + 1 != numIfStmnts) {
        HEIGHT = HEIGHT + SIZE_REFACTOR;
      }
    }
    
    //ELSE PART
    //Defining row 5 of grid
    Text elseName = new Text("Else");
    elseName.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    elseName.setFill(WHITE);
    grid.add(elseName, 1, 5);
    grid.setHalignment(elseName, HPos.CENTER);

    //Defining row 6 of grid
    VBox vboxElse = new VBox(VBOX_PADDING);
    grid.add(vboxElse, 2, 6);
    for(int i = 0; i < numElseStmnts; i++) {
      Rectangle connBlock = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
      //Store the conneciton blocks in a list
      elseConnBlockList.addAll(connBlock);
      vboxElse.getChildren().add(connBlock);
      if (i + 1 != numElseStmnts) {
        HEIGHT = HEIGHT + SIZE_REFACTOR;
      }
    }

    //Add the lists of connection blocks to the ruleGroupList
    this.ruleGroupList.addAll(conditionConnBlockList, ifConnBlockList, elseConnBlockList);

    //Set the result block for this block to the result connection we made
    this.result = resultConn;

    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, HEIGHT, GREY);
    
    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
    //Create the actual Node object for this block
    this.createNode("if");
  }
}

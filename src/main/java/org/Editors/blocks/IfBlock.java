package editors.blocks;

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

/**Well, this is bad code but the conndition connection rectangle is stored in connBlockList which
is stored in the Block class. The connection blocks from the if part and the else part are stored in their
own lists defined here: ifConnBlockList and elseConnBlockList**/

public class IfBlock extends Block {
  private int HEIGHT = 270;

  private int firstColWidth = 20;
  private int midColWidth = 100;
  private int lastColWidth = 20;

  protected ObservableList<javafx.scene.Node> ifConnBlockList = FXCollections.observableArrayList();
  protected ObservableList<javafx.scene.Node> elseConnBlockList = FXCollections.observableArrayList();

  public IfBlock() {
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

    //Base visual of the stackpane
    Rectangle base = new Rectangle(BLOCK_WIDTH, HEIGHT, GREY);

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
    connBlockList.addAll(conditionInput);
    Text connText = new Text("Condition");
    connText.setFill(WHITE);
    Rectangle result = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, BLUE);
    grid.add(result, 0, 1);
    grid.add(connText, 1, 1);
    grid.add(conditionInput, 2, 1);
    grid.setHalignment(connText, HPos.RIGHT); 
    
    // Defining row 2 of grid
    Text thenText  = new Text("Then");
    thenText.setFill(WHITE);
    grid.add(thenText, 1, 2);
    grid.setHalignment(thenText, HPos.CENTER); 

    //Defining row 3 of grid
    Rectangle thenInput = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    ifConnBlockList.addAll(thenInput);
    VBox vbox = new VBox(5, thenInput);
    grid.add(vbox, 2, 3);
    
    // Define row 4
    Button addThenOfIf = new Button("+");
    grid.add(addThenOfIf, 1, 4);
    grid.setHalignment(addThenOfIf, HPos.CENTER); 

    // action event
    EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
      public void handle(MouseEvent e)
      {
        Rectangle thenInput2 = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
        ifConnBlockList.addAll(thenInput2);
        vbox.getChildren().add(thenInput2);
        HEIGHT = HEIGHT + 35;
        base.setHeight(HEIGHT);
      }
    };
    addThenOfIf.addEventFilter(MouseEvent.MOUSE_CLICKED, event); 
    
    //ELSE PART
    //Defining row 5 of grid
    Text elseName = new Text("Else");
    elseName.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    elseName.setFill(WHITE);
    grid.add(elseName, 1, 5);
    grid.setHalignment(elseName, HPos.CENTER);

    //Defining row 6 of grid
    Rectangle thenElsePart = new Rectangle(CONNECTION_WIDTH, CONNECTION_HEIGHT, SILVER);
    elseConnBlockList.addAll(thenElsePart);
    VBox vboxElsePart = new VBox(5, thenElsePart);
    grid.add(vboxElsePart, 2, 6);
    
    // Define row 7
    Button addThenOfElse = new Button("+");
    grid.add(addThenOfElse, 1, 7);
    grid.setHalignment(addThenOfElse, HPos.CENTER); 

    // action event
    EventHandler<MouseEvent> eventElsePart = new EventHandler<MouseEvent>() {
      public void handle(MouseEvent e)
      {
        Rectangle thenInput2 = new Rectangle(19, 30, SILVER);
        elseConnBlockList.addAll(thenInput2);
        vboxElsePart.getChildren().add(thenInput2);
        HEIGHT = HEIGHT + 35;
        base.setHeight(HEIGHT);
      }
    };
    addThenOfElse.addEventFilter(MouseEvent.MOUSE_CLICKED, eventElsePart); 
    
    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
    //Create the actual Node object for this block
    this.createNode("if");
  }
}

package editors.rule_editor_ui.blocks;

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

public class ConditionBlock extends Block {
  int HEIGHT = 270;
  int WIDTH = 170;

  public ConditionBlock() {
    //Base visual of the stackpane
    Rectangle base = new Rectangle(WIDTH, HEIGHT, GREY);

    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top & bottom to 10px; padding of left  & right 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(WIDTH, HEIGHT);
    grid.setPrefSize(WIDTH, HEIGHT);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to px
    grid.getColumnConstraints().add(new ColumnConstraints(140));
    //Set width of column 1 to 10px
    grid.getColumnConstraints().add(new ColumnConstraints(10));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 0 of grid
    Text name = new Text("If");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);
    grid.add(name, 0, 0);

    //Defining row 1 of grid
    Rectangle ifInput = new Rectangle(19, 30, SILVER);
    Text index = new Text("Condition");
    index.setFill(WHITE);
    grid.add(index, 0, 1);
    grid.add(ifInput, 1, 1);
    
    // Defining row 2 of grid
    Text thenText  = new Text("Then");
    thenText.setFill(WHITE);
    grid.add(thenText,0, 2);

    //Defining row 3 of grid
    Rectangle thenInput = new Rectangle(19, 30, BLUE);
    VBox vbox = new VBox(5, thenInput);
    grid.add(vbox, 1, 3);
    
    // Define row 4
    Button addThenOfIf = new Button("+");
    grid.add(addThenOfIf,0, 4);
    // action event
    EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
      public void handle(MouseEvent e)
      {
        Rectangle thenInput2 = new Rectangle(19, 30, BLUE);
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
    grid.add(elseName, 0,5);

    //Defining row 6 of grid
    Rectangle thenElsePart = new Rectangle(19, 30, BLUE);
    VBox vboxElsePart = new VBox(5, thenElsePart);
    grid.add(vboxElsePart, 1, 6);
    
    // Define row 7
    Button addThenOfElse = new Button("+");
    grid.add(addThenOfElse,0, 7);
    // action event
    EventHandler<MouseEvent> eventElsePart = new EventHandler<MouseEvent>() {
      public void handle(MouseEvent e)
      {
        Rectangle thenInput2 = new Rectangle(19, 30, BLUE);
        vboxElsePart.getChildren().add(thenInput2);
        HEIGHT = HEIGHT + 35;
        base.setHeight(HEIGHT);
      }
    };
    addThenOfElse.addEventFilter(MouseEvent.MOUSE_CLICKED, eventElsePart); 
    
    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
    
  }
}
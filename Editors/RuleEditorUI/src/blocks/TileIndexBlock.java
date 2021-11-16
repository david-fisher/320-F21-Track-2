package editors.rule_editor_ui.blocks;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class TileIndexBlock extends Block {
  public TileIndexBlock() {
    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top & bottom to 10px; padding of left  & right 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(170, 105);
    grid.setPrefSize(170, 105);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set width of column 1 to 10px
    grid.getColumnConstraints().add(new ColumnConstraints(10));
    //Set width of column 2 to 45px
    grid.getColumnConstraints().add(new ColumnConstraints(45));
    //Set width of column 3 to 55px
    grid.getColumnConstraints().add(new ColumnConstraints(55));
    //Set width of column 4 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 1 of grid
    Rectangle indexIn = new Rectangle(19, 30, SILVER);
    Text index = new Text("i:");
    index.setFill(WHITE);
    TextField indexField = new TextField();
    indexField.setPrefColumnCount(10);
    Rectangle resultOut = new Rectangle(19, 30, SILVER);
    Text result = new Text("Result:");
    result.setFill(WHITE);
    grid.add(indexIn, 0, 1);
    grid.add(index, 1, 1);
    grid.add(indexField, 2, 1);
    grid.add(result, 3, 1);
    grid.add(resultOut, 4, 1);
    grid.setHalignment(result, HPos.RIGHT);
    
    //Defining row 2 of grid
    Rectangle ifIn = new Rectangle(19, 30, BLUE);
    grid.add(ifIn, 0, 2);

    //Defining row 0 of grid
    Text name = new Text("get tile[i]");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);

    //Base visual of the stackpane
    Rectangle base = new Rectangle(170, 105, GREY);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
  }
}
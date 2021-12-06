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


public class GetTileConnBlock extends Block {
  public GetTileConnBlock() {
    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top & bottom to 10px; padding of left  & right 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(170, 150);
    grid.setPrefSize(170, 150);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set width of column 1 to 35x
    grid.getColumnConstraints().add(new ColumnConstraints(35));
    //Set width of column 2 to 55px
    grid.getColumnConstraints().add(new ColumnConstraints(55));
    //Set width of column 3 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set width of column 4 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 1 of grid
    Rectangle connIn = new Rectangle(19, 30, SILVER);
    Text conn = new Text("Conn.:");
    conn.setFill(WHITE);
    TextField connField = new TextField();
    connField.setPrefColumnCount(10);
    grid.add(connIn, 0, 1);
    grid.add(conn, 1, 1);
    grid.add(connField, 2, 1);

    //Defining row 2 of grid
    Rectangle fromIn = new Rectangle(19, 30, SILVER);
    Text from = new Text("From:");
    from.setFill(WHITE);
    TextField fromField = new TextField();
    fromField.setPrefColumnCount(10);
    grid.add(fromIn, 0, 2);
    grid.add(from, 1, 2);
    grid.add(fromField, 2, 2);
    
    //Defining row 3 of grid
    Rectangle ifIn = new Rectangle(19, 30, BLUE);
    Rectangle resultOut = new Rectangle(19, 30, SILVER);
    Text result = new Text("Result:");
    result.setFill(WHITE);
    grid.add(ifIn, 0, 3);
    grid.add(result, 3, 3);
    grid.add(resultOut, 4, 3);
    grid.setHalignment(result, HPos.RIGHT);

    //Defining row 0 of grid
    Text name = new Text("get connection");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);

    //Base visual of the stackpane
    Rectangle base = new Rectangle(170, 150, GREY);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
  }
}
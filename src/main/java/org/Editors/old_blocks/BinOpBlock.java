package editors.rule_editor_ui.blocks;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.control.ComboBox;

public class BinOpBlock extends Block {
  public BinOpBlock() {
    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(170, 185);
    grid.setPrefSize(170, 185);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set width of column 1 to 30px
    grid.getColumnConstraints().add(new ColumnConstraints(30));
    //Set width of column 2 to 85px
    grid.getColumnConstraints().add(new ColumnConstraints(85));
    //Set width of column 3 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 1 of grid
    Text val1 = new Text("Val1.:");
    val1.setFill(WHITE);
    TextField val1Field = new TextField();
    val1Field.setPrefColumnCount(10);
    Rectangle val1In = new Rectangle(19, 30, SILVER);
    grid.add(val1In, 0, 1);
    grid.add(val1, 1, 1);
    grid.add(val1Field, 2, 1);
    
    //Defining row 2 of grid
    Text op = new Text("Op:");
    op.setFill(WHITE);
    ComboBox opDropDown = new ComboBox();
    opDropDown.getItems().addAll("+", "-", "*", "/", ">", "<", ">=", "<=", "=", "!=", "&&", "||");
    Rectangle ifIn = new Rectangle(19, 30, BLUE);
    grid.add(ifIn, 0, 2);
    grid.add(op, 1, 2);
    grid.add(opDropDown, 2, 2);
    grid.setHalignment(opDropDown, HPos.CENTER);

    //Defining row 3 of grid
    Text val2 = new Text("Val2:");
    val2.setFill(WHITE);
    TextField val2Field = new TextField();
    val2Field.setPrefColumnCount(10);
    Rectangle val2In = new Rectangle(19, 30, SILVER);
    grid.add(val2In, 0, 3);
    grid.add(val2, 1, 3);
    grid.add(val2Field, 2, 3);

    //Degfining row 4 of grid
    Rectangle resultOut = new Rectangle(19, 30, SILVER);
    Text result = new Text("Result:");
    result.setFill(WHITE);
    grid.add(resultOut, 3, 4);
    grid.add(result, 2, 4);
    grid.setHalignment(result, HPos.RIGHT);  

    //Defining row 0 of grid
    Text name = new Text("binary op");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);

    //Base visual of the stackpane
    Rectangle base = new Rectangle(170, 185, GREY);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
  }
}

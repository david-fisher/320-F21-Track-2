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


public class GetBlock extends Block {
  public GetBlock() {
    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(170, 140);
    grid.setPrefSize(170, 140);
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
    Text reg = new Text("Prop.:");
    reg.setFill(WHITE);
    TextField regField = new TextField();
    regField.setPrefColumnCount(10);
    grid.add(reg, 1, 1);
    grid.add(regField, 2, 1);
    
    //Defining row 2 of grid
    Text from = new Text("From:");
    from.setFill(WHITE);
    TextField fromField = new TextField();
    fromField.setPrefColumnCount(10);
    grid.add(from, 1, 2);
    grid.add(fromField, 2, 2);

    //Defining row 3 of grid
    Rectangle toConnect = new Rectangle(19, 30, SILVER);
    Text result = new Text("Result:");
    Rectangle ifIn = new Rectangle(19, 30, BLUE);
    result.setFill(WHITE);
    grid.add(ifIn, 0, 3);
    grid.add(toConnect, 3, 3);
    grid.add(result, 2, 3);
    grid.setHalignment(result, HPos.RIGHT);

    //Defining row 0 of grid
    Text name = new Text("get");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);

    //Base visual of the stackpane
    Rectangle base = new Rectangle(170, 140, GREY);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    this.block.getChildren().addAll(base, grid, name);
    //Position the name of the block at the top of the stackpane
    this.block.setAlignment(name, Pos.TOP_CENTER);
  }
}
package editors.rule_editor_ui.blocks;

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

public class PsetBlock extends Block {
  public PsetBlock() {
    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(170, 150);
    grid.setPrefSize(170, 150);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set width of column 1 to 30px
    grid.getColumnConstraints().add(new ColumnConstraints(30));
    //Set width of column 2 to 100px
    grid.getColumnConstraints().add(new ColumnConstraints(85));
    //Set width of column 3 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 1 of grid
    Text prop = new Text("Prop.:");
    prop.setFill(WHITE);
    Rectangle propConnect = new Rectangle(19, 30, SILVER);
    TextField propField = new TextField();
    propField.setPrefColumnCount(10);
    grid.add(prop, 1, 1);
    grid.add(propConnect, 0, 1);
    grid.add(propField, 2, 1);
    
    //Defining row 2 of grid
    Text from = new Text("From:");
    from.setFill(WHITE);
    TextField fromField = new TextField();
    fromField.setPrefColumnCount(10);
    grid.add(from, 1, 2);
    grid.add(fromField, 2, 2);

    //Defining row 3 of grid
    Text value = new Text("Value:");
    value.setFill(WHITE);
    TextField valueField = new TextField();
    valueField.setPrefColumnCount(10);
    Rectangle valConnect = new Rectangle(19, 30, SILVER);
    Rectangle valOut = new Rectangle(19, 30, SILVER);
    grid.add(value, 1, 3);
    grid.add(valueField, 2, 3);
    grid.add(valConnect, 0, 3);
    grid.add(valOut, 3, 3);

    //Pane on which we stack the base Rectangle and grid GridPane
    StackPane stackPane = new StackPane();
    //Base visual of the stackpane
    Rectangle base = new Rectangle(170, 150, GREY);

    //Defining row 0 of grid
    Text name = new Text("pset");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);
    stackPane.setAlignment(name, Pos.TOP_CENTER);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    stackPane.getChildren().addAll(base, grid, name);
    
    this.block = stackPane;
  }
}

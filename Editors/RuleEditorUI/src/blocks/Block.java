package editors.rule_editor_ui.blocks;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Block {
  protected StackPane block;
  final Color SILVER = Color.rgb(200,200,200);
  final Color GREY = Color.rgb(55,55,55);
  final Color WHITE = Color.rgb(255,255,255);
  final int HEADER_SIZE = 15;

  public StackPane getBlock() {
    return this.block;
  }

  public void setTopLeft(AnchorPane pane) {
    //Set the stackpane's position to the top left of the pane
    this.block.setTranslateX(pane.getWidth() / 16);
    this.block.setTranslateY(pane.getHeight() / 16);
  }
}

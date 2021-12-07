package editors.rule_editor_ui.blocks;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import nodes.OpNode;
import nodes.NodeMaker;

public class Block {
  protected StackPane block;
  protected OpNode node;

  private double startX;
  private double startY;

  final Color SILVER = Color.rgb(200,200,200);
  final Color GREY = Color.rgb(55,55,55);
  final Color WHITE = Color.rgb(255,255,255);
  final Color BLUE = Color.rgb(0,180,255);
  final int HEADER_SIZE = 15;

  //Initialize block as a stackpane and make it draggable
  public Block() {
    this.block = new StackPane();
    this.block.setOnMousePressed(e -> {
      //calculate offset
      startX = e.getSceneX() - this.block.getTranslateX();
      startY = e.getSceneY() - this.block.getTranslateY();
    });

    this.block.setOnMouseDragged(e -> {
      //set new position
      this.block.setTranslateX(e.getSceneX() - startX);
      this.block.setTranslateY(e.getSceneY() - startY);
    });
  }

  //Return the current instance of the block
  public StackPane getBlock() {
    return this.block;
  }

  //Position the block in the top left portion of the given pane
  public void setTopLeft(AnchorPane pane) {
    this.block.setTranslateX(pane.getWidth() / 16);
    this.block.setTranslateY(pane.getHeight() / 16);
  }

  protected void createNode(String nodeType) {
    this.node = NodeMaker.makeNode(nodeType);
  }
}

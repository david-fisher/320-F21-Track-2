package editors.blocks;

public class MoveToTileIndexBlock extends Block {
  public MoveToTileIndexBlock() {
    this.createGenBlock("move to tile", new String[] {"Obj:", "Tile:"});
    //this.createNode();
  }
}

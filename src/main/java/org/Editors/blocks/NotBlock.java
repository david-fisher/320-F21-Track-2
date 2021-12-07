package editors.blocks;

public class NotBlock extends Block {
  public NotBlock() {
    this.createGenBlock("not", new String[] {"Val:"});
    //this.createNode("not");
  }
}


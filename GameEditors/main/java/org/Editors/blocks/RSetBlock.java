package editors.blocks;

public class RSetBlock extends Block {
  public RSetBlock() {
    this.createGenBlock("rset", new String[] {"Reg:", "Obj:"});
    this.createNode("rset");
  }
}

package editors.rule_editor_ui.blocks;

public class RSetBlock extends Block {
  public RSetBlock() {
    this.createGenBlock("rset", new String[] {"Obj:", "Reg:"});
    this.createNode("rset");
  }
}
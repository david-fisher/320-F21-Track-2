package editors.rule_editor_ui.blocks;

public class PSetBlock extends Block {
  public PSetBlock() {
    this.createGenBlock("pset", new String[] {"Prop:", "Obj:", "Val:"});
    //this.createNode("pset");
  }
}

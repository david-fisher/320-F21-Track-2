package editors.rule_editor_ui.blocks;

public class GetBlock extends Block {
  public GetBlock() {
    this.createGenBlock("get", new String[] {"Prop:", "Obj:"});
    this.createNode("get");
  }
}

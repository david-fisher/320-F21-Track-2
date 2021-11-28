package editors.rule_editor_ui.blocks;

public class GetPlayerByIndexBlock extends Block {
  public GetPlayerByIndexBlock() {
    this.createGenBlock("get player[i]", new String[] {"i:"});
    //this.createNode();
  }
}
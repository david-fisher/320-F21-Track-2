package editors.rule_editor_ui.blocks;

public class MoveByStepsBlock extends Block {
  public MoveByStepsBlock() {
    this.createGenBlock("move by steps", new String[] {"Obj:", "Steps:"});
    //this.createNode();
  }
}

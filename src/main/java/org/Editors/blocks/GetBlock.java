package editors.blocks;

public class GetBlock extends Block {
  public GetBlock() {
    this.createGenBlock("get", new String[] {"Prop:", "Obj:"});
    this.createNode("get");
  }
}

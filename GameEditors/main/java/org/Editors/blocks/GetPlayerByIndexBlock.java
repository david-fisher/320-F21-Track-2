package editors.blocks;

public class GetPlayerByIndexBlock extends Block {
  public GetPlayerByIndexBlock() {
    this.createGenBlock("get player[i]", new String[] {"i:"});
    //this.createNode();
  }
}

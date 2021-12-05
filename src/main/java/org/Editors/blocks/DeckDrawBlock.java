package editors.blocks;

public class DeckDrawBlock extends Block {
  public DeckDrawBlock() {
    this.createGenBlock("deck draw", new String[] {"Plcmnt:", "Deck:", "Player:"});
    //this.createNode();
  }
}

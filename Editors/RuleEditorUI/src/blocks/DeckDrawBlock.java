package editors.rule_editor_ui.blocks;

public class DeckDrawBlock extends Block {
  public DeckDrawBlock() {
    this.createGenBlock("deck draw", new String[] {"Plcmnt:", "Deck:", "Player:"});
    //this.createNode();
  }
}
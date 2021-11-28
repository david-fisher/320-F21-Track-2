package editors.rule_editor_ui.blocks;

public class DeckPutBlock extends Block {
  public DeckPutBlock() {
    this.createGenBlock("deck put", new String[] {"Card:", "Plcmnt:", "Deck:"});
    //this.createNode();
  }
}
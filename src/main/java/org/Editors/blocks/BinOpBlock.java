package org.Editors.blocks;

public class BinOpBlock extends Block {
  public BinOpBlock() {
    this.createGenBlock("binary op.", new String[] {"Val1:", "Op:", "Val2:"});
    //this.createNode();
  }
}

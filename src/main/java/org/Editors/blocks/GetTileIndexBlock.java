package org.Editors.blocks;

public class GetTileIndexBlock extends Block {
  public GetTileIndexBlock() {
    this.createGenBlock("get tile[i]", new String[] {"i:"});
    //this.createNode();
  }
}

package org.GameObjects.objects;

public class GameBoard extends GameObject {

  private static int count = 0;

  public GameBoard() {
	  super() ;
	  
	  this.setLabel("gameboard" + String.format("%02d", ++count));
  }

//  public String repr(boolean hasLabel) {
//    return "GameBoard\n" + super.repr(hasLabel);
//  }
}


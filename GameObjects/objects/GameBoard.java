package objects;

public class GameBoard extends GameObject {

  private static int count = 0;

  public GameBoard() {
	  super() ;
	  
	  this.setLabel("gameboard" + String.format("%02d", ++count));
  }

}

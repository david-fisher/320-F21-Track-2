package objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestsV1 {

  /*
  @Test
  public void DieGettersAndSetters() {
    Dice die = new Dice();
    assert (die.getLabel().equals("defaultLabel"));
    assert (die.getColor().equals("#ffffff"));
    assert (die.getIcon().equals("defaultIcon.jpg"));
    assert (die.getNumSides() == 0);
    assert (die.getDotColor().equals("Black"));
    die.setLabel("die1");
    die.setIcon("img.jpg");
    die.setColor("Green");
    assert (die.getLabel().equals("die1"));
    assert (die.getIcon().equals("img.jpg"));
    assert (die.getColor().equals("Green"));
    die.setNumSides(6);
    die.setDotColor("Black");
    assert (die.getNumSides() == 6);
    assert (die.getDotColor().equals("Black"));
    die.setNumSides(10);
    assert (die.getNumSides() == 10);
    die.setDotColor("Red");
    assert (die.getDotColor().equals("Red"));
  }

  @Test
  public void SpinnerGettersAndSetters() { // Need updating for new version of Spinner class methods
    Spinner spin = new Spinner(10);
    assert (spin.getCategoryName(0).equals("defaultCategoryName"));
    assert (spin.getCategoryName(9).equals("defaultCategoryName"));
    assert (spin.getNumCategories() == 10);
    assert (spin.getWeight(0) == 0.1);
    assert (spin.getWeight(9) == 0.1);
    assert (spin.getColor(0).equals("White"));
    assert (spin.getColor(9).equals("White"));
    spin.setNumCategories(4);
    assert (spin.getNumCategories() == 4);
    assert (spin.getWeight(0) == 0.25);
    spin.setNumCategories(20);
    assert (spin.getNumCategories() == 20);
    assert (spin.getWeight(19) == 0.05);
    spin.setWeight(5, 0.1);
    spin.setColor(3, "Yellow");
    spin.setColor("Green");
    assert (spin.getColor(4).equals("White"));
    assert (spin.getColor(3).equals("Yellow"));
    assert (spin.getWeight(4) == 0.05);
    assert (spin.getWeight(5) == 0.1);
  } */

  @Test
  public void CardGettersAndSetters() {
    Card card = new Card();
    assert (card.getText().equals("default text"));
    card.setText("new text");
    assert (card.getText().equals("new text"));
  }

  /*
  @Test
  public void TimerGettersAndSetters() {
    Timer timer = new Timer();
    assert (timer.getInitialTime() == 60);
    timer.setInitialTime(45.5);
    assert (timer.getInitialTime() == 45.5);
  }

  @Test
  public void GameBoardGettersAndSetters() {
    GameBoard game = new GameBoard("square", 10, 10);
    assert (game.getShape().equals("square"));
    assert (game.getHeight() == 10);
    assert (game.getWidth() == 10);
    game.setShape("rectangle");
    game.setHeight(11);
    game.setWidth(25);
    assert (game.getShape().equals("rectangle"));
    assert (game.getHeight() == 11);
    assert (game.getWidth() == 25);
  } */

  @Test
  public void TokenGettersAndSetters() {
    Token token = new Token();
    assert (token.getValue() == 1);
    token.setValue(5);
    assert (token.getValue() == 5);
  }

  /*
  @Test
  public void TileGettersAndSetters() {
    Tile tile = new Tile();
    assert (tile.getConnect().size() == 0);
    Tile tile2 = new Tile();
    tile.addConnect(tile2);
    assert (tile.getConnect().size() == 1);
    tile.deleteConnect(tile2);
    assert (tile.getConnect().size() == 0);
  }

  @Test
  public void GamepieceGettersAndSetters() {
    Gamepiece game = new Gamepiece();
    Tile tile = new Tile();
    game.setLocation(tile);
    assert (game.getLocation().equals(tile));
  } */

}

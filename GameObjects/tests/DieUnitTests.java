package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import objects.Die;

import org.junit.jupiter.api.Test;

class DieUnitTests {

  Die die = new Die();
  int[] count = new int[die.getNumSides()];
  
  @Test
  public void DieProducesOutputWithinExpectedBounds() {
    for (int i = 0; i < 1000; ++i) {
      int roll = die.roll();
      assert (roll > 0 && roll < 7);
    }
    die.setNumSides(23);
    for (int i = 0; i < 1000; ++i) {
      int roll = die.roll();
      assert (roll > 0 && roll < 24);
    }
    die.setNumSides(123);
    for (int i = 0; i < 1000; ++i) {
      int roll = die.roll();
      assert (roll > 0 && roll < 124);
    }
    die.setNumSides(2);
    for (int i = 0; i < 1000; ++i) {
      int roll = die.roll();
      assert (roll > 0 && roll < 3);
    }
    die.setNumSides(48);
    for (int i = 0; i < 1000; ++i) {
      int roll = die.roll();
      assert (roll > 0 && roll < 49);
    }
    die.setNumSides(3);
    for (int i = 0; i < 1000; ++i) {
      int roll = die.roll();
      assert (roll > 0 && roll < 4);
    }
  }
  
  @Test
  public void DefaultDieProducesReasonableRandomDistribution() {
    die = new Die();
    assert (die.getColor().equals(Color.WHITE));
    assert (die.getNumSides() == 6);
    assert (die.getDotColor().equals(Color.BLACK));
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < 6; ++i) {
      assert (count[i] > 40);
    }
  }
  
  @Test
  public void ModifiedDieProducesRRD() {
    die = new Die();
    die.setColor("Green");
    die.setDotColor("White");
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < 6; ++i) {
      assert (count[i] > 40);
    }
  }

  @Test
  public void DieWithMoreSidesProducesRRD() {
    die = new Die();
    die.setColor("Red");
    die.setDotColor("Blue");
    die.setNumSides(15);
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < die.getNumSides(); ++i) {
      assert (count[i] > 20);
    }
    die.setColor("Yellow");
    die.setDotColor("Cyan");
    die.setNumSides(150);
    count = new int[die.getNumSides()];
    for (int i = 0; i < 10000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < die.getNumSides(); ++i) {
      assert (count[i] > 15);
    }
    die.setNumSides(56);
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < die.getNumSides(); ++i) {
      assert (count[i] > 4);
    }
  }
  
  @Test
  public void DieWithFewerSidesProducesRRD() {
    die = new Die();
    die.setColor("LightGray");
    die.setDotColor("Magenta");
    die.setNumSides(5);
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < die.getNumSides(); ++i) {
      assert (count[i] > 60);
    }
    die.setColor("Orange");
    die.setDotColor("Orange");
    die.setNumSides(3);
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < die.getNumSides(); ++i) {
      assert (count[i] > 100);
    }
    die.setColor("Orange");
    die.setDotColor("Orange");
    die.setNumSides(2);
    count = new int[die.getNumSides()];
    for (int i = 0; i < 1000; ++i) {
      count[die.roll() - 1]++;
    }
    for (int i = 0; i < die.getNumSides(); ++i) {
      assert (count[i] > 300);
    }
  }
}

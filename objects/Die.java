package objects;

public class Die extends GameObject {
  int numSides;
  String dotColor;

  public Die() {
    this.numSides = 0;
    this.dotColor = "Black";
  }

  public int roll() {
    // TODO
    return 1;
  }

  public void setNumSides(int num) {
    this.numSides = num;
  }

  public int getNumSides() {
    return this.numSides;
  }

  public void setDotColor(String color) {
    this.dotColor = color;
  }

  public String getDotColor() {
    return this.dotColor;
  }
}

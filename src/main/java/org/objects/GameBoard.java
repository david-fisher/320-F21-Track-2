
package org.objects;

public class GameBoard extends GameObject {
  private String shape;
  private double height;
  private double width;

  public GameBoard(String shape, double height, double width) {
    this.shape = shape;
    this.height = height;
    this.width = width;
  }

  public void setShape(String shape) {
    this.shape = shape;
  }

  public String getShape() {
    return this.shape;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getHeight() {
    return this.height;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getWidth() {
    return this.width;
  }
}

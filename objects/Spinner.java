package objects;

import java.util.ArrayList;

public class Spinner extends GameObject {
  private int numCategories;
  private ArrayList<String> categories;
  private ArrayList<Double> weights;
  private ArrayList<String> colors;

  public Spinner(int num) {
    this.numCategories = num;
    this.categories = new ArrayList<String>();
    this.weights = new ArrayList<Double>();
    this.colors = new ArrayList<String>();
    for (int i = 0; i < num; ++i) {
      weights.add((double) 1 / num);
      categories.add("defaultCategoryName");
      colors.add("White");
    }
  }

  public String spin() {
    // TODO
    return "";
  }

  public void setNumCategories(int num) {
    if (this.numCategories != num) {
      Spinner t = new Spinner(num);
      this.categories = t.categories;
      this.weights = t.weights;
      this.colors = t.colors;
    }
    this.numCategories = num;
  }

  public int getNumCategories() {
    return this.numCategories;
  }

  public void setCategoryName(int i, String categoryName) {
    categories.set(i, categoryName);
  }

  public String getCategoryName(int i) {
    return categories.get(i);
  }

  public void setWeight(int i, double weight) {
    weights.set(i, weight);
  }

  public double getWeight(int i) {
    return weights.get(i);
  }

  public void setColor(int i, String color) {
    colors.set(i, color);
  }

  public String getColor(int i) {
    return colors.get(i);
  }
}

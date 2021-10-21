package objects;

public abstract class GameObject {
  private String label;
  private int quantity;
  private String icon;
  private String color;

  GameObject() {
    label = "defaultLabel";
    quantity = 1;
    icon = "defaultIcon.jpg";
    color = "#ffffff";
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getIcon() {
    return this.icon;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColor() {
    return this.color;
  }
}

package objects;

public class Card extends GameObject {
  private String text;

  public Card() {
    this.text = "defaultText";
  }

  public void setText(String str) {
    this.text = str;
  }

  public String getText() {
    return this.text;
  }
}

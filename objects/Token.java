package objects;

public class Token {
  private int value;

  public Token() {
    this.value = 1;
  }

  public void setValue(int num) {
    this.value = num;
  }

  public int getValue() {
    return this.value;
  }
}

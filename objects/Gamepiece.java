package objects;

public class Gamepiece {
  private Tile location;

  public Gamepiece() {
    this.location = new Tile();
  }

  public Tile getLocation() {
    return this.location;
  }

  public void setLocation(Tile tile) {
    this.location = tile;
  }
}

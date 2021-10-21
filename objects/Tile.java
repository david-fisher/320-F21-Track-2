package objects;

import java.util.ArrayList;

public class Tile {
  private ArrayList<Tile> connections;

  public Tile() {
    this.connections = new ArrayList<Tile>();
  }

  public ArrayList<Tile> getConnect() {
    return this.connections;
  }

  public void addConnect(Tile tile) {
    this.connections.add(tile);
  }

  public void deleteConnect(Tile tile) {
    this.connections.remove(tile);
  }
}

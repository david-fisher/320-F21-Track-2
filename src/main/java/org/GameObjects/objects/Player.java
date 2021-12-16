package org.GameObjects.objects;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject {
    private static int count = 0;

    private ArrayList<Gamepiece> gamePieces;
    private ArrayList<GameObject> inventory;

    public Player() {
        gamePieces = new ArrayList<>();
        inventory = new ArrayList<>();
        this.setLabel("button" + String.format("%02d", ++count));
        this.setColorString("#FFFFFF");
        this.setGamePieces(gamePieces);
        this.setInventory(inventory);
        this.setIsHuman(false);
        this.setLastUsedObj(null);
    }

    public Player clone() {
        ArrayList<Gamepiece> y = new ArrayList<Gamepiece>();
        for (Gamepiece c: this.getGamePieces()) {
            y.add(c.clone());
        }
        ArrayList<GameObject> z = new ArrayList<GameObject>();
        for (GameObject c: this.getInventory()) {
            z.add(c.clone());
        }
        Player x = new Player(this.getLabel(), this.getColor(), y, z, this.getIsHuman());
        return x;
    }
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

      // run game object's set trait first
      if (super.setTrait(trait, value, suppressTraitChecker)) {
          return true;
      }

      // checks for other valid inputs
      else if (suppressTraitChecker || // if true don't check trait type
              (trait.equals("lastUsedObj") && value instanceof GameObject) ||
              (trait.equals("isHuman") && value instanceof Boolean) ||
              (trait.equals(""))) {
          prevTraits.put(trait, traits.get(trait)) ;
          traits.put(trait, value);
          return true;
      }

      // returns false if input is invalid
      return false;
    }

    public boolean setLastUsedObj(GameObject gameobj){
        return this.setTrait("lastUsedObj", gameobj);
    }

    public GameObject getLastUsedObj(){
        return (GameObject) this.getTrait("lastUsedObj");
    }
    
    public void setGamePieces(ArrayList<Gamepiece> gamePieces) { 
        this.gamePieces = gamePieces;
    }
    
    public void setInventory(ArrayList<GameObject> inventory) { 
        this.inventory = inventory;
    }

    public void addInventory(GameObject gameobj){
        this.getInventory().add(gameobj);
    }

    public void removeInventory(GameObject gameobj){
        setLastUsedObj(gameobj);
        this.getInventory().remove(gameobj);
    }

    public void removeInventory(int index){
        GameObject gameobj = this.getInventory().get(index);
        setLastUsedObj(gameobj);
        this.getInventory().remove(index);
    }

    public void addInventoryAtIndex(int index, GameObject gameobj){
        this.getInventory().set(index, gameobj);
    }
    
    public boolean setIsHuman(boolean isHuman) {
      return this.setTrait("isHuman", isHuman);
    }

    public boolean getIsHuman() {
        return (Boolean) this.traits.get("isHuman");
    }

    public ArrayList<Gamepiece> getGamePieces() { 
        return this.gamePieces;
    }
    
    public ArrayList<GameObject> getInventory() { 
      return this.inventory;
    }

    public void addPiece(Gamepiece piece) { 
        this.getGamePieces().add(piece); 
    }
    
    public void removePiece(Gamepiece piece) { 
        this.getGamePieces().remove(piece); 
    }
    
    public void removePiece(int pieceIndex) { 
        this.getGamePieces().remove(pieceIndex); 
    }
    
    public void setPiece(int pieceIndex, Gamepiece piece) { 
        this.getGamePieces().set(pieceIndex, piece); 
    }

    public Color getRandomColor(){
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        return Color.rgb(red, green, blue, .99);
    }

    @Override
    public String toString() {
        return "Player{" +
                "Label='" + this.getLabel() + '\'' +
                ", color=" + this.getColor() +
                ", gamePieces=" + this.getGamePieces() +
                ", inventory=" + this.getInventory() +
                ", isHuman=" + this.getIsHuman() +
                '}';
    }
}
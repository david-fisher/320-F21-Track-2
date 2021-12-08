package objects;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject {

    private ArrayList<Gamepiece> gamePieces;
    private ArrayList<GameObject> inventory;

    private boolean isHuman;

    public Player(String Label, Color color, ArrayList<Gamepiece> gamePieces, ArrayList<GameObject> inventory, boolean isHuman) {
        this.setLabel(Label);
        this.setColor(color);
        this.setGamePieces(gamePieces);
        this.setInventory(inventory);
        this.setIsHuman(isHuman);
    }

    public Player(String Label, ArrayList<Gamepiece> gamePieces, ArrayList<GameObject> inventory, boolean isHuman) {
        this.setLabel(Label);
        this.setColor(getRandomColor());
        this.setGamePieces(gamePieces);
        this.setInventory(inventory);
        this.setIsHuman(isHuman);
    }
    
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

      // run game object's set trait first
      if (super.setTrait(trait, value, suppressTraitChecker)) {
          return true;
      }

      // checks for other valid inputs
      else if (suppressTraitChecker || // if true don't check trait type
              (trait.equals("gamePieces") && value instanceof ArrayList) ||
              (trait.equals("inventory") && value instanceof ArrayList) ||
              (trait.equals("isHuman") && value instanceof Boolean)) {
          prevTraits.put(trait, traits.get(trait)) ;
          traits.put(trait, value);
          return true;
      }

      // returns false if input is invalid
      return false;
    }
    
    public boolean setGamePieces(ArrayList<Gamepiece> gamePieces) { 
        return this.setTrait("gamePieces", gamePieces);
    }
    
    public boolean setInventory(ArrayList<GameObject> inventory) { 
      return this.setTrait("inventory", inventory);
    }
    
    public boolean setIsHuman(boolean isHuman) {
      return this.setTrait("isHuman", isHuman);
    }

    public ArrayList<Gamepiece> getGamePieces() { 
        return (ArrayList<Gamepiece>) this.traits.get("gamePieces");
    }
    
    public ArrayList<GameObject> getInventory() { 
      return (ArrayList<GameObject>) this.traits.get("inventory");
    }
    
    public boolean getIsHuman() { 
        return (Boolean) this.traits.get("isHuman");
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

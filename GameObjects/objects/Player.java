package objects;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject {

    private String Label;

    private Color color;
    private ArrayList<Gamepiece> gamePieces;
    private ArrayList<GameObject> inventory;

    private boolean isHuman;

    public Player(String Label, Color color, ArrayList<Gamepiece> gamePieces, ArrayList<GameObject> inventory, boolean isHuman) {
        this.Label = Label;
        this.color = color;
        this.gamePieces = gamePieces;
        this.inventory = inventory;
        this.isHuman = isHuman;
    }

    public Player(String Label, ArrayList<Gamepiece> gamePieces, ArrayList<GameObject> inventory, boolean isHuman) {
        this.Label = Label;
        this.gamePieces = gamePieces;
        this.inventory = inventory;
        this.isHuman = isHuman;

        this.color = getRandomColor();
    }
    
    public void setGameTokens(ArrayList<Gamepiece> gamePieces) { this.gamePieces = gamePieces; }
    public void setInventory(ArrayList<GameObject> inventory) { this.inventory = inventory; }
    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public ArrayList<Gamepiece> getGamePieces() { return this.gamePieces; }
    public ArrayList<GameObject> getInventory() { return this.inventory; }
    public boolean getIsHuman() { return this.isHuman; }

    // modifiers
    public void addPiece(Gamepiece piece) { this.gamePieces.add(piece); }
    public void removePiece(Gamepiece piece) { this.gamePieces.remove(piece); }
    public void removePiece(int pieceIndex) { this.gamePieces.remove(pieceIndex); }
    public void setPiece(int pieceIndex, Gamepiece piece) { this.gamePieces.set(pieceIndex, piece); }

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
                "Label='" + Label + '\'' +
                ", color=" + color +
                ", gamePieces=" + gamePieces +
                ", inventory=" + inventory +
                ", isHuman=" + isHuman +
                '}';
    }
}

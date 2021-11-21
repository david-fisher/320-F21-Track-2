package objects;

import java.awt.Color;
import engine.GameState;


public class Demo {

	public static void main(String[] args) {
		Savable.intitDB();
//		Project p = Savable.createProject("hello", "J:\\Cs 320");
		Project x = Savable.getProjects().get(0);
		Savable.setProject(x);
		GameState g = Savable.getGameState(true);
		Savable.saveGameState(g, true);
		Savable.closeDB();
	}

}

package objects;

import java.awt.Color;
import java.util.ArrayList;

import engine.GameState;


public class Demo {

	public static void main(String[] args) {
		Savable.intitDB();
//		Project p = Savable.createProject("hello");
		GameState x = Savable.getProjects().get(0).getIntiGS();
		x.dice = new ArrayList<Die>();
		x.dice.add(new Die());
		Savable.closeDB();
	}

}

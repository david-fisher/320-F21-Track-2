package org.GameObjects.objects;

import java.awt.Color;
import java.util.ArrayList;

import org.RuleEngine.engine.GameState;


public class Demo {

	public static void main(String[] args) {
		Savable.intitDB();
//		Project p = Savable.createProject("hello", "J:\\Cs 320");
		Project x = Savable.getProjects().get(0);
		Savable.setProject(x);
		GameState g = Savable.getGameState(true);
		Savable.saveGameState(g, true);
//		Project p = Savable.createProject("hello");
		GameState x = Savable.getProjects().get(0).getIntiGS();
		x.dice = new ArrayList<Die>();
		x.dice.add(new Die());
		Savable.closeDB();
	}

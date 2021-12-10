package org.GameObjects.objects;

import java.awt.Color;
import java.util.ArrayList;

import org.RuleEngine.engine.GameState;

public class Demo {

	public static void main(String[] args) {
		Savable.intitDB();
//		Project p = Savable.createProject("hello");
		GameState x = Savable.getProjects().get(0).getIntiGS();
//		GameState x = p.getIntiGS();
//		x.dice = new ArrayList<Die>();
		x.dice.get(0).setNumSides(6);
//		x.dice.add(new Die());
		Savable.closeDB();
	}

}
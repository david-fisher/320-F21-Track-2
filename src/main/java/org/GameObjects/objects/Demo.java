package org.GameObjects.objects;

import org.RuleEngine.engine.GameState;

public class Demo {

	public static void main(String[] args) {
		Savable.initDB();
//		Project p = Savable.createProject("hello");
		GameState x = Savable.getProjects().get(0).getInitGS();
//		GameState x = p.getIntiGS();
//		x.dice = new ArrayList<Die>();
		x.dice.get(0).setNumSides(6);
//		x.dice.add(new Die());
		Savable.closeDB();
	}

}
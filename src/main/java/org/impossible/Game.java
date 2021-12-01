package org.impossible;

public class Game {
	public static void pprint(String board) {
		String analogue = "";
		for (int i=0; i<board.length();i++) {
			if (board.charAt(i)=='0') {
				analogue = analogue + ' ';
			} else if (board.charAt(i)=='1') {
				analogue = analogue + 'X';
			} else {
				analogue = analogue + 'O';
			}
		}
		System.out.println(""+analogue.charAt(0)+'|'+analogue.charAt(1)+'|'+analogue.charAt(2));
		System.out.println("-----");
		System.out.println(""+analogue.charAt(3)+'|'+analogue.charAt(4)+'|'+analogue.charAt(5));
		System.out.println("-----");
		System.out.println(""+analogue.charAt(6)+'|'+analogue.charAt(7)+'|'+analogue.charAt(8));
	}
	public static void help() {
		System.out.println("0|1|2");
		System.out.println("-----");
		System.out.println("3|4|5");
		System.out.println("-----");
		System.out.println("6|7|8");
	}
	public static void phelp(State b) {
		String board = b.toString();
		String analogue = "";
		for (int i=0; i<board.length();i++) {
			if (board.charAt(i)=='0') {
				analogue = analogue + ' ';
			} else if (board.charAt(i)=='1') {
				analogue = analogue + 'X';
			} else {
				analogue = analogue + 'O';
			}
		}
		System.out.println(""+analogue.charAt(0)+'|'+analogue.charAt(1)+'|'+analogue.charAt(2)+"\t0|1|2");
		System.out.println("-----\t-----");
		System.out.println(""+analogue.charAt(3)+'|'+analogue.charAt(4)+'|'+analogue.charAt(5)+"\t3|4|5");
		System.out.println("-----\t-----");
		System.out.println(""+analogue.charAt(6)+'|'+analogue.charAt(7)+'|'+analogue.charAt(8)+"\t6|7|8");
	}
	public static void main(String args[]) {
		State s = new State();
		MinimaxAgent agent1 = new MinimaxAgent('1');
		HumanAgent agent2 = new HumanAgent('2');
		int i = 0;
		while (s.incomplete()) {
			i++;
			//System.out.println("Turn " + Integer.toString(i) + ": " + agent1.size() + ", " + agent2.size());
			System.out.println("Turn " + Integer.toString(i) + ":");
			if (s.player == '1') {
				s = agent1.move(s);
			} else {
				s = agent2.move(s);
			}
			phelp(s);
		}
	}
}

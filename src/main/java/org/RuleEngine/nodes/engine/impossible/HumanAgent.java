package org.RuleEngine.nodes.engine.impossible;

import java.util.Scanner;

public class HumanAgent {
	private char player;
	public HumanAgent(char p) {
		this.player = p;
	}
	public char other() {
		if (this.player=='1') {
			return '2';
		} else {
			return '1';
		}
	}
	public int size() {
		return 0;
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
	public State move(State s) {
		phelp(s);
		System.out.println("Please select output:");
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		String s0 = s.toString();
		s0 = s0.substring(0, Integer.valueOf(choice)) + this.player +  s0.substring(Integer.valueOf(choice)+1);
		return new State(this.other(), s0);
	}	
}

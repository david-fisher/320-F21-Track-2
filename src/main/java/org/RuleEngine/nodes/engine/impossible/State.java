package org.RuleEngine.nodes.engine.impossible;

import java.util.ArrayList;

public class State {
	public char player;
	private String board;

	public State() {
		this.player = '1';
		this.board = "000000000";
	}

	public State(char player, String board) {
		this.player = player;
		this.board = board;
	}
	
	public boolean incomplete() {
		return !this.isFull() && !victory('1') && !victory('2');
	}
	
	public boolean loss(char person) {
		if (person=='1') {
			return victory('2');
		} else {
			return victory('1');
		}
	}
	
	public boolean victory(char person) {
		return rowWin(0, person) 
			|| rowWin(1, person)
			|| rowWin(2, person)
			|| colWin(0, person)
			|| colWin(1, person)
			|| colWin(2, person)
			|| diagWin(0, person)
			|| diagWin(1, person);
	}
	
	public boolean rowWin(int row, char person) {
		return this.board.charAt(3*row)==person
			&& this.board.charAt(3*row + 1)==person
			&& this.board.charAt(3*row + 2)==person;
	}
	
	public boolean colWin(int col, char person) {
		return this.board.charAt(col)==person
			&& this.board.charAt(3+col)==person
			&& this.board.charAt(6+col)==person;
	}
	
	public boolean diagWin(int diag, char person) {
		if (diag == 0) {
			return this.board.charAt(0)==person
				&& this.board.charAt(4)==person
				&& this.board.charAt(8)==person;
		} else {
			return this.board.charAt(2)==person 
				&& this.board.charAt(4)==person
				&& this.board.charAt(6)==person;
		}
	}
	
	public ArrayList<Integer> emptyList() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i=0; i<9; i++) {
			if (this.board.charAt(i)=='0') {
				l.add(i);
			}
		}
		return l;
	}
	
	public boolean isFull() {
		return emptyList().size()==0;
	}
	
	public ArrayList<State> getSucessors() {
		ArrayList<State> succ = new ArrayList<State>();
		if (this.victory('1') || this.victory('2') || this.isFull()) {
			return succ;
		}
		char nextPlayer;
		if (this.player=='1') {
			nextPlayer='2';
		} else {
			nextPlayer='1';
		}
		for (Integer i : emptyList()) {
			succ.add(new State(nextPlayer, this.board.substring(0, i) + this.player + this.board.substring(i+1)));
		}
		return succ;
	}
	
	public String toString() {
		return this.board;
	}
}

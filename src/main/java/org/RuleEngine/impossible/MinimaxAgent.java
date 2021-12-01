package org.RuleEngine.impossible;

public class MinimaxAgent {
	//private HashMap<String, Double> cache;
	//private HashMap<String, Double> cache2;
	//private HashMap<String, Double> cache3;
	private char player;
	public MinimaxAgent(char p) {
		this.player = p;
		//this.cache = new HashMap<String, Double>();
		//this.cache2 = new HashMap<String, Double>();
		//this.cache3 = new HashMap<String, Double>();
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
		//return this.cache.size();
	}
	public double scoreState(State s) {
		//if (this.cache.containsKey(s.toString())) {
		//	return this.cache.get(s.toString());
		//} else 
		if (s.victory(this.player)) {
		//	this.cache.put(s.toString(), 1000.0);
			return 1.0;
		} else if (s.loss(this.player)) {
		//	this.cache.put(s.toString(), -1000.0);
			return -1.0;
		} else if (s.isFull()) {
		//	this.cache.put(s.toString(), 0.0);
			return 0.0;
		} else {
			return 0.0;
		}
	}
	public State move(State s) {
		double bestVal;
		State best = s;
		if (this.player==s.player) {
			bestVal = Integer.MIN_VALUE+0.5;
		} else {
			bestVal = Integer.MAX_VALUE-0.5;
		}
		
		for (State s0: s.getSucessors()) {
			double util = this.minimax(s0);
			if ((this.player==s.player && util > bestVal) || (this.player!=s.player && util < bestVal)) {
				bestVal = util;
				best = s0;
			}
		}
		return best;
	}
	public double minimax(State s) {
		double v = maxHelper(s);
		return v;
	}
	public double maxHelper(State s) {
		if (!s.incomplete()) {
			return this.scoreState(s);
		} 
		//else if (this.cache2.containsKey(s.toString())) {
		//	return this.cache2.get(s.toString());
		//}
		Double v = Integer.MIN_VALUE+0.5;
		for (State s0: s.getSucessors()) {
			v = Math.max(v, minHelper(s0));
		}
		//this.cache2.put(s.toString(), v);
		return v;
	}
	public double minHelper(State s) {
		if (!s.incomplete()) {
			return this.scoreState(s);
		} 
		//else if (this.cache3.containsKey(s.toString())) {
		//	return this.cache3.get(s.toString());
		//}
		Double v = Integer.MAX_VALUE-0.5;
		for (State s0: s.getSucessors()) {
			v = Math.min(v, maxHelper(s0));
		}
		//this.cache3.put(s.toString(), v);
		return v;
	}
}

package controller;

import model.Agent;

public class Phase1 {

	private static Phase1 instance;
	private Agent agent1;
	
	private Phase1() {
	}
	
	public Phase1 getInstance() {
		if (instance == null) {
			instance = new Phase1();
		}
		return instance;
	}
}

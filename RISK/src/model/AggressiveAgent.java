package model;

import java.util.ArrayList;

public class AggressiveAgent extends Agent{

	public final static int ID = 3;
	
	public AggressiveAgent() {
		super();
	}
	
	public void play() {
		addBonous();
		int MaxArmiesNodeIdx = getMaxTerritoryIndex();
		int numOfArmies = territories.get(MaxArmiesNodeIdx).getNumberOfArmies();
		territories.get(MaxArmiesNodeIdx).setNumberOfArmies(numOfArmies + bonus);
		bonus = 0;
		attack();
	}
	
	private void addBonous() {
		int index = getMaxTerritoryIndex();
		int numOfArmies = territories.get(index).getNumberOfArmies();
		territories.get(index).setNumberOfArmies(numOfArmies + bonus);
	}
	
	private void attack() {
		int max = 0;
		int attackNodeId = -1;
		int enemyNodeId = -1;
		for (int i = 0 ; i < territories.size() ; i++) {
			canAttack(territories.get(i), attackNodeId, enemyNodeId, max);
		}
		if (enemyNodeId != -1) {
			conquer(attackNodeId,enemyNodeId);
			bonus = 2;
		}
	}
	
	/**
	 * 
	 * @param node
	 * @param enemyId
	 * @param max
	 */
	private void canAttack(Node node, int attackNodeId, int enemyId, int max) {
		ArrayList<Node> neighbors = node.getNeighbors();
		for (int i = 0 ; i < neighbors.size() ; i++) {
			if (neighbors.get(i).getId() != ID && 
					(node.getNumberOfArmies() - neighbors.get(i).getNumberOfArmies() > 1)) {
				if(neighbors.get(i).getNumberOfArmies() > max) {
					max = neighbors.get(i).getNumberOfArmies();
					enemyId = neighbors.get(i).getId();
					attackNodeId = node.getId();
				}
			}
		}
	}
}

package model;

import java.util.ArrayList;

public abstract class Agent {
	
	protected int bonus;
	protected ArrayList<Node> territories;
	protected ArrayList<Node> opponentTerritories;
	
	public Agent() {
		territories = new ArrayList<>();
		opponentTerritories = new ArrayList<>();
		bonus = 0;
	}
	
	public void addTerritory(Node territory) {
		territories.add(territory);
	}
	
	public void setBonous(int bonous) {
		this.bonus = bonous;
	}
	
	public ArrayList<Node> getTerritories() {
		return territories;
	}
	
	public void conquer(int agentNodeId, int enemyNodeId) {
		int i;
		for (i = 0; i < territories.size() ; i++) {
			if (territories.get(i).getId() == agentNodeId)
				break;
		}
		int j;
		for(j = 0 ; j < opponentTerritories.size() ; j++) {
			if (opponentTerritories.get(j).getId() == enemyNodeId)
				break;
		}
		int humanNodeArmies = territories.get(i).getNumberOfArmies();
		int enemyNodeArmies = opponentTerritories.get(j).getNumberOfArmies();
		if(humanNodeArmies - enemyNodeArmies > 1) {
			territories.get(i).setNumberOfArmies(humanNodeArmies - enemyNodeArmies - 1);
			Node node = opponentTerritories.get(j);
			node.setNumberOfArmies(1);
			opponentTerritories.remove(j);
			territories.add(node);
			bonus = 2;
		}
	}
	
	/**
	 * this will be override and implemented according to the agent type.
	 */
	public void play() {
		
	}
	
	/**
	 * 
	 * @return the index of territory which has the maximum number 
	 * of armies. 
	 */
	public int getMaxTerritoryIndex() {
		return 0;
	}
	
	/**
	 * 
	 * @return the index of territory which has the minimum number 
	 * of armies.
	 */
	public int getMinTerritoryIndex() {
		return 0;
	}
}

package model;

import java.awt.Point;
import java.util.ArrayList;

public class NearlyPacifistAgent extends Agent{

	public final static int ID = 4;
	
	public NearlyPacifistAgent() {
		super();
	}
	
	@Override
	public void play() {
		Node minTerritory = territories.get(getMinTerritoryIndex());
		minTerritory.setNumberOfArmies(minTerritory.getNumberOfArmies() + bonus);
		bonus = 0;
		int minIndex = 0;
		int minNeighbourIndex = 0;
		int minNum = Integer.MAX_VALUE;
		Point temp;
		for (int i = 0; i < territories.size(); i++) {
			int curNumOfArmies = territories.get(i).getNumberOfArmies();
			temp = getMinNeighbour(territories.get(i).getNeighbors(), curNumOfArmies);
			if (temp.x < minNum) {
				minNum = temp.x;
				minIndex = i;
				minNeighbourIndex = temp.y;
			}
		}
		
		if (minNum == Integer.MAX_VALUE) {
			return;
		}
		Node newNode = territories.get(minIndex).getNeighbors().get(minNeighbourIndex);
		int newArmies = territories.get(minIndex).getNumberOfArmies() - newNode.getNumberOfArmies() - 1;
		territories.get(minIndex).setNumberOfArmies(newArmies);
		newNode.setNumberOfArmies(1);
		addTerritory(newNode);
		deleteFromOpponent (newNode.getId());
		
	}
	
	void deleteFromOpponent(int id) {
		for (int i = 0; i < opponentTerritories.size(); i++) {
			if (opponentTerritories.get(i).getId() == id) {
				opponentTerritories.remove(i);
				break;
			}
		}
	}
	
	Point getMinNeighbour(ArrayList<Node> neighbours, int armies) {
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		for (int i = 0; i < neighbours.size(); i++) {
			if ((ID != neighbours.get(i).getAgentId()) && 
					(armies > neighbours.get(i).getNumberOfArmies() + 1) && 
					(neighbours.get(i).getNumberOfArmies() < min)) {
				min = neighbours.get(i).getNumberOfArmies();
				minIndex = i;
			}
		}
		return new Point(min, minIndex);
	}
}

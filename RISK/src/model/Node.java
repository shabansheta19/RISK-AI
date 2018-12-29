package model;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
	
	private int id;
	private int numberOfArmies;
	private ArrayList<Node> neighbors;
	private int agentId;
	
	public Node(int id) {
		this.id = id;
		neighbors = new ArrayList<>();
	}
	
	@Override
	public int compareTo(Node o) {
		if (this.numberOfArmies == o.numberOfArmies) {
			if (this.id < o.id) {
				return -1;
			}
			return 1;
		}
		else if (this.numberOfArmies < o.numberOfArmies)	
			return -1;
		return 1;
	}
	
	public int getId() {
		return id;
	}
	
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	
	public int getAgentId() {
		return agentId;
	}
	
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}
	
	public int getNumberOfArmies() {
		return numberOfArmies;
	}
	
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	
	public void addNeighbor(Node neighbor) {
		neighbors.add(neighbor);
	}
}

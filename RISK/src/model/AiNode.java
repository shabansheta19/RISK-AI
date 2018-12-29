package model;

public class AiNode {
	private int id;
	private int numOfArmies;
	
	public AiNode(int id) {
		this.id = id;
	}
	
	public AiNode(int id, int numOfArmies) {
		super();
		this.id = id;
		this.numOfArmies = numOfArmies;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumOfArmies() {
		return numOfArmies;
	}
	
	public void setNumOfArmies(int numOfArmies) {
		this.numOfArmies = numOfArmies;
	}
}

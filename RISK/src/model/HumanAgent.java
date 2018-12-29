package model;

public class HumanAgent extends Agent{

	public final static int ID = 3;
	
	private int humanNodeId;
	private int enemyNodeId;
	
	public HumanAgent() {
		super();
	}
	
	public void play() {
		conquer(humanNodeId, enemyNodeId);
	}
	
	public void setParticipateNodes(int humanNodeId , int enemyNodeId) {
		this.humanNodeId = humanNodeId;
		this.enemyNodeId = enemyNodeId;
	}
}

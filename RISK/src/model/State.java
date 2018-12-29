package model;

import java.util.ArrayList;

public class State {
	private ArrayList<AiNode> agentTerritories;
	private ArrayList<AiNode> opponentTerritories;
	private float g_of_n;
	private State parent;

	public State() {
		parent = null;
		agentTerritories = new ArrayList<>();
		opponentTerritories = new ArrayList<>();
	}
	
	public ArrayList<AiNode> getAgentTerritories() {
		return agentTerritories;
	}

	public void setAgentTerritories(ArrayList<AiNode> agentTerritories) {
		this.agentTerritories = agentTerritories;
	}

	public ArrayList<AiNode> getOpponentTerritories() {
		return opponentTerritories;
	}

	public void setOpponentTerritories(ArrayList<AiNode> opponentTerritories) {
		this.opponentTerritories = opponentTerritories;
	}
	
	public float getG_of_n() {
		return g_of_n;
	}
	
	public void setG_of_n(float g_of_n) {
		this.g_of_n = g_of_n;
	}
	
	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}
	
	public float getHeuristicValue() {
		return agentTerritories.size() - opponentTerritories.size(); 
	}
}

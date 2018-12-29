package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;

import model.AiNode;
import model.Node;
import model.State;

public class Search {

	protected PriorityQueue<State> frontier;
	protected ArrayList<State> explored;
	private Hashtable<Integer, ArrayList<Integer>> graph; 
	
	public Search() {
		graph = Phase2.getInstance().getGraph();
	}
 	
 	/**
 	 * this method will be same for the algorithms that we will
 	 * use.
 	 * @param state the object of the state
 	 */
 	public State startSearch(State initialState, Comparator<State> costValueComparator) {
 		frontier = new PriorityQueue<>(costValueComparator);
		explored = new ArrayList<>();
		frontier.add(initialState);
		
		while(!frontier.isEmpty()) {
			State curState = frontier.remove();
			explored.add(curState);
			
			if (isGoal(curState)) {
				return curState;
			}
			addAgentBonus(curState);
			addNeighbors(curState);
		}
		return null;
 	}
 	
 	private void addAgentBonus(State curState) {
		int bonus = 2 * curState.getAgentTerritories().size();
		ArrayList<AiNode> agentNodes = curState.getAgentTerritories();
		int max = agentNodes.get(0).getNumOfArmies();
		int maxIdx = 0;
		for (int j = 1 ; j < agentNodes.size() ; j++) {
			if (agentNodes.get(j).getNumOfArmies() > max) {
				max = agentNodes.get(j).getNumOfArmies();
				maxIdx = j;
			}
		}
		agentNodes.get(maxIdx).setNumOfArmies(agentNodes.get(maxIdx).getNumOfArmies() + bonus);
	}
 	
 	private void addOpponentBonus(State state) {
		int bonus = 2 * state.getOpponentTerritories().size();
		ArrayList<AiNode> opponentNodes = state.getOpponentTerritories();
		int min = opponentNodes.get(0).getNumOfArmies();
		int minIdx = 0;
		for (int j = 1 ; j < opponentNodes.size() ; j++) {
			if (opponentNodes.get(j).getNumOfArmies() < min) {
				min = opponentNodes.get(j).getNumOfArmies();
				minIdx = j;
			}
		}
		opponentNodes.get(minIdx).setNumOfArmies(opponentNodes.get(minIdx).getNumOfArmies() + bonus);
	}

	private void addNeighbors(State curState) {
		 ArrayList<AiNode> aiAgentNodes = curState.getAgentTerritories();
		 for (int i = 0 ; i < aiAgentNodes.size() ; i++) {
			 AiNode node = aiAgentNodes.get(i);
			 ArrayList<Integer> neighbors = graph.get(node.getId());
			 ArrayList<AiNode> opponentNodes = curState.getOpponentTerritories();
			 for (int j = 0 ; j < opponentNodes.size() ; j++) {
				 AiNode opponentNode = opponentNodes.get(j);
				 if (neighbors.contains(opponentNode.getId()) && (node.getNumOfArmies() - opponentNode.getNumOfArmies() > 1)) {
					 State newState = getNewState(curState, node.getId(), opponentNode);
					 //newState.getAgentTerritories().add(opponentNode);
					 if (!inExplored(newState)) {
						 if(!inFrontier(newState))
							 frontier.add(newState);
					 }
				 }
			 }
		 }
	}
	
	private boolean inFrontier(State newState) {
		ArrayList<State> states = new ArrayList<>();
		Iterator<State> itr = frontier.iterator(); 
        while (itr.hasNext()) 
            states.add(itr.next());
        if (states.isEmpty())
        	return false;
		for (int i = 0; i < states.size(); i++) {
			ArrayList<AiNode> x = states.get(i).getAgentTerritories();
			ArrayList<AiNode> y = newState.getAgentTerritories();
			if (x.size() == y.size()) {
				for (int j = 0; j < x.size(); j++) {
					boolean found = false;
					for (int k = 0; k < x.size(); k++) {
						if (x.get(j).getId() == y.get(k).getId() && x.get(j).getNumOfArmies() == y.get(k).getNumOfArmies())
							found = true;
					}
					if(!found)
						return false;
				}
			} else {
				return false;
			}
			
			x = states.get(i).getOpponentTerritories();
			y = newState.getOpponentTerritories();
			if (x.size() == y.size()) {
				for (int j = 0; j < x.size(); j++) {
					boolean found = false;
					for (int k = 0; k < x.size(); k++) {
						if (x.get(j).getId() == y.get(k).getId() && x.get(j).getNumOfArmies() == y.get(k).getNumOfArmies())
							found = true;
					}
					if(!found)
						return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean inExplored(State newState) {
		ArrayList<AiNode> y = newState.getAgentTerritories();
		ArrayList<AiNode> z = newState.getOpponentTerritories();
		for (int i = 0; i < explored.size(); i++) {
			ArrayList<AiNode> x = explored.get(i).getAgentTerritories();
			if (x.size() == y.size()) {
				for (int j = 0; j < x.size(); j++) {
					boolean found = false;
					for (int k = 0; k < x.size(); k++) {
						if (x.get(j).getId() == y.get(k).getId() && x.get(j).getNumOfArmies() == y.get(k).getNumOfArmies())
							found = true;
					}
					if(!found)
						return false;
				}
			} else {
				return false;
			}
			
			x = explored.get(i).getOpponentTerritories();
			if (x.size() == z.size()) {
				for (int j = 0; j < x.size(); j++) {
					boolean found = false;
					for (int k = 0; k < x.size(); k++) {
						if (x.get(j).getId() == z.get(k).getId() && x.get(j).getNumOfArmies() == z.get(k).getNumOfArmies())
							found = true;
					}
					if(!found)
						return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public State getNewState(State curState, int agentNodeId, AiNode opponentNode) {
		ArrayList<AiNode> agentNodes = curState.getAgentTerritories();
		ArrayList<AiNode> opponentNodes = curState.getOpponentTerritories();
		ArrayList<AiNode> newAgentNodes = new ArrayList<>();
		for (int i = 0 ; i < agentNodes.size() ; i++) {
			AiNode node = new AiNode(agentNodes.get(i).getId());
			node.setNumOfArmies(agentNodes.get(i).getNumOfArmies());
			if (node.getId() == agentNodeId)
				node.setNumOfArmies(node.getNumOfArmies() - opponentNode.getNumOfArmies() - 1);
			newAgentNodes.add(node);
		}
		ArrayList<AiNode> newOpponentNodes = new ArrayList<>();
		for (int i = 0 ; i < opponentNodes.size() ; i++) {
			if (opponentNodes.get(i).getId() != opponentNode.getId()) {
				AiNode node = new AiNode(opponentNodes.get(i).getId());
				node.setNumOfArmies(opponentNodes.get(i).getNumOfArmies());
				newOpponentNodes.add(node);
			}
		}
		AiNode node = new AiNode(opponentNode.getId());
		node.setNumOfArmies(1);
		newAgentNodes.add(node);
		State state = new State();
		state.setAgentTerritories(newAgentNodes);
		state.setOpponentTerritories(newOpponentNodes);
		state.setParent(curState);
		state.setG_of_n(curState.getG_of_n() + 1);
		if (!state.getOpponentTerritories().isEmpty())
			addOpponentBonus(state);
		return state;
	}

	private boolean isGoal(State state) {
 		if (state.getOpponentTerritories().isEmpty() || state.getAgentTerritories().isEmpty())
 			return true;
 		return false;
 	}
 	
 	/**
 	 * this method will return the cost of state.
 	 * this method will be override and implemented according
 	 * to the search algorithm.
 	 * @param state the object of the state.
 	 * @return the cost of the state.
 	 */
 	public float getCost(State state) {
 		return 0;
 	}
}

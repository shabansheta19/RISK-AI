package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;

import model.State;
import utils.Constants;

public class Phase2 {
	private static Phase2 instance;
	private static Hashtable<Integer, ArrayList<Integer>> graph; 

	private Phase2() {
	}
	
	public static Phase2 getInstance() {
		if (instance == null) {
			instance = new Phase2();
			graph = new Hashtable<>();
		}
		return instance;
	}
	
	public ArrayList<State> getPath(String searchType, State initialState) {
		Comparator<State> costValueComparator = null;
		if (searchType.equals(Constants.GREEDY)) {
			costValueComparator = new Comparator<State>() {
	            @Override
	            public int compare(State s1, State s2) {
	                return (int) (s2.getHeuristicValue() - s1.getHeuristicValue());
	            }
	        };
		} else if (searchType.equals(Constants.A_STAR)) {
			costValueComparator = new Comparator<State>() {
	            @Override
	            public int compare(State s1, State s2) {
	            	return (int) ((s2.getHeuristicValue()+s2.getG_of_n()) - (s1.getHeuristicValue()+s1.getG_of_n()));
	            }
	        };
		}
		ArrayList<State> path = new ArrayList<>();
		State state = new Search().startSearch(initialState, costValueComparator);
		if(state != null) {
			path.add(0, state);
			while (state.getParent() != null) {
				path.add(0, state.getParent());
				state = state.getParent();
			}
		}
		return path;
	}
	
	public Hashtable<Integer, ArrayList<Integer>> getGraph() {
		return graph;
	}

	public void setGraph(Hashtable<String, String> graphSpecification) {
		int numOfV = Integer.parseInt(graphSpecification.get(Constants.NUM_OF_V));
		for (int i = 0; i < numOfV; i++)
			graph.put(i+1, new ArrayList<Integer>());
		int numOfE = Integer.parseInt(graphSpecification.get(Constants.NUM_OF_E));
		String[] edges = graphSpecification.get(Constants.EDGES).split(",");
		for (int i = 0; i < numOfE; i++) {
			String[] vertices = edges[i].split(" ");
			int v1 = Integer.parseInt(vertices[0]);
			int v2 = Integer.parseInt(vertices[1]);
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}
	}
}

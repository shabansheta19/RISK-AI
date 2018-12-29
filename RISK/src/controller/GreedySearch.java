package controller;

import java.util.Comparator;

import model.State;

public class GreedySearch extends Search {
	
	public GreedySearch() {
		super();
		/*// A custom comparator that compares two Strings by their length.
        */
	}
	
	public float getCost(State state) {
		return state.getHeuristicValue();
	}

}

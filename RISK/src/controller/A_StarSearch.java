package controller;

import java.util.Comparator;

import model.State;

public class A_StarSearch extends Search {
	
	public A_StarSearch() {
		super();
		/*// A custom comparator that compares two Strings by their length.
        Comparator<State> costValueComparator = new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return 1;
            }
        };*/
	}
	
	public float getCost(State state) {
		return state.getG_of_n() + state.getHeuristicValue();
	}

}

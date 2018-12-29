import java.util.ArrayList;
import java.util.Hashtable;

import controller.Phase2;
import model.AiNode;
import model.State;
import utils.Constants;
import utils.FileParser;
import view.GUI;

public class Main {

	public static void main(String[] args) {
		FileParser fileParser = new FileParser();
		Hashtable<String, String> graphSpecification = fileParser.getGraphSpecification("board.txt");
		
		Phase2.getInstance().setGraph(graphSpecification);
		State initialState = new State();
		ArrayList<AiNode> agent = new ArrayList<>();
		agent.add(new AiNode(1,22));
		agent.add(new AiNode(4,9));
		ArrayList<AiNode> opponent = new ArrayList<>();
		opponent.add(new AiNode(2,5));
		opponent.add(new AiNode(3,8));
		initialState.setAgentTerritories(agent);
		initialState.setOpponentTerritories(opponent);
		ArrayList<State> path = Phase2.getInstance().getPath(Constants.A_STAR, initialState);
		
		for (int i = 0; i < path.size(); i++) {
			System.out.println("----------------(" + i + ")-------------------");
			System.out.print("Agent --> ");
			ArrayList<AiNode> x = path.get(i).getAgentTerritories();
			for (int j = 0; j < x.size(); j++) {
				System.out.print("(" + x.get(j).getId() + "," + x.get(j).getNumOfArmies() + ")  ");
			}
			System.out.println();
			System.out.print("Opponent --> ");
			ArrayList<AiNode> y = path.get(i).getOpponentTerritories();
			for (int j = 0; j < y.size(); j++) {
				System.out.print("(" + y.get(j).getId() + "," + y.get(j).getNumOfArmies() + ")  ");
			}
			System.out.println();
		}
	}
}

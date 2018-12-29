package model;

public class CompletelyPassiveAgent extends Agent{

	public CompletelyPassiveAgent() {
		super();
	}
	
	@Override
	public void play() {
		if (bonus <= 0) {
			return;
		}
		Node minTerritory = territories.get(getMinTerritoryIndex());
		minTerritory.setNumberOfArmies(minTerritory.getNumberOfArmies() + bonus);
		bonus = 0;
	}
	
}

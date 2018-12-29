package utils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class FileParser implements IFileParser{
	
	public FileParser() {
	}

	@Override
	public Hashtable<String, String> getGraphSpecification(String fileName) {
		Hashtable<String, String> graphSpecification = new Hashtable<>();
		try (Scanner scanner = new Scanner(new File(fileName))) {
			String numOfV = scanner.nextLine().split(" ")[1];
			graphSpecification.put(Constants.NUM_OF_V, numOfV);
			int numOfE = Integer.parseInt(scanner.nextLine().split(" ")[1]);
			graphSpecification.put(Constants.NUM_OF_E, ""+numOfE);
			String edges = "";
			for (int i = 0 ; i < numOfE ; i++)
				edges += scanner.nextLine() + ",";
			graphSpecification.put(Constants.EDGES, edges);
			
			int numOfC = Integer.parseInt(scanner.nextLine().split(" ")[1]);
			graphSpecification.put(Constants.NUM_OF_CELLS, ""+numOfC);
			String cells = "";
			for (int i = 0 ; i < numOfC ; i++)
				cells += scanner.nextLine() + ",";
			graphSpecification.put(Constants.CELLS, cells);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graphSpecification;
	}

	@Override
	public Hashtable<String, String> getInitialPlacement(String fileName) {
		Hashtable<String, String> initialPlacement = new Hashtable<>();
		try (Scanner scanner = new Scanner(new File(fileName))) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return initialPlacement;
	}
}

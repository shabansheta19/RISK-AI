package utils;

import java.util.Hashtable;

public interface IFileParser {
	
	/**
	 * this method is used  to read the graph specification from a file
	 * and parse it into hash table, the keys are (number of edges, number 
	 * of vertices, edges, number of partition cells and the partition cells).
	 * @param fileName the name of the text file that contains the graph 
	 * specification.
	 * @return the hash table contains the graph components and its values.
	 */
	public Hashtable<String, String> getGraphSpecification(String fileName);
	
	/**
	 * this method is used to read the initial placement of armies from a
	 * file and parse it into hash table the key is the vertex number and 
	 * the value is the number of armies in the vertex. 
	 * @param fileName the name of the text file that contains the initial 
	 * placement.
	 * @return the hash table.
	 */
	public Hashtable<String, String> getInitialPlacement(String fileName);

}

/**@author Austin Feydt
 * 4/7/2015
 * EECS 233
 *Programming Assignment #3
 *This class represents a node, containing a word
 *and its number of encounters so far*/
public class StringNode {
	//Word in the node
	private String word;
	//Current number of encounters
	private int encounters;
	
	/**2-Arg constructor
	 * 
	 * @param word
	 * @param encounters
	 */
	public StringNode(String word, int encounters){
		this.word = word;
		this.encounters = encounters;
	}
	
	/**This method sets the word of the StringNode
	 * @param word  desired word */
	public void setWord(String word){
		this.word = word;
	}
	
	/**This method returns the word associated to the StringNode
	 * @return  the word in the StringNode*/
	public String getWord(){
		return this.word;
	}
	
	/**This method sets the number of encounters of the word in the StringNode
	 * @param encounters  how many times the word has appeared*/
	public void setEncounters(int encounters){
		this.encounters = encounters;
	}
	
	/**This method returns the number of encounters of the word in the StringNode
	 * @return  how many times the word has appeared*/
	public int getEncounters(){
		return this.encounters;
	}
}
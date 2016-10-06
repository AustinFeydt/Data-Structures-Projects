/**@author Austin Feydt
 * 4/7/2015
 * EECS 233
 * Programming Assignment #3
 * This class represents a HashTable of LinkedLists arrays*/
import java.util.*;
import java.math.*;
public class HashTable{
	//Array of LinkedLists to hold words
	private LinkedList<StringNode>[] table;
	//The current size of the array
	private int tableSize;
	//The current number of unique entries
	private int uniqueWords = 0;
	//The current number of array entries that are not null
	private int nonZeroEntries = 0;
	
	/**1-Arg Constructor
	 * @param tableSize  desired initial lenght of array*/
	public HashTable(int tableSize){
		this.table = new LinkedList[tableSize]; 
		this.tableSize = tableSize;
	}
	
	/**This method returns the array of LinkedLists of the hashtable
	 * @return  the table*/
	public LinkedList<StringNode>[] getTable(){
		return table;
	}

	/**This method returns the current length of the table
	 * @return  length of array*/
	public int getTableSize(){
		return tableSize;
	}

	/**This method returns the current number of unique entries
	 * @return  total chaining*/
	public int getUniqueWords() {
		return uniqueWords;
	}

	/**This method returns the current number of nonempty array entries
	 * @return  number of array entries that are not currently null*/
	public int getNonZeroEntries(){
		return nonZeroEntries;
	}

	/**This method inserts a given word into the HashTable,
	 * focusing on whether or not the HashTable already contains
	 * the word
	 * @param word  input word
	 */
	public void insert(String word){
		word = word.toLowerCase();
		//Hashes the absolute value of the word (incase of overflow)
		int hashing = Math.abs(word.hashCode()%tableSize);
		//Handles case when the LinkedList hasn't been initialized yet
		if(table[hashing] == null){
			table[hashing] = new LinkedList<StringNode>();
			table[hashing].add(new StringNode(word, 1));
			uniqueWords++;
			nonZeroEntries++;
		}
		else{
			//Whether or not the word was already in the list
			boolean existed = false;
			
			//Loops through every node of the current LinkedList
			for(StringNode n : table[hashing] ){
				//Increments word's encounter field
				if(n.getWord().equals(word)){
					n.setEncounters(n.getEncounters()+1);
					existed = true;
				}
			}
			//If the word was not found in the LinkedList
			if(!existed){
				//Creates new entry in LinkedList
				table[hashing].addLast(new StringNode(word,1));
				uniqueWords++;
			}
		}	
	}
	
	/**This method resizes the hashtable and rehashes
	 * every StringNode in it as well*/
	public void resize(){
		int oldSize = tableSize;
		tableSize = oldSize*2;
		//Creates temporary array that's twice as big as the old one
		LinkedList<StringNode>[] temp = new LinkedList[tableSize];
		
		//Loops through every element of the old array
		for(LinkedList<StringNode> n : table){
			/*If current LinkedList isn't null, loops through every StringNode and
			 * inserts the StringNode into the temporary array, based on new hashing*/
			if(n != null){
				for(StringNode k : n)
				reInsert(k, temp);
			}
		}
		//Sets field to point to the new array
		table = temp;
	}
	
	/**This helper method rehashes each StringNode based on the new table
	 * size and inserts each into the new array
	 * @param node  node to be reInserted
	 * @param temp  where the node is reInserted*/
	private void reInsert(StringNode node, LinkedList<StringNode>[] temp){
		//Hashes word based on the new table size
		int hashing = Math.abs(node.getWord().hashCode()%tableSize);
		//Initializes the LinkedList if it hasn't been yet
		if(temp[hashing] == null)
			temp[hashing] = new LinkedList<StringNode>();
		//Adds the node to the end of the LinkedList
		temp[hashing].add(node);
	}
	
	/**This overridden method returns the necessary
	 * statistics about the HashTable*/
	public String toString(){
		return "OK; Total unique words: " + uniqueWords + ", Hashtable size: " + tableSize 
				+ ", Average length of collision lists: " + (double)uniqueWords/tableSize;
	}
}
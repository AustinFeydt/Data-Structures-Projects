/**@author Austin Feydt
 *3/25/2015
 *EECS 233
 *Programming Assignment #2
 *This class represents a HuffmanNode used to hold a character and its
 *frequency*/
public class HuffmanNode implements Comparable<HuffmanNode> {
	private Character inChar; //Character in the node
	private int frequency;    //Character's frequency
	private HuffmanNode left; //Node's left child
	private HuffmanNode right;//Node's right child
	
	/**4- arg constructor
	 * @param c  character to be placed in node
	 * @param f  character's frequency
	 * @param left  character's left child
	 * @param right  character's right child
	 */
	public HuffmanNode(Character c, int f, HuffmanNode left, HuffmanNode right){
		inChar = c;
		frequency = f;
		this.left = left;
		this.right = right;
	}
	
	/**This method sets the character value of the HuffmanNode
	 * @param c  desired character*/
	public void setInChar(Character c){
		inChar = c;
	}
	
	/**This method returns the character in the HuffmanNode
	 * @return  the node's associated character value*/
	public Character getInChar(){
		return inChar;
	}
	
	/**This method sets the frequency of the HuffmanNode's character
	 * @param f  desired frequency*/
	public void setFrequency(int f){
		frequency = f;
	}
	
	/**This method returns the frequency of the HuffmanNode's character
	 * @return  the frequency*/
	public int getFrequency(){
		return frequency;
	}
	
	/**This method sets the current HuffmanNode's left child
	 * @param l  desired left child*/
	public void setLeft(HuffmanNode l){
		left = l;
	}
	
	/**This method returns the current node's left child
	 * @return  left child*/
	public HuffmanNode getLeft(){
		return left;
	}
	
	/**This method sets the current HuffmanNode's right child
	 * @param r  desired right child*/
	public void setRight(HuffmanNode r){
		right = r;
	}
	
	/**This method returns the current node's right child
	 * @return  right child*/
	public HuffmanNode getRight(){
		return right;
	}
	
	/**This method compares two HuffmanNodes based on their frequencies
	 * @param h2  the other node in the comparison
	 * @return  negative if this is less than h2,
	 * positive if this is more than h2, 
	 * and 0 if same*/
	public int compareTo(HuffmanNode h2){
		return this.frequency - h2.frequency;
	}
}
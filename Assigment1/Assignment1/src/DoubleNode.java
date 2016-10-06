/**@author Austin Feydt
 *EECS 233
 *Programming Assignment #1
 *12 February 2015
 *This class contains a node, which will be used to create a linked list of DoubleNodes*/
public class DoubleNode {
	//The element in the node
	private double element;
	//The next node in the list
	private DoubleNode next = null; 
	
	/**2-arg Constructor
	 * @param element  the element being placed in this new node
	 * @param next  the next node in the chain of nodes */
	public DoubleNode(double element, DoubleNode next){
		this.element = element;
		this.next = next;
	}
	
	/**This method sets the element of the node
	 * @param element  desired element to be placed in node*/
	public void setElement(double element){
		this.element = element;
	}
	
	/**This method returns the element of the desired node
	 * @return  element in node*/
	public double getElement(){
		return this.element;
	}
	
	/**This method sets the next node of the current node
	 * @param next  the desired next node*/
	public void setNext(DoubleNode next){
		this.next = next;
	}
	
	/**This method retunrs the next node of the current node
	 * @return  the desired next node*/
	public DoubleNode getNext(){
		return this.next;
	}
}
/**@author Austin Feydt
 *EECS 233
 *Programming Assignment #1
 *12 February 2015
 *This class implements the NumList ADT to function as an single Linked List
 *of DoubleNodes.*/
public class NumLinkedList implements NumList {
	//The first node in the list
	private DoubleNode head; 
	//The current number of nodes in the list
	private int size;		  
	
	/**This method returns the size of the NumLinkedList.
	 * Worst-case running time: O(1) (constant)
	 * @return  number of nodes*/
	public int size(){
		return this.size;
	}
	
	/**This method inserts a DoubleNode before the ith component of the list 
	 * (where i=0 is the head of the list).
	 * Worst-case running time: O(n)
	 * @param i  the desired index
	 * @param value  the desired component to be inserted*/
	public void insert(int i, double value){
		//Creates a pointer used to traverse the list
		DoubleNode ptr = head;
		//Inserting an element before the current head
		if(head == null || i == 0)
			head = new DoubleNode(value, head);
		//Inserting an element at the end of the list by default
		else if(i > size){
			while( ptr.getNext() != null)
				ptr = ptr.getNext();
			ptr.setNext(new DoubleNode(value, null));
		}
		//Inserting an element in the middle of the list
		else{		
			for(int j = 0; j != i-1; j++)
				ptr = ptr.getNext();
			ptr.setNext(new DoubleNode(value, ptr.getNext()));
		}
		size++;
	}
	
	/**This method removes the ith component in the NumLinkedList (where i=0
	 * is the head of the list).  If the list contains less than i components,
	 * then nothing happens.
	 * Worst-case running time: O(n) 
	 * @param i  the desired index of the component to remove.
	 */
	public void remove(int i){
		if( i < size){
			//Creates a pointer used to traverse the list
			DoubleNode ptr = head;
			//Deals with removal of the head
			if(i==0)
				head = head.getNext();
			//Removing an element in the middle of the list
			else{
				for(int j = 0; j != i-1; j++)
					ptr = ptr.getNext();
				ptr.setNext(ptr.getNext().getNext());
			}
			size--;
		}
	}
	
	/**This method returns the ith component of the NumLinkedList (where i=0
	 * is the head of the list).  If the list has fewer than i+1 components,
	 * a RuntimeException is thrown.
	 * Worst-case running time: O(n)
	 * @return  the element within the ith component.*/
	public double lookup(int i){
		//Deals with the ith component not existing
		if( size < i+1)
			throw new RuntimeException("The list does not have that many elements");
		else{
			//Creates a pointer used to traverse list
			DoubleNode ptr = head;
			for(int j = 0; j!= i; j++)
				ptr = ptr.getNext();
			return ptr.getElement();
		}
	}
}
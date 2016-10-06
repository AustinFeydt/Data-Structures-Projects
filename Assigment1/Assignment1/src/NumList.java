/**@author Austin Feydt
 *EECS 233
 *Programming Assignment #1
 *12 February 2015
 *This interface is an abstract data type used for a sequence of
 *'double' primitive values.*/
public interface NumList {
	
	/**This method counts the number of elements currently in the 
	 * sequence.
	 * @return  the current length of the sequence*/
	public int size();
	
	/**This method inserts the desired element right before the ith
	 * index of the sequence, unless the sequence has less than i 
	 * elements, in which it is inserted at the end of the sequence.
	 * @param i  the desired index of the sequence
	 * @param value  the desired element to be inserted*/
	public void insert(int i, double value);
	
	/**This method removes the ith element in the sequence. If there
	 * are fewer than i elements in the sequence, nothing is 
	 * changed.
	 * 
	 * @param i  the desired index of the sequence*/
	public void remove(int i);
	
	/**This method returns the ith element in the sequence.
	 * @param i  the desired index of the sequence
	 * @return the value of the ith element of the sequence.
	 */
	public double lookup(int i);
}
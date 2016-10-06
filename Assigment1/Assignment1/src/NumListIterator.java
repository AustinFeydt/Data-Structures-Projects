/**@author Austin Feydt
 *EECS 233
 *Programming Assignment #1
 *12 February 2015
 *This class is an iterator which will be used to access the private fields 
 *inside of a NumList*/
public class NumListIterator {
	//The list being iterated through
	NumList list;
	//A counter to keep track of where we are in the list
	int counter = 0;
	
	/**1-Arg Constructor
	 * @param lst  the desired Numlist to create an iterator for*/
	public NumListIterator (NumList lst){
		this.list = lst;
	}
	
	/**This method determines if we are at the end of the NumList.
	 * @return  true if we've reached end of NumList, otherwise false */
	public boolean hasNext(){
		return(counter != list.size());
	}
	
	/**This method accesses the current element in the NumList, or
	 * raises an exception if there are no more elements
	 * @return  the current element of NumList*/
	public double next(){
		if(!hasNext())
			throw new NullPointerException("Falling off list");
		double val = list.lookup(counter);
		counter++;
		return val;
	}
}
/**@author Austin Feydt
 *EECS 233
 *Programming Assignment #1
 *12 February 2015
 *This class implements the NumList ADT to function as an array of doubles.*/
public class NumArrayList implements NumList {
	//The maximum length of the array
	private int arraySize = 100;
	//The array used to hold the sequence of doubles
	private double[] list = new double[arraySize];
	//The current number of elements in the sequence of doubles
	private int size = 0;	
	//Whether to increase or decrease the size of the array
	private int resize = 0;
		
	/**This method returns the current size of the NumArrayList
	 * Worst-case running time: O(1) (constant)
	 * @return  current size*/
	public int size(){
		return this.size;
	}
	
	/**This method inserts the desired element right before the ith
	 * index of the sequence, unless the sequence has less than i 
	 * elements, in which it is inserted at the end of the sequence.
	 * Worst-case running time: O(n)
	 * @param i  the desired index of the sequence
	 * @param value  the desired element to be inserted*/
	public void insert(int i , double value){
		if(size + 1 > arraySize){
			resize = 1;
			list = resizeList(resize);
		}
		if(i > size -1)
			list[size] = value;
		else{
			//Creates a temporary array so no information is lost during insertion
			double[] temp = new double[arraySize];
			//Copies the data into the temporary array
			for(int j = 0; j < size; j++)
				temp[j] = list[j];
			list[i] = value;
			//Copies the data back into the original array
			for(int k = i; k < size; k++)
				list[k+1] = temp[k];
		}
		size++;
	}
	
	/**This method removes the ith element in the sequence. If there
	 * are fewer than i elements in the sequence, nothing is 
	 * changed.
	 * Worst-case running time: O(n)
	 * @param i  the desired index of the sequence*/
	public void remove(int i){
		if(i <= size-1){
			//Creates a temporary array to hold original array's data
			double[] temp = new double[arraySize];
			//Copies data into temporary array
			for(int j = 0; j < size; j++)
				temp[j] = list[j];
			//Copies data back into original array
			for(int k = i; k < size - 1; k++)
				list[k] = temp[k+1];
			list[size-1] = 0;
			size--;
			if(size < arraySize/2){
				resize = -1;
				list = resizeList(resize);
			}
		}
	}
	
	/**This method returns the ith element in the sequence.
	 * Worst-case running time: O(1) (constant)
	 * @param i  the desired index of the sequence
	 * @return the value of the ith element of the sequence.
	 */
	public double lookup(int i){
		if(size < i+1)
			throw new RuntimeException("The list does not have that many elements");
		else
			return list[i];
	}
	
	/**This helper method resizes the array without losing any elements.
	 * Worst-case running time: O(n)
	 * @return  the address of the new array*/
	public double[] resizeList(int i){
		if(i == 1)
			arraySize *=2;
		else
			arraySize /=2;
		double[] newList = new double[arraySize];
		for(int j = 0; j < size; j++)
			newList[j] = list[j];
		resize = 0;
		return newList;
	}
}
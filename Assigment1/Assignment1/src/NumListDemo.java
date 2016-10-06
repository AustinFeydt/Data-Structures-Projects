/**@author Austin Feydt
 *EECS 233
 *Programming Assignment #1
 *12 February 2015
 *This class demos the NumArrayList and NumLinkedList classes.*/
public class NumListDemo {
	/**This method finds the mean of all doubles contained inside of the NumList.
	 * @param lst  the NumList to be used
	 * @return  the mean of the list*/
	public static double meanSequence(NumList lst){
		//Creating an iterator to traverse the NumList
		NumListIterator it = new NumListIterator(lst);
		double total = 0;
		int size = lst.size();
		//Loops through every element in the iterator
		while(it.hasNext())
			total += it.next();
		return total/size;
	}
	
	/**This main method tests both a NumArrayList and a NumLinkedList.
	 * @param args  array of user String inputs*/
	public static void main(String[] args){
		//Creating a NumArrayList and inserting elements into it
		NumArrayList arrayList = new NumArrayList();
		arrayList.insert(0, 12);
		arrayList.insert(0,11);
		arrayList.insert(0, 10);
		arrayList.insert(3, 9);
		arrayList.insert(1, 8);
		arrayList.insert(1, 7);
		
		//Displaying the mean of the contents in the NumArrayList
		System.out.println("The mean of the NumArrayList is: "
				+ meanSequence(arrayList) + "\n");
		
		//Creating a NumLinkedList and inserting elements into it
		NumLinkedList linkedList = new NumLinkedList();
		linkedList.insert(0, 12);
		linkedList.insert(0,11);
		linkedList.insert(0, 10);
		linkedList.insert(3, 9);
		linkedList.insert(1, 8);
		linkedList.insert(1, 7);
		
		//Displaying the mean of the contents in the NumLinkedList
		System.out.println("The mean of the NumLinkedList is: "
				+ meanSequence(linkedList));
	}
}
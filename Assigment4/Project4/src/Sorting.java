/**@author Austin Feydt
 * 4/22/15
 * EECS 233
 * Programming Assignment #4
 *This class contains three different sorting methods that also return
 *the approximated running time of the sort*/
public class Sorting {
	
	/**This method emulates the heap sort algorithm, turning the input
	 * array into a max-at-top array representation of a heap, and then
	 * returning the max value every time, sifting, and repeating.
	 * @param arr  the array to be sorted
	 * @return  the runtime of the method*/
	public static long heapSort(int[] arr){
		long startTime = System.nanoTime();
		//Build max on top array representation of heap
		int[] heap = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			heap[i] = arr[i];
			//Sifts new element up every time
			siftUp(heap, i);
		}
		int endUnsorted = arr.length -1;
		//Loops until every element is removed from heap
		while(endUnsorted >= 0){
			//Puts the largest element at the end of the array
			arr[endUnsorted]=heap[0];
			siftDown(heap, endUnsorted);
			endUnsorted--;
		}
		return System.nanoTime() - startTime;
	}
	
	/**This helper method sifts the given element as far up the heap
	 * as it can go
	 * @param a  input heap
	 * @param i  index to begin sifting from*/
	static void siftUp(int[] a, int i){
		if( i!= 0 && a[i] > a[(i-1)/2]){
			swap(a,i,(i-1)/2);
			siftUp(a, (i-1)/2);
		}
	}
	
	/**This helper method sifts the given element down as far as it can
	 * go (modeled after method in the notes)
	 * @param a  input heap
	 * @param i  index to begin sifting*/
	static void siftDown(int[] a, int i){
		a[0] = a[i];
		int toSift = a[0];
		int parent = 0;
		int child = 2*parent + 1;
		while(child < i){
			if(child +1 < i && a[child + 1] > a[child]){
				child ++;
			}
			if(toSift >= a[child])
				break;
			a[parent]=a[child];
			parent = child;
			child=2*parent+1;
		}
		a[parent]=toSift;
	}
	
	/**This method emulates the quick sort algorithm on an array of integers 
	 * @param arr  input array
	 * @return  the runtime of the method*/
	static long quickSort(int[] arr){
		long startTime = System.nanoTime();
		//Makes call to recursive helper method
		myQuickSort(arr, 0, arr.length -1);
		return System.nanoTime() - startTime;
	}
	
	/**This recursive helper method calls itself on the smaller 
	 * halves based on pivot point
	 * @param arr  input array
	 * @param first  first index to be referenced
	 * @param last  last index to be referenced*/
	static void myQuickSort(int[] arr, int first, int last){
		if(first >= last)
			return;
		int split = partition(arr, first, last);
		myQuickSort(arr, first, split);
		myQuickSort(arr, split+1, last);
	}
	
	/**This method finds the pivot point of the given index and swaps values
	 * in array when needed
	 * @param arr  input array
	 * @param first  first index to be referenced
	 * @param last  last index to be referenced
	 * @return  pivot point for recursive quick sort*/
	static int partition(int[] arr, int first, int last){
		int pivot = arr[(first + last)/2];
		int i = first -1;
		int j = last +1;
		while(true){
			do{
				i++;
			}while(arr[i] < pivot);
			do{
				j--;
			}while(arr[j] > pivot);
			if(i<j)
				swap(arr,i,j);
			else
				return j;
		}
	}
	
	/**This method swaps two elements of an array
	 * @param arr  input array
	 * @param i  first element
	 * @param j  second element*/
	static void swap(int[] arr, int i, int j){
		int k = arr[i];
		arr[i] = arr[j];
		arr[j] = k;
	}
	
	/**This method emulates the merge sort algorithm and returns
	 * the runtime of the method.  It does not copy the partially sorted
	 * array back into the original every time, but alternates between the two
	 * @param arr  array to be sorted
	 * @return  the runtime of the method*/
	static long mergeSort(int[] arr){
		long startTime = System.nanoTime();
		//Creates temp array of the same size
		int[] temp = new int[arr.length];
		//Keeps track of where the final sorted array ends up
		boolean toTemp = true;
		//Loops through every power of two
		for(int i = 2; i <= arr.length; i*=2){
			for(int left = 0; left+i<=arr.length; left+=i){
				//Merges into temp array
				if(toTemp)
					merge(arr, temp, left, left + i/2 -1, left + i/2, left+i-1);
				//Merges into original array
				else
					merge(temp, arr, left, left + i/2 -1, left + i/2, left + i -1);
			}
			//Changes boolean flag for next power of 2
			if(toTemp)
				toTemp = false;
			else
				toTemp = true;
		}
		//If the sorted array ends up in the temp
		if(!toTemp){
			for(int i = 0; i < arr.length; i++)
				arr[i] = temp[i];
		}
		return System.nanoTime() - startTime;
	}
	
	/**This method compares values and merges sub arrays together
	 * @param arr  array to be referenced
	 * @param temp  array to be overwritten
	 * @param leftStart  beginning of left comparison
	 * @param leftEnd  end of left comparison
	 * @param rightStart  beginning of right comparison
	 * @param rightEnd  end of right comparison*/
	static void merge(int[] arr, int[] temp, int leftStart, int leftEnd,
			int rightStart, int rightEnd){
		//Temp values for reference
		int i = leftStart;
		int j = rightStart;
		int k = leftStart;
		
		//Loops until either left or right counters go too far
		while(i!=rightStart && j <= rightEnd){
			if(arr[i] > arr[j]){
				temp[k] = arr[j];
				j++;
				k++;
			}
			else{
				temp[k] = arr[i];
				i++;
				k++;
			}
		}
		//Fills in leftover values based on where comparison ended
		if(i==rightStart){
			for(k=k; k <= rightEnd; k++){
				temp[k] = arr[j];
				j++;
			}
		}
		else
			for(k=k; k<=rightEnd; k++){
				temp[k] = arr[i];
				i++;
			}
	}
}
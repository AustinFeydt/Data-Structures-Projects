import java.io.*;
/**@author austin
 *4/21/15
 *EECS 233
 *Programming Assignment #4
 *This class shows that the sorting algorithms do work and outputs
 *results to files*/
public class Reporting2 {

	public static void main(String[] args) throws IOException{	
		File file  = new File(args[0]);
		//File doesn't exist
		if(!file.exists())
			throw new IOException("Input file not found!");
		//To read the file in UTF8
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String line;
		//Creates a total of 9 arrays, 3 for each sorting algorithm
		int[] heapArray1 = new int[16];
		int[] heapArray2 = new int[16];
		int[] heapArray3 = new int[16];
		int[] quickArray1 = new int[16];
		int[] quickArray2 = new int[16];
		int[] quickArray3 = new int[16];
		int[] mergeArray1 = new int[16];
		int[] mergeArray2 = new int[16];
		int[] mergeArray3 = new int[16];

		//Keeps track of where in the array the element needs to go
		int counter = 0;
		while((line = in.readLine()) != null){
			//Parses the int and puts it in all 12 arrays
			heapArray1[counter] = Integer.parseInt(line);
			heapArray2[counter] = Integer.parseInt(line);
			heapArray3[counter] = Integer.parseInt(line);
			quickArray1[counter] = Integer.parseInt(line);
			quickArray2[counter] = Integer.parseInt(line);
			quickArray3[counter] = Integer.parseInt(line);
			mergeArray1[counter] = Integer.parseInt(line);
			mergeArray2[counter] = Integer.parseInt(line);
			mergeArray3[counter] = Integer.parseInt(line);
			counter++;
		}
		in.close();
		
		//Creates the output files using naming guidelines
		File heapOut = new File("apf31HS.txt");
		File quickOut =  new File("apf31QS.txt");
		File mergeOut = new File("apf31MS.txt");
		
		//Array of times for the heap sort
		long[] heapResults = new long[3];
		heapResults[0] = Sorting.heapSort(heapArray1);
		heapResults[1] = Sorting.heapSort(heapArray2);
		heapResults[2] = Sorting.heapSort(heapArray3);
		
		//Array of times for the quick sort
		long[] quickResults = new long[3];
		quickResults[0] = Sorting.quickSort(quickArray1);
		quickResults[1] = Sorting.quickSort(quickArray2);
		quickResults[2] = Sorting.quickSort(quickArray3);
	
		//Array of times for the merge sort
		long[] mergeResults = new long[3];
		mergeResults[0] = Sorting.mergeSort(mergeArray1);
		mergeResults[1] = Sorting.mergeSort(mergeArray2);
		mergeResults[2] = Sorting.mergeSort(mergeArray3);
		
		//Writer objects to output to the three files from earlier
		BufferedWriter h = new BufferedWriter(new FileWriter(heapOut));
		BufferedWriter q = new BufferedWriter(new FileWriter(quickOut));
		BufferedWriter m = new BufferedWriter(new FileWriter(mergeOut));
		
		//Loops through every element in the sorted array and writes to files
		for(int i: heapArray1)
			h.write(i + "\n");
		for(int j: quickArray1)
			q.write(j + "\n");
		for(int k: mergeArray1)
			m.write(k + "\n");
		h.close();
		q.close();
		m.close();
		
		//Displays results
		System.out.println("HSapf31 = " 
				+ getMedian(heapResults[0], heapResults[1], heapResults[2])
				+ " ns; QSapf31 = "
				+ getMedian(quickResults[0], quickResults[1], quickResults[2])
				+ " ns; MSapf31 = "
				+ getMedian(mergeResults[0], mergeResults[1], mergeResults[2])
				+ " ns");
	}
	/**This method compares three long, and outputs the median
	 * @param a  first number
	 * @param b  second number
	 * @param c  third number
	 * @return  the median of the three number*/
	public static long getMedian(long a, long b, long c){
		if(a > b){
			if(b>c)
				return b;
			else if(a>c)
				return c;
			else
				return a;
			}
		else{
			if(a>c)
				return a;
			else if( b>c)
				return c;
			else
				return b;
		}
	}
}

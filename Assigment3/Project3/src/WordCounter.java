/**@author Austin Feydt
 * 4/7/2015
 * EECS 233
 * Programming Assignment #3 
 * This class uses the custom HashTable to store encounters of each word in the input
 * text file*/
import java.io.*;
import java.util.*;
public class WordCounter {
	//Hashtable used to keep track of encounters of each word
	private HashTable hashtable = new HashTable(2);
	
	/**This method runs through the entire process of building a hashtable from
	 * an input text file, and then outputting the results
	 * @param input_file  desired file to read
	 * @param output_file  name of output file
	 * @return  Whether or not the method ran successfully*/
	public static String wordCount(String input_file, String output_file){
		//Creates WordCounter Object
		WordCounter w = new WordCounter();
		try{
			//Reads file and builds HashTable
			w.readFile(input_file);
		}
		//Error message for improper input file name
		catch(IOException e){
			return "Input file error";
		}
		//Writes to the output file
		w.writeFile(output_file);
		//Returns statistics
		return w.hashtable.toString();
	}
	
	/**This method attempts to read the file and insert each word in the 
	 * file into the HashTable
	 * @param inputFile  the name of file to be read
	 * @throws IOException  if the file does not exist*/
	public void readFile(String inputFile) throws IOException{
		File file  = new File(inputFile);
		//File doesn't exist
		if(!file.exists())
			throw new IOException("Input file not found!");
		//To read the file in UTF8
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		//The current line of the text file
		String line;
		
		//Loops through entire file, stops when next line is null
		while((line = in.readLine()) != null){
			//Creates an array of each word in the line, looking out for any delimeter
			String[] array = line.split("[\\p{P} \\t\\n\\r]");
			
			//Inserts every string from the array into the HashTable
			for(String s : array){
				if(!s.equals("")){
					hashtable.insert(s);
					//If the Load Factor goes above 50%, then the hashtable is resized
					if((double)hashtable.getNonZeroEntries() / hashtable.getTableSize() >= 0.5)
						hashtable.resize();
					}
				}
			}
		//Closes BufferedReader
		in.close();
	}
	
	/**This method writes every word and its number of encounters to an 
	 * output file
	 * @param outputFile  desired output file name*/
	public void writeFile(String outputFile){
		try{
			//The output file
			File ofile = new File(outputFile);
			//Creates output file if it doesn't already exist
			if(!ofile.exists())
				ofile.createNewFile();
			//To write
			BufferedWriter out = new BufferedWriter(new FileWriter(ofile));
		
			//Loops through every element of the array. Each element is a LinkedList
			for(LinkedList<StringNode> n  : hashtable.getTable()){
				if(n!=null){
				
					//Loops through every node of the current LinkedList and writes results
					for(StringNode s : n){
						out.write("("+s.getWord() + " " + s.getEncounters() + ")\n");
					}
				}
			}
			//Closes BufferedWriter
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}	
	
	/**This main method enters the first two input Strings into the static
	 * wordCount method
	 * @param args  array of user-entered Strings*/
	public static void main(String[] args){
		//Only runs method if user entered exactly 2 arguments
		//if(args.length == 2)
			System.out.println(wordCount("src/Toy.txt", "src/ToyOut.txt"));	
		//else
			System.out.println("Please only insert an input file name and an"
					+ " output file name!");
	}
}
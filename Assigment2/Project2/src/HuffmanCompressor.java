/**@author Austin Feydt
 * 3/25/2015
 * EECS 233
 * Programming Assignment #2
 * This class is used to take an input text file, determine the Huffman Encoding
 * of each character in the file, and then output the same text file, but in terms
 * of the encodings of each character to save space.*/
import java.io.*;
import java.util.*;
public class HuffmanCompressor {
	//The name of the input file
	private String inputFile;
	//The name of the output file (encoded text)
	private String outputFile;
	//The file to be read
	private File file;
	/*The list of Huffman Nodes
	 * I chose an ArrayList, rather than a LinkedList, because ArrayLists
	 * autoresize themselves, unlike primitive arrays, making them easy to
	 * work with.  I also just prefer using arrays whenever I can, especially
	 * of their ability to access any element in constant time.*/
	private ArrayList<HuffmanNode> huffList = new ArrayList<HuffmanNode>(); 
	//Hashtable mapping characters to their frequencies
	private Hashtable<Character, Integer> freqTable = new Hashtable<Character,Integer>();
	//Hashtable mapping characters to their Huffman Encoding
	private Hashtable<Character, String> encodingTable = new Hashtable<Character, String>();
	
	/**2 arg constructor
	 * @param inputFile  name of file to be encoded
	 * @param outputFile  name of encoded file output*/
	public HuffmanCompressor(String inputFile, String outputFile){
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	/**This method scans the entire file and creates the ordered list of Huffman 
	 * Nodes and determines each character's frequency.
	 * @param fi - the input file being read
	 * @throws IOException */
	public void scanFrequencies() throws IOException{
		file  = new File(inputFile);
		if(!file.exists())
			throw new IOException("Input file not found!");
		BufferedReader in = new BufferedReader(new FileReader(file)); //to read file
		int current;	//The integer representation of the current character
		char character; //The current character
		
		//Loops through every character in the input file
		while((current = in.read()) != -1){
			character = (char)current;
			//Creates a new mapping in the hashtable
			if(!freqTable.containsKey(character))
				freqTable.put(character, 1);
			else
				freqTable.put(character, freqTable.get(character)+1);
		}
		in.close();
		
		//Creates an enumeration of all the keys in the hashtable
		Enumeration<Character> charEnum = freqTable.keys();
	
		//Creates ArrayList of nodes corresponding to each character in enumeration
		while(charEnum.hasMoreElements()){
			character = charEnum.nextElement();
			current = (int) character;
			HuffmanNode node = new HuffmanNode(character,freqTable.get(character), null, null);
			huffList.add(node);
		}
		//Orders the ArrayList based on frequencies
		Collections.sort(huffList, new Comparator<HuffmanNode>(){
			public int compare(HuffmanNode h1, HuffmanNode h2){
				return h1.compareTo(h2);
			}
		});
	}
	
	/**This method uses the Huffman encoding algorithm to produce a Huffman tree*/
	public void buildHuffmanTree(){
		//Loops until the root of the tree is the only entry in the ArrayList
		while(huffList.size() > 1){
			//Removes the two leftmost elements
			HuffmanNode n1 = huffList.remove(0);
			HuffmanNode n2 = huffList.remove(0);
			//Creates a new node with those two nodes as children
			HuffmanNode comb = new HuffmanNode(null, n1.getFrequency() + n2.getFrequency(), n1, n2);
			//Adds new node to end of list
			huffList.add(comb);
		}	
	}
	
	/**This method creates the output file of the encoding triples*/
	public void writeEncodings(){
		//Calls recursive method on the root of the Huffman Tree
		moveDown(huffList.get(0), "");
		try{
			File cfile = new File("src/triples.txt");//The output file
			//Creates file if it already doesn't exist
			if(!cfile.exists())
				cfile.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(cfile));//to write file
			bw.write("Encoding triples:\n");
			//Loops through every ASCII character
			for(int i = 0; i <= 127; i++){
				Character c = new Character((char)i);
				if(freqTable.containsKey(c)){
					String cString; //needed to replace "special" characters
					//Deals with these "special" characters
					if(c == '\n')
						cString = "\\n";
					else if(c == '\r')
						cString = "\\r";
					else if(c == '\t')
						cString = "\\t";
					else
						cString = c.toString();
					//Writes the triple to the file
					bw.write(cString + ": " + freqTable.get(c) + ": " 
							+ encodingTable.get(c) + "\n");
				}
			}
			bw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**This method writes the encoded output file, and computes space saving */
	public void outputEncodedText(){
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));//to read file
			File ofile = new File(outputFile);//The output file
			//Creates output file if it doesn't already exist
			if(!ofile.exists())
				ofile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(ofile));//to write
			int current;		  //the integer representation of the current char
			char character;		  //the current char
			int counter = 0;      //counter used for auto-newline
			int spaceOriginal = 0;//Keeps track of bits used in original file
			int spaceEncoded = 0; //Keeps track of bits used in encoded file
			
			//Loops through every character in the input file
			while((current = in.read()) != -1){
				counter++;
				//Each character is 8 bits
				spaceOriginal+=8;
				character = (char)current;
				spaceEncoded += String.valueOf(encodingTable.get(character)).length();
				
				//moves cursor to next line every 15 characters for neatness
				if(counter == 15){
					out.write("\n");
					counter = 0;
				}
				//Writes character's encoding to file
				out.write(encodingTable.get(character));
			}
			//Calculates space saving and writes to file
			System.out.println("\nThe total amount of savings is: " 
					+ (spaceOriginal - spaceEncoded) + " bits!" );
			in.close();
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**This method demonstrates the whole process of the program by calling
	 * the methods in the correct order
	 * @param inputFileName  name of file to be read
	 * @param outputFileName  name of encoded file to be written
	 * @return  success/failure of the execution*/
	public static String huffmanCoder(String inputFileName, String outputFileName){
		//Constructs the HuffmanCompressor using the input file names
		HuffmanCompressor h = new HuffmanCompressor(inputFileName, outputFileName);
		try{
			h.scanFrequencies();
		}
		//Handles the user entering wrong inputFile name
		catch(IOException e){
			return "Input File Error";

		}
		//Builds the tree
		h.buildHuffmanTree();
		//Writes the encoding table file
		h.writeEncodings();
		//Writes the encoded text file
		h.outputEncodedText();
		return "OK";
	}
	
	/**This method using recursion to get the encoding of every character in the
	 * Huffman Tree
	 * @param node  the current node the pointer is at
	 * @param digits  the current binary path of the pointer*/
	private void moveDown(HuffmanNode node, String digits){
		String nodeEncoding = digits;//Used to keep track of the binary representation
		
		//Base case: adds the character and it's encoding to the encoding hashtable
		if(node.getLeft() == null && node.getRight() == null){
			encodingTable.put(node.getInChar(), nodeEncoding);
		}
		//Deals with current node having children
		else{
			if(node.getLeft() != null){
				//Recursive call on node's left subtree, adds a 0 since moving left
				moveDown(node.getLeft(), nodeEncoding + "0");
			}
			if(node.getRight() != null){
				//Recursive call on node's right subtree, adds a 1 since moving right
				moveDown(node.getRight(), nodeEncoding + "1");
			}
		}
	}
	
	/**The main method tests all of the above methods with a single method call
	 * @param args  array of user inputs*/
	public static void main(String[] args){
		//Will only run if the user enters just two file names
		if(args.length == 2)
			System.out.println(huffmanCoder(args[0], args[1]));
		//error message
		else
			System.out.println("Please enter an input file name and "
					+ "an output file name.");
	}
}
/*
 * A class that counts the words in one or more files.
 * A thread is started for each file.
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordCount1 {
	public static void main(String[] args) {
		int fileNum = args.length;
		if(fileNum == 0) {
			System.out.println("No files were given; program is shutting down . . . ");
			System.exit(0);
		}
		
		HashMap<String, Integer> files = new HashMap<>();
		
		ArrayList<WordCounter> threads = new ArrayList<WordCounter>();
		for(int i = 0; i < args.length; i++) {
			WordCounter wc = new WordCounter(args[i]);
			threads.add(wc);
			wc.start();
		}
		
		for(WordCounter wc : threads) {
			try {
				wc.join();
				files.put(wc.getFileName(), wc.getCount());
			} catch(Exception ex) {	
			}
		}
		
		System.out.println("The total word counts:");
		for(String s : files.keySet()) {
			System.out.println(s + ": " + files.get(s));
		}
	}
}

class WordCounter extends Thread{
	private String file;
	int count;
	
	/*
	 * Constructor of a WordCounter thread object
	 * 
	 * @param fileName the name of the file to count the words of
	 */
	public WordCounter(String fileName) {
		this.file = fileName;
	}
	
	/*
	 * Returns the name of the file
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return this.file;
	}
	
	/*
	 * Returns the number of words in the file
	 * 
	 * @return the word count
	 */
	public int getCount() {
		return this.count;
	}
	
	@Override
	public void run() {
		count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line = br.readLine();
		        
		     while(line != null) {
		    	 count += line.split(" ").length;
				 line = br.readLine();
		     }
		     br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
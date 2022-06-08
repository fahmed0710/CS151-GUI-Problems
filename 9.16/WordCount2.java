import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordCount2{
	/*
	 * Static factory method to create a Runnable to count the number of words in a file
	 * 
	 * @param name the name of the file
	 */
	public static Runnable counter(String name) {
		return() -> {
			int count = 0;
			try {
				Scanner reader = new Scanner(new File(name));
				while(reader.hasNextLine()) {
					count += reader.nextLine().split(" ").length;
				}
				reader.close();
				System.out.println(name + ": " + count);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ExecutorService service = Executors.newCachedThreadPool();
		
		for(String file : args) {
			service.execute(counter(file));
		}
		
		service.shutdown();
	}
}

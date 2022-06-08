import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class WordCount2 {
	/*
	 * Static factory method to create a Callable 
	 * that'll output the number of words in a file
	 * 
	 * @param file the file name
	 * @return a Callable that gives the number of words in a file
	 */
	public static Callable<Integer> counter(String name){
		return new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int count = 0;
				try {
					File f = new File(name);
					Scanner reader = new Scanner(f);
					while(reader.hasNextLine()) {
						count += reader.nextLine().split(" ").length;
					}
					reader.close();
					System.out.print(name + ": " + count + "\n");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("The file " + name + "was not found.");
				}
				return count;
			}
		};
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		List<Callable<Integer>> tasks = new ArrayList<>();
		for(String file : args) {
			tasks.add(counter(file));
		}
		
		ExecutorService service = Executors.newCachedThreadPool();
		List<Future<Integer>> results = service.invokeAll(tasks);
		
		service.shutdown();
		
		int total = 0;
		for(Future<Integer> result : results) {
			total += result.get();
		}
		System.out.println("The total word count is: " + total);
	}
}
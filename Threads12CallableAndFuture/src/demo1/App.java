package demo1;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		Future<Integer> futureReturn = executor.submit(new Callable<Integer>() {
			
			public Integer call() throws IOException {
				int duration = new Random().nextInt(4000);

				if(duration > 2000) {
					throw new IOException("Sleeping for too long...");
				}
				
				System.out.println("Starting...");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Finishing...");
				
				return duration;
			}
		});

		executor.shutdown(); // dont get any more tasks
		
		
		
		try {
			System.out.println("Result is: " + futureReturn.get());
		} catch (InterruptedException | ExecutionException e) {
			IOException ex = (IOException)e.getCause();
			System.out.println(ex.getMessage());
		}
	}
}
